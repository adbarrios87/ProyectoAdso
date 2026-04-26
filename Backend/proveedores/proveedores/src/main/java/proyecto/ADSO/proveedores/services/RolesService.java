package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.RolesEntity;
import proyecto.ADSO.proveedores.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolesService {

    @Autowired
    private RolesRepository repository;

    public boolean create(RolesCreateRequestDto dto){
        RolesEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<RolesResponseDto> getAll(){
        List<RolesEntity> entities = this.repository.findAll();
        List<RolesResponseDto> dtos = new ArrayList<>();
        for (RolesEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public RolesResponseDto getDetail(Integer id){
        RolesEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, RolesCreateRequestDto dto) {
        RolesEntity entity = validateIfExist(id);
        RolesEntity newEntity = dtoToEntity(dto);
        entity.setRol(newEntity.getRol());
        entity.setEstadoRol(newEntity.getEstadoRol());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        RolesEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public RolesEntity validateIfExist(Integer id){
        Optional<RolesEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public RolesEntity dtoToEntity(RolesCreateRequestDto dto){
        return RolesEntity.builder()
                .rol(dto.getRol())
                .estadoRol(dto.getEstadoRol())
                .descripcion(dto.getDescripcion())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .build();
    }

    public RolesResponseDto entityToDto(RolesEntity entity){
        return RolesResponseDto.builder()
                .idRol(entity.getIdRol())
                .rol(entity.getRol())
                .estadoRol(entity.getEstadoRol())
                .descripcion(entity.getDescripcion())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .build();
    }
}
