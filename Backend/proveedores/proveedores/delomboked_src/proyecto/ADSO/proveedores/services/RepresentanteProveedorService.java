package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.RepresentanteProveedorEntity;
import proyecto.ADSO.proveedores.repositories.RepresentanteProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RepresentanteProveedorService {

    @Autowired
    private RepresentanteProveedorRepository repository;

    public boolean create(RepresentanteProveedorCreateRequestDto dto){
        RepresentanteProveedorEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<RepresentanteProveedorResponseDto> getAll(){
        List<RepresentanteProveedorEntity> entities = this.repository.findAll();
        List<RepresentanteProveedorResponseDto> dtos = new ArrayList<>();
        for (RepresentanteProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public RepresentanteProveedorResponseDto getDetail(Integer id){
        RepresentanteProveedorEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, RepresentanteProveedorCreateRequestDto dto) {
        RepresentanteProveedorEntity entity = validateIfExist(id);
        RepresentanteProveedorEntity newEntity = dtoToEntity(dto);
        entity.setIdRepresentanteLegal(newEntity.getIdRepresentanteLegal());
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setCargo(newEntity.getCargo());
        entity.setFechaInicio(newEntity.getFechaInicio());
        entity.setFechaFin(newEntity.getFechaFin());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        entity.setREFERENCES(newEntity.getREFERENCES());
        entity.setREFERENCES(newEntity.getREFERENCES());
        entity.setREFERENCES(newEntity.getREFERENCES());
        entity.setREFERENCES(newEntity.getREFERENCES());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        RepresentanteProveedorEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public RepresentanteProveedorEntity validateIfExist(Integer id){
        Optional<RepresentanteProveedorEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public RepresentanteProveedorEntity dtoToEntity(RepresentanteProveedorCreateRequestDto dto){
        return RepresentanteProveedorEntity.builder()
                .idRepresentanteLegal(dto.getIdRepresentanteLegal())
                .idProveedor(dto.getIdProveedor())
                .cargo(dto.getCargo())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .REFERENCES(dto.getREFERENCES())
                .REFERENCES(dto.getREFERENCES())
                .REFERENCES(dto.getREFERENCES())
                .REFERENCES(dto.getREFERENCES())
                .build();
    }

    public RepresentanteProveedorResponseDto entityToDto(RepresentanteProveedorEntity entity){
        return RepresentanteProveedorResponseDto.builder()
                .idRelacion(entity.getIdRelacion())
                .idRepresentanteLegal(entity.getIdRepresentanteLegal())
                .idProveedor(entity.getIdProveedor())
                .cargo(entity.getCargo())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .REFERENCES(entity.getREFERENCES())
                .REFERENCES(entity.getREFERENCES())
                .REFERENCES(entity.getREFERENCES())
                .REFERENCES(entity.getREFERENCES())
                .build();
    }
}
