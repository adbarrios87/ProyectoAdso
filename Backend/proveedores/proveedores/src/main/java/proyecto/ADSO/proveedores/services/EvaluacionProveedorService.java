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

    public List<EvaluacionProveedorResponseDto> getByIdProveedor(Integer idProveedor) {
        List<EvaluacionProveedorEntity> entities = this.repository.findByIdProveedor(idProveedor);
        List<EvaluacionProveedorResponseDto> dtos = new ArrayList<>();
        for (EvaluacionProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public boolean create(EvaluacionProveedorCreateRequestDto dto){
        EvaluacionProveedorEntity entity = this.dtoToEntity(dto);
        calcularPuntajeYCalificacion(entity);
        if (entity.getFechaCreado() == null) {
            entity.setFechaCreado(java.time.LocalDateTime.now());
        }
        if (entity.getActivo() == null) {
            entity.setActivo(true);
        }
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
        entity.setCalidad(newEntity.getCalidad());
        entity.setObsCalidad(newEntity.getObsCalidad());
        entity.setTiempo(newEntity.getTiempo());
        entity.setObsTiempo(newEntity.getObsTiempo());
        entity.setDocumenta(newEntity.getDocumenta());
        entity.setObsDocumental(newEntity.getObsDocumental());
        entity.setSarlaft(newEntity.getSarlaft());
        entity.setObsSarlaft(newEntity.getObsSarlaft());
        entity.setComercial(newEntity.getComercial());
        entity.setObsComercial(newEntity.getObsComercial());
        entity.setSocial(newEntity.getSocial());
        entity.setObsSocial(newEntity.getObsSocial());
        entity.setMejora(newEntity.getMejora());
        entity.setObsMejora(newEntity.getObsMejora());
        
        calcularPuntajeYCalificacion(entity);
        
        entity.setObservaciones(newEntity.getObservaciones());
        entity.setUrlCalificacion(newEntity.getUrlCalificacion());
        entity.setFechaModificado(java.time.LocalDateTime.now());
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

    private void calcularPuntajeYCalificacion(EvaluacionProveedorEntity entity) {
        int sum = (entity.getCalidad() != null ? entity.getCalidad() : 0) +
                  (entity.getTiempo() != null ? entity.getTiempo() : 0) +
                  (entity.getDocumenta() != null ? entity.getDocumenta() : 0) +
                  (entity.getSarlaft() != null ? entity.getSarlaft() : 0) +
                  (entity.getComercial() != null ? entity.getComercial() : 0) +
                  (entity.getSocial() != null ? entity.getSocial() : 0) +
                  (entity.getMejora() != null ? entity.getMejora() : 0);
        
        int puntajeCalculado = Math.round((sum / 35.0f) * 100);
        entity.setPuntaje(puntajeCalculado);
        
        if (puntajeCalculado < 60) {
            entity.setIdCalificacion(3); // No Confiable
        } else if (puntajeCalculado <= 80) {
            entity.setIdCalificacion(2); // Alternativo
        } else {
            entity.setIdCalificacion(1); // Confiable
        }
    }

    public EvaluacionProveedorEntity dtoToEntity(EvaluacionProveedorCreateRequestDto dto){
        return EvaluacionProveedorEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .idUsuario(dto.getIdUsuario())
                .idCalificacion(dto.getIdCalificacion())
                .puntaje(dto.getPuntaje())
                .observaciones(dto.getObservaciones())
                .urlCalificacion(dto.getUrlCalificacion())
                .calidad(dto.getCalidad())
                .obsCalidad(dto.getObsCalidad())
                .tiempo(dto.getTiempo())
                .obsTiempo(dto.getObsTiempo())
                .documenta(dto.getDocumenta())
                .obsDocumental(dto.getObsDocumental())
                .sarlaft(dto.getSarlaft())
                .obsSarlaft(dto.getObsSarlaft())
                .comercial(dto.getComercial())
                .obsComercial(dto.getObsComercial())
                .social(dto.getSocial())
                .obsSocial(dto.getObsSocial())
                .mejora(dto.getMejora())
                .obsMejora(dto.getObsMejora())
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
                .calidad(entity.getCalidad())
                .obsCalidad(entity.getObsCalidad())
                .tiempo(entity.getTiempo())
                .obsTiempo(entity.getObsTiempo())
                .documenta(entity.getDocumenta())
                .obsDocumental(entity.getObsDocumental())
                .sarlaft(entity.getSarlaft())
                .obsSarlaft(entity.getObsSarlaft())
                .comercial(entity.getComercial())
                .obsComercial(entity.getObsComercial())
                .social(entity.getSocial())
                .obsSocial(entity.getObsSocial())
                .mejora(entity.getMejora())
                .obsMejora(entity.getObsMejora())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
