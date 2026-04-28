package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.HistorialUsuarioEntity;
import proyecto.ADSO.proveedores.repositories.HistorialUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistorialUsuarioService {

    @Autowired
    private HistorialUsuarioRepository repository;

    public boolean create(HistorialUsuarioCreateRequestDto dto){
        HistorialUsuarioEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<HistorialUsuarioResponseDto> getAll(){
        List<HistorialUsuarioEntity> entities = this.repository.findAll();
        List<HistorialUsuarioResponseDto> dtos = new ArrayList<>();
        for (HistorialUsuarioEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public HistorialUsuarioResponseDto getDetail(Integer id){
        HistorialUsuarioEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, HistorialUsuarioCreateRequestDto dto) {
        HistorialUsuarioEntity entity = validateIfExist(id);
        HistorialUsuarioEntity newEntity = dtoToEntity(dto);
        entity.setIdUsuario(newEntity.getIdUsuario());
        entity.setIdEstadoUsuario(newEntity.getIdEstadoUsuario());
        entity.setComentarios(newEntity.getComentarios());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        HistorialUsuarioEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public HistorialUsuarioEntity validateIfExist(Integer id){
        Optional<HistorialUsuarioEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public HistorialUsuarioEntity dtoToEntity(HistorialUsuarioCreateRequestDto dto){
        return HistorialUsuarioEntity.builder()
                .idUsuario(dto.getIdUsuario())
                .idEstadoUsuario(dto.getIdEstadoUsuario())
                .comentarios(dto.getComentarios())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public HistorialUsuarioResponseDto entityToDto(HistorialUsuarioEntity entity){
        return HistorialUsuarioResponseDto.builder()
                .idHistorialUsuario(entity.getIdHistorialUsuario())
                .idUsuario(entity.getIdUsuario())
                .idEstadoUsuario(entity.getIdEstadoUsuario())
                .comentarios(entity.getComentarios())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
