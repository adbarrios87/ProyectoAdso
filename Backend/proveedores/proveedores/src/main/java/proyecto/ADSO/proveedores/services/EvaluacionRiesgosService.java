package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.EvaluacionRiesgosEntity;
import proyecto.ADSO.proveedores.repositories.EvaluacionRiesgosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvaluacionRiesgosService {

    @Autowired
    private EvaluacionRiesgosRepository repository;

    public boolean create(EvaluacionRiesgosCreateRequestDto dto){
        EvaluacionRiesgosEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<EvaluacionRiesgosResponseDto> getAll(){
        List<EvaluacionRiesgosEntity> entities = this.repository.findAll();
        List<EvaluacionRiesgosResponseDto> dtos = new ArrayList<>();
        for (EvaluacionRiesgosEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public EvaluacionRiesgosResponseDto getDetail(Integer id){
        EvaluacionRiesgosEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, EvaluacionRiesgosCreateRequestDto dto) {
        EvaluacionRiesgosEntity entity = validateIfExist(id);
        EvaluacionRiesgosEntity newEntity = dtoToEntity(dto);
        entity.setIdUsuario(newEntity.getIdUsuario());
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdValidacion(newEntity.getIdValidacion());
        entity.setValidacionAuditoria(newEntity.getValidacionAuditoria());
        entity.setFecha(newEntity.getFecha());
        entity.setComentariosAuditoria(newEntity.getComentariosAuditoria());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        EvaluacionRiesgosEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public EvaluacionRiesgosEntity validateIfExist(Integer id){
        Optional<EvaluacionRiesgosEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public EvaluacionRiesgosEntity dtoToEntity(EvaluacionRiesgosCreateRequestDto dto){
        return EvaluacionRiesgosEntity.builder()
                .idUsuario(dto.getIdUsuario())
                .idProveedor(dto.getIdProveedor())
                .idValidacion(dto.getIdValidacion())
                .validacionAuditoria(dto.getValidacionAuditoria())
                .fecha(dto.getFecha())
                .comentariosAuditoria(dto.getComentariosAuditoria())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public EvaluacionRiesgosResponseDto entityToDto(EvaluacionRiesgosEntity entity){
        return EvaluacionRiesgosResponseDto.builder()
                .idEvaluacionRiesgos(entity.getIdEvaluacionRiesgos())
                .idUsuario(entity.getIdUsuario())
                .idProveedor(entity.getIdProveedor())
                .idValidacion(entity.getIdValidacion())
                .validacionAuditoria(entity.getValidacionAuditoria())
                .fecha(entity.getFecha())
                .comentariosAuditoria(entity.getComentariosAuditoria())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
