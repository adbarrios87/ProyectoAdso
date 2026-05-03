package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.UsuariosEntity;
import proyecto.ADSO.proveedores.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    public boolean create(UsuariosCreateRequestDto dto){
        UsuariosEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<UsuariosResponseDto> getAll(){
        List<UsuariosEntity> entities = this.repository.findAll();
        List<UsuariosResponseDto> dtos = new ArrayList<>();
        for (UsuariosEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public UsuariosResponseDto getDetail(Integer id){
        UsuariosEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, UsuariosCreateRequestDto dto) {
        UsuariosEntity entity = validateIfExist(id);
        UsuariosEntity newEntity = dtoToEntity(dto);
        entity.setNombreUsuario(newEntity.getNombreUsuario());
        entity.setCargoUsuario(newEntity.getCargoUsuario());
        entity.setCorreoUsuario(newEntity.getCorreoUsuario());
        entity.setContrasena(newEntity.getContrasena());
        entity.setEstadoUsuario(newEntity.getEstadoUsuario());
        entity.setIdRol(newEntity.getIdRol());
        entity.setFotoUrl(newEntity.getFotoUrl());
        entity.setUltimoIngreso(newEntity.getUltimoIngreso());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        UsuariosEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public boolean updateFoto(Integer id, String fotoUrl) {
        UsuariosEntity entity = validateIfExist(id);
        entity.setFotoUrl(fotoUrl);
        this.repository.save(entity);
        return true;
    }

    public UsuariosEntity validateIfExist(Integer id){
        Optional<UsuariosEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Optional<UsuariosEntity> optEntity = this.repository.findByNombreUsuarioAndContrasena(
                request.getUsuario(), request.getContrasena());

        if (optEntity.isPresent()) {
            UsuariosEntity entity = optEntity.get();
            return LoginResponseDto.builder()
                    .successful(true)
                    .message("Login exitoso")
                    .idUsuario(entity.getIdUsuario())
                    .nombreUsuario(entity.getNombreUsuario())
                    .correoUsuario(entity.getCorreoUsuario())
                    .idRol(entity.getIdRol())
                    .fotoUrl(entity.getFotoUrl())
                    .build();
        } else {
            return LoginResponseDto.builder()
                    .successful(false)
                    .message("Usuario o contraseña incorrectos")
                    .build();
        }
    }

    public UsuariosEntity dtoToEntity(UsuariosCreateRequestDto dto){
        return UsuariosEntity.builder()
                .nombreUsuario(dto.getNombreUsuario())
                .cargoUsuario(dto.getCargoUsuario())
                .correoUsuario(dto.getCorreoUsuario())
                .contrasena(dto.getContrasena())
                .estadoUsuario(dto.getEstadoUsuario())
                .idRol(dto.getIdRol())
                .fotoUrl(dto.getFotoUrl())
                .ultimoIngreso(dto.getUltimoIngreso())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .build();
    }

    public UsuariosResponseDto entityToDto(UsuariosEntity entity){
        return UsuariosResponseDto.builder()
                .idUsuario(entity.getIdUsuario())
                .nombreUsuario(entity.getNombreUsuario())
                .cargoUsuario(entity.getCargoUsuario())
                .correoUsuario(entity.getCorreoUsuario())
                .contrasena(entity.getContrasena())
                .estadoUsuario(entity.getEstadoUsuario())
                .idRol(entity.getIdRol())
                .fotoUrl(entity.getFotoUrl())
                .ultimoIngreso(entity.getUltimoIngreso())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .build();
    }
}
