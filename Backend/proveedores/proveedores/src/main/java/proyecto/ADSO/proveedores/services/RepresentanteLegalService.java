package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.RepresentanteLegalEntity;
import proyecto.ADSO.proveedores.repositories.RepresentanteLegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RepresentanteLegalService {

    @Autowired
    private RepresentanteLegalRepository repository;

    public boolean create(RepresentanteLegalCreateRequestDto dto){
        RepresentanteLegalEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<RepresentanteLegalResponseDto> getAll(){
        List<RepresentanteLegalEntity> entities = this.repository.findAll();
        List<RepresentanteLegalResponseDto> dtos = new ArrayList<>();
        for (RepresentanteLegalEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public RepresentanteLegalResponseDto getDetail(Integer id){
        RepresentanteLegalEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, RepresentanteLegalCreateRequestDto dto) {
        RepresentanteLegalEntity entity = validateIfExist(id);
        RepresentanteLegalEntity newEntity = dtoToEntity(dto);
        entity.setIdTipoIdentificacion(newEntity.getIdTipoIdentificacion());
        entity.setIdDocumento(newEntity.getIdDocumento());
        entity.setNumeroIdentificacion(newEntity.getNumeroIdentificacion());
        entity.setNombres(newEntity.getNombres());
        entity.setApellidos(newEntity.getApellidos());
        entity.setIdTipoTelefono(newEntity.getIdTipoTelefono());
        entity.setTelefono(newEntity.getTelefono());
        entity.setCorreo(newEntity.getCorreo());
        entity.setIdUsuario(newEntity.getIdUsuario());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        RepresentanteLegalEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public RepresentanteLegalEntity validateIfExist(Integer id){
        Optional<RepresentanteLegalEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public RepresentanteLegalEntity dtoToEntity(RepresentanteLegalCreateRequestDto dto){
        return RepresentanteLegalEntity.builder()
                .idTipoIdentificacion(dto.getIdTipoIdentificacion())
                .idDocumento(dto.getIdDocumento())
                .numeroIdentificacion(dto.getNumeroIdentificacion())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .idTipoTelefono(dto.getIdTipoTelefono())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .idUsuario(dto.getIdUsuario())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public RepresentanteLegalResponseDto entityToDto(RepresentanteLegalEntity entity){
        return RepresentanteLegalResponseDto.builder()
                .idRepresentanteLegal(entity.getIdRepresentanteLegal())
                .idTipoIdentificacion(entity.getIdTipoIdentificacion())
                .idDocumento(entity.getIdDocumento())
                .numeroIdentificacion(entity.getNumeroIdentificacion())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .idTipoTelefono(entity.getIdTipoTelefono())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .idUsuario(entity.getIdUsuario())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
