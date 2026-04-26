package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.EvaluacionProveedorEntity;
import proyecto.ADSO.proveedores.repositories.EvaluacionProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvaluacionProveedorService {

    @Autowired
    private EvaluacionProveedorRepository repository;

    public boolean create(EvaluacionProveedorCreateRequestDto dto){
        EvaluacionProveedorEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<EvaluacionProveedorResponseDto> getAll(){
        List<EvaluacionProveedorEntity> entities = this.repository.findAll();
        List<EvaluacionProveedorResponseDto> dtos = new ArrayList<>();
        for (EvaluacionProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public EvaluacionProveedorResponseDto getDetail(Integer id){
        EvaluacionProveedorEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, EvaluacionProveedorCreateRequestDto dto) {
        EvaluacionProveedorEntity entity = validateIfExist(id);
        EvaluacionProveedorEntity newEntity = dtoToEntity(dto);
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdUsuario(newEntity.getIdUsuario());
        entity.setIdCalificacion(newEntity.getIdCalificacion());
        entity.setPuntaje(newEntity.getPuntaje());
        entity.setObservaciones(newEntity.getObservaciones());
        entity.setUrlCalificacion(newEntity.getUrlCalificacion());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        EvaluacionProveedorEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public EvaluacionProveedorEntity validateIfExist(Integer id){
        Optional<EvaluacionProveedorEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public EvaluacionProveedorEntity dtoToEntity(EvaluacionProveedorCreateRequestDto dto){
        return EvaluacionProveedorEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .idUsuario(dto.getIdUsuario())
                .idCalificacion(dto.getIdCalificacion())
                .puntaje(dto.getPuntaje())
                .observaciones(dto.getObservaciones())
                .urlCalificacion(dto.getUrlCalificacion())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public EvaluacionProveedorResponseDto entityToDto(EvaluacionProveedorEntity entity){
        return EvaluacionProveedorResponseDto.builder()
                .idEvaluacion(entity.getIdEvaluacion())
                .idProveedor(entity.getIdProveedor())
                .idUsuario(entity.getIdUsuario())
                .idCalificacion(entity.getIdCalificacion())
                .puntaje(entity.getPuntaje())
                .observaciones(entity.getObservaciones())
                .urlCalificacion(entity.getUrlCalificacion())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
