const fs = require('fs');
const path = require('path');

const SQL_FILE = 'c:\\Users\\Spriggan\\ProyectoAdso\\src\\db\\create_tables.sql';
const BASE_PATH = 'd:\\Informacion compu\\Documents\\SENA\\proyecto\\proveedores\\proveedores\\src\\main\\java\\proyecto\\ADSO\\proveedores';
const BASE_PKG = 'proyecto.ADSO.proveedores';

const TYPE_MAPPING = {
    'INT': 'Integer',
    'VARCHAR': 'String',
    'TEXT': 'String',
    'BOOLEAN': 'Boolean',
    'DATETIME': 'java.time.LocalDateTime',
    'DATE': 'java.time.LocalDate',
    'DECIMAL': 'java.math.BigDecimal',
    'LONGBLOB': 'byte[]',
    'BIGINT': 'Long'
};

function toCamelCase(str) {
    const parts = str.split('_');
    return parts[0] + parts.slice(1).map(x => x.charAt(0).toUpperCase() + x.slice(1)).join('');
}

function toPascalCase(str) {
    const parts = str.split('_');
    return parts.map(x => x.charAt(0).toUpperCase() + x.slice(1)).join('');
}

function parseSql() {
    const content = fs.readFileSync(SQL_FILE, 'utf8');
    const tables = [];
    const tableRegex = /CREATE TABLE\s+(\w+)\s*\(([\s\S]*?)\)\s*ENGINE/gi;
    let match;
    
    while ((match = tableRegex.exec(content)) !== null) {
        const tableName = match[1];
        const block = match[2];
        const columns = [];
        
        const lines = block.split('\n');
        for (let line of lines) {
            line = line.trim();
            if (!line || line.startsWith('--') || line.startsWith('PRIMARY KEY') || line.startsWith('CONSTRAINT') || line.startsWith('UNIQUE') || line.startsWith('ON ') || line.startsWith('FOREIGN KEY') || line.startsWith('REFERENCES')) {
                continue;
            }
            
            const colMatch = line.match(/^`?(\w+)`?\s+(\w+)/);
            if (colMatch) {
                const colName = colMatch[1];
                const sqlType = colMatch[2].toUpperCase();
                let javaType = TYPE_MAPPING[sqlType] || 'String';
                if (sqlType === 'ENUM') javaType = 'String';
                
                const isPk = line.toUpperCase().includes('PRIMARY KEY');
                
                columns.push({
                    name: colName,
                    javaName: toCamelCase(colName),
                    sqlType: sqlType,
                    javaType: javaType,
                    isPk: isPk
                });
            }
        }
        tables.push({ name: tableName, columns: columns });
    }
    return tables;
}

function writeFile(folder, filename, content) {
    const folderPath = path.join(BASE_PATH, folder);
    if (!fs.existsSync(folderPath)) {
        fs.mkdirSync(folderPath, { recursive: true });
    }
    fs.writeFileSync(path.join(folderPath, filename), content, 'utf8');
}

function generateDtos(table) {
    const tName = toPascalCase(table.name);
    
    const fieldsCreate = table.columns.filter(c => !c.isPk).map(c => `    private ${c.javaType} ${c.javaName};`);
    const contentCreate = `package ${BASE_PKG}.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${tName}CreateRequestDto {
${fieldsCreate.join('\n')}
}
`;
    writeFile('dtos', `${tName}CreateRequestDto.java`, contentCreate);

    const fieldsResp = table.columns.map(c => `    private ${c.javaType} ${c.javaName};`);
    const contentResp = `package ${BASE_PKG}.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${tName}ResponseDto {
${fieldsResp.join('\n')}
}
`;
    writeFile('dtos', `${tName}ResponseDto.java`, contentResp);
}

function generateEntity(table) {
    const tName = toPascalCase(table.name);
    
    const fields = table.columns.map(c => {
        let res = '';
        if (c.isPk) {
            res += `    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n`;
        }
        res += `    @Column(name = "${c.name}")\n`;
        res += `    private ${c.javaType} ${c.javaName};`;
        return res;
    });
    
    const content = `package ${BASE_PKG}.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "${table.name}")
public class ${tName}Entity {

${fields.join('\n\n')}

}
`;
    writeFile('entites', `${tName}Entity.java`, content);
}

function generateRepository(table) {
    const tName = toPascalCase(table.name);
    const pk = table.columns.find(c => c.isPk);
    const pkType = pk ? pk.javaType : 'Integer';
    
    const content = `package ${BASE_PKG}.repositories;

import ${BASE_PKG}.entites.${tName}Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ${tName}Repository extends
        JpaRepository<${tName}Entity, ${pkType}>,
        JpaSpecificationExecutor<${tName}Entity> {
}
`;
    writeFile('repositories', `${tName}Repository.java`, content);
}

