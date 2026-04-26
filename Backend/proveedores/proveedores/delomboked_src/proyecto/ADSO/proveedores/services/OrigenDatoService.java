package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.OrigenDatoEntity;
import proyecto.ADSO.proveedores.repositories.OrigenDatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrigenDatoService {

    @Autowired
    private OrigenDatoRepository repository;

    public boolean create(OrigenDatoCreateRequestDto dto){
        OrigenDatoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<OrigenDatoResponseDto> getAll(){
        List<OrigenDatoEntity> entities = this.repository.findAll();
        List<OrigenDatoResponseDto> dtos = new ArrayList<>();
        for (OrigenDatoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public OrigenDatoResponseDto getDetail(Integer id){
        OrigenDatoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, OrigenDatoCreateRequestDto dto) {
        OrigenDatoEntity entity = validateIfExist(id);
        OrigenDatoEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        OrigenDatoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public OrigenDatoEntity validateIfExist(Integer id){
        Optional<OrigenDatoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public OrigenDatoEntity dtoToEntity(OrigenDatoCreateRequestDto dto){
        return OrigenDatoEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public OrigenDatoResponseDto entityToDto(OrigenDatoEntity entity){
        return OrigenDatoResponseDto.builder()
                .idOrigen(entity.getIdOrigen())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
