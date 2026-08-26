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

    @Autowired
    private proyecto.ADSO.proveedores.repositories.TipoNotificacionRepository tipoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private proyecto.ADSO.proveedores.repositories.UsuariosRepository usuariosRepository;

    public void generarNotificacion(Integer idUsuario, String codigoTipo, String mensaje, boolean esExterna) {
        // 1. Obtener el tipo de notificación
        Optional<proyecto.ADSO.proveedores.entites.TipoNotificacionEntity> optTipo = tipoRepository.findByCodigo(codigoTipo);
        if (optTipo.isEmpty()) {
            System.err.println("Tipo de notificación no encontrado: " + codigoTipo);
            return;
        }

        if (esExterna) {
            // Enviar correo
            Optional<proyecto.ADSO.proveedores.entites.UsuariosEntity> optUsuario = usuariosRepository.findById(idUsuario);
            if (optUsuario.isPresent() && optUsuario.get().getCorreoUsuario() != null) {
                emailService.sendSystemNotification(optUsuario.get().getCorreoUsuario(), "Notificación: " + optTipo.get().getDescripcion(), mensaje);
            }
        } else {
            // Guardar en base de datos para la campana
            NotificacionesEntity notificacion = NotificacionesEntity.builder()
                    .idUsuario(idUsuario)
                    .idTipoNotificacion(optTipo.get().getIdTipoNotificacion())
                    .mensaje(mensaje)
                    .fechaNotificacion(java.time.LocalDateTime.now())
                    .fechaCreado(java.time.LocalDateTime.now())
                    .creadoPor(1) // Sistema
                    .activo(true)
                    .build();
            repository.save(notificacion);
        }
    }

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
        List<NotificacionesEntity> entities = this.repository.findByIdUsuarioAndActivoTrueOrderByFechaNotificacionDesc(userId);
        List<NotificacionesResponseDto> dtos = new ArrayList<>();
        for (NotificacionesEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public long getActiveCountByUserId(Integer userId) {
        return this.repository.countByIdUsuarioAndActivoTrue(userId);
    }

    public boolean desactivarNotificacion(Integer id) {
        NotificacionesEntity entity = validateIfExist(id);
        entity.setActivo(false);
        this.repository.save(entity);
        return true;
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
        String codigoTipo = null;
        String descripcionTipo = null;
        
        if (entity.getIdTipoNotificacion() != null) {
            Optional<proyecto.ADSO.proveedores.entites.TipoNotificacionEntity> optTipo = tipoRepository.findById(entity.getIdTipoNotificacion());
            if (optTipo.isPresent()) {
                codigoTipo = optTipo.get().getCodigo();
                descripcionTipo = optTipo.get().getDescripcion();
            }
        }

        return NotificacionesResponseDto.builder()
                .idNotificacion(entity.getIdNotificacion())
                .idUsuario(entity.getIdUsuario())
                .idTipoNotificacion(entity.getIdTipoNotificacion())
                .codigoTipo(codigoTipo)
                .descripcionTipo(descripcionTipo)
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
