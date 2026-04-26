package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.CalificacionEntity;
import proyecto.ADSO.proveedores.repositories.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository repository;

    public boolean create(CalificacionCreateRequestDto dto){
        CalificacionEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<CalificacionResponseDto> getAll(){
        List<CalificacionEntity> entities = this.repository.findAll();
        List<CalificacionResponseDto> dtos = new ArrayList<>();
        for (CalificacionEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public CalificacionResponseDto getDetail(Integer id){
        CalificacionEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, CalificacionCreateRequestDto dto) {
        CalificacionEntity entity = validateIfExist(id);
        CalificacionEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        CalificacionEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public CalificacionEntity validateIfExist(Integer id){
        Optional<CalificacionEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public CalificacionEntity dtoToEntity(CalificacionCreateRequestDto dto){
        return CalificacionEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public CalificacionResponseDto entityToDto(CalificacionEntity entity){
        return CalificacionResponseDto.builder()
                .idCalificacion(entity.getIdCalificacion())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