function generateService(table) {
    const tName = toPascalCase(table.name);
    const pk = table.columns.find(c => c.isPk);
    const pkType = pk ? pk.javaType : 'Integer';
    
    const dtoToEntity = table.columns.filter(c => !c.isPk).map(c => {
        const getter = `get${c.javaName.charAt(0).toUpperCase() + c.javaName.slice(1)}()`;
        return `                .${c.javaName}(dto.${getter})`;
    });
    
    const entityToDto = table.columns.map(c => {
        const getter = `get${c.javaName.charAt(0).toUpperCase() + c.javaName.slice(1)}()`;
        return `                .${c.javaName}(entity.${getter})`;
    });
    
    const updateSets = table.columns.filter(c => !c.isPk).map(c => {
        const getter = `get${c.javaName.charAt(0).toUpperCase() + c.javaName.slice(1)}()`;
        const setter = `set${c.javaName.charAt(0).toUpperCase() + c.javaName.slice(1)}`;
        return `        entity.${setter}(newEntity.${getter});`;
    });
    
    const content = `package ${BASE_PKG}.services;

import ${BASE_PKG}.dtos.*;
import ${BASE_PKG}.entites.${tName}Entity;
import ${BASE_PKG}.repositories.${tName}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ${tName}Service {

    @Autowired
    private ${tName}Repository repository;

    public boolean create(${tName}CreateRequestDto dto){
        ${tName}Entity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<${tName}ResponseDto> getAll(){
        List<${tName}Entity> entities = this.repository.findAll();
        List<${tName}ResponseDto> dtos = new ArrayList<>();
        for (${tName}Entity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ${tName}ResponseDto getDetail(${pkType} id){
        ${tName}Entity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(${pkType} id, ${tName}CreateRequestDto dto) {
        ${tName}Entity entity = validateIfExist(id);
        ${tName}Entity newEntity = dtoToEntity(dto);
${updateSets.join('\n')}
        this.repository.save(entity);
        return true;
    }

    public void delete(${pkType} id) {
        ${tName}Entity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ${tName}Entity validateIfExist(${pkType} id){
        Optional<${tName}Entity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ${tName}Entity dtoToEntity(${tName}CreateRequestDto dto){
        return ${tName}Entity.builder()
${dtoToEntity.join('\n')}
                .build();
    }

    public ${tName}ResponseDto entityToDto(${tName}Entity entity){
        return ${tName}ResponseDto.builder()
${entityToDto.join('\n')}
                .build();
    }
}
`;
    writeFile('services', `${tName}Service.java`, content);
}

function generateController(table) {
    const tName = toPascalCase(table.name);
    const pk = table.columns.find(c => c.isPk);
    const pkType = pk ? pk.javaType : 'Integer';
    
    const content = `package ${BASE_PKG}.controllers;

import ${BASE_PKG}.dtos.*;
import ${BASE_PKG}.services.${tName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${table.name}")
public class ${tName}Controller {

    @Autowired
    private ${tName}Service service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated ${tName}CreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<${tName}ResponseDto>> getAll(){
        List<${tName}ResponseDto> response = this.service.getAll();

        return ResponseDto.<List<${tName}ResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<${tName}ResponseDto> getDetail(@PathVariable ${pkType} id){
        ${tName}ResponseDto response = this.service.getDetail(id);
        return ResponseDto.<${tName}ResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable ${pkType} id,
            @RequestBody @Validated ${tName}CreateRequestDto dto
    ){
        boolean response = this.service.update(id, dto);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<GeneralResponseDto> delete(@PathVariable ${pkType} id){
        this.service.delete(id);

        return ResponseDto.<GeneralResponseDto>builder()
                .data(GeneralResponseDto.builder()
                        .successful(true)
                        .build())
                .build();
    }
}
`;
    writeFile('controllers', `${tName}Controller.java`, content);
}

function generateCommonDtos() {
    const contentResp = `package ${BASE_PKG}.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private T data;
}
`;
    writeFile('dtos', 'ResponseDto.java', contentResp);
    
    const contentGen = `package ${BASE_PKG}.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseDto {
    private boolean successful;
}
`;
    writeFile('dtos', 'GeneralResponseDto.java', contentGen);
}

function main() {
    const tables = parseSql();
    generateCommonDtos();
    let count = 0;
    for (let table of tables) {
        if (table.columns.length === 0) continue;
        generateDtos(table);
        generateEntity(table);
        generateRepository(table);
        generateService(table);
        generateController(table);
        count++;
    }
    console.log(`Generadas ${count} entidades correctamente.`);
}

main();
