package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.NotificacionesEntity;
import proyecto.ADSO.proveedores.repositories.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository repository;

    public boolean create(NotificacionesCreateRequestDto dto){
        NotificacionesEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<NotificacionesResponseDto> getAll(){
        List<NotificacionesEntity> entities = this.repository.findAll();
        List<NotificacionesResponseDto> dtos = new ArrayList<>();
        for (NotificacionesEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public List<NotificacionesResponseDto> getByUserId(Integer userId) {
        List<NotificacionesEntity> entities = this.repository.findByIdUsuario(userId);
        List<NotificacionesResponseDto> dtos = new ArrayList<>();
        for (NotificacionesEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public NotificacionesResponseDto getDetail(Integer id){
        NotificacionesEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, NotificacionesCreateRequestDto dto) {
        NotificacionesEntity entity = validateIfExist(id);
        NotificacionesEntity newEntity = dtoToEntity(dto);
        entity.setIdUsuario(newEntity.getIdUsuario());
        entity.setIdTipoNotificacion(newEntity.getIdTipoNotificacion());
        entity.setFechaNotificacion(newEntity.getFechaNotificacion());
        entity.setMensaje(newEntity.getMensaje());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        NotificacionesEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public NotificacionesEntity validateIfExist(Integer id){
        Optional<NotificacionesEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public NotificacionesEntity dtoToEntity(NotificacionesCreateRequestDto dto){
        return NotificacionesEntity.builder()
                .idUsuario(dto.getIdUsuario())
                .idTipoNotificacion(dto.getIdTipoNotificacion())
                .fechaNotificacion(dto.getFechaNotificacion())
                .mensaje(dto.getMensaje())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public NotificacionesResponseDto entityToDto(NotificacionesEntity entity){
        return NotificacionesResponseDto.builder()
                .idNotificacion(entity.getIdNotificacion())
                .idUsuario(entity.getIdUsuario())
                .idTipoNotificacion(entity.getIdTipoNotificacion())
                .fechaNotificacion(entity.getFechaNotificacion())
                .mensaje(entity.getMensaje())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
