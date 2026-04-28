package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.ProveedoresEntity;
import proyecto.ADSO.proveedores.repositories.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProveedoresService {

    @Autowired
    private ProveedoresRepository repository;

    public boolean create(ProveedoresCreateRequestDto dto){
        ProveedoresEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ProveedoresResponseDto> getAll(){
        List<ProveedoresEntity> entities = this.repository.findAll();
        List<ProveedoresResponseDto> dtos = new ArrayList<>();
        for (ProveedoresEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ProveedoresResponseDto getDetail(Integer id){
        ProveedoresEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, ProveedoresCreateRequestDto dto) {
        ProveedoresEntity entity = validateIfExist(id);
        ProveedoresEntity newEntity = dtoToEntity(dto);
        entity.setIdTipoIdentificacion(newEntity.getIdTipoIdentificacion());
        entity.setNumeroIdentificacion(newEntity.getNumeroIdentificacion());
        entity.setDigitoVerificacion(newEntity.getDigitoVerificacion());
        entity.setRazonSocial(newEntity.getRazonSocial());
        entity.setNombres(newEntity.getNombres());
        entity.setApellidos(newEntity.getApellidos());
        entity.setIdTipoPersona(newEntity.getIdTipoPersona());
        entity.setTelefonoPrincipal(newEntity.getTelefonoPrincipal());
        entity.setIdTipoTelefono(newEntity.getIdTipoTelefono());
        entity.setCorreoPrincipal(newEntity.getCorreoPrincipal());
        entity.setIdActualizacionProveedor(newEntity.getIdActualizacionProveedor());
        entity.setRequiereActualizacion(newEntity.getRequiereActualizacion());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        ProveedoresEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ProveedoresEntity validateIfExist(Integer id){
        Optional<ProveedoresEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ProveedoresEntity dtoToEntity(ProveedoresCreateRequestDto dto){
        return ProveedoresEntity.builder()
                .idTipoIdentificacion(dto.getIdTipoIdentificacion())
                .numeroIdentificacion(dto.getNumeroIdentificacion())
                .digitoVerificacion(dto.getDigitoVerificacion())
                .razonSocial(dto.getRazonSocial())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .idTipoPersona(dto.getIdTipoPersona())
                .telefonoPrincipal(dto.getTelefonoPrincipal())
                .idTipoTelefono(dto.getIdTipoTelefono())
                .correoPrincipal(dto.getCorreoPrincipal())
                .idActualizacionProveedor(dto.getIdActualizacionProveedor())
                .requiereActualizacion(dto.getRequiereActualizacion())
                .descripcion(dto.getDescripcion())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public ProveedoresResponseDto entityToDto(ProveedoresEntity entity){
        return ProveedoresResponseDto.builder()
                .idProveedor(entity.getIdProveedor())
                .idTipoIdentificacion(entity.getIdTipoIdentificacion())
                .numeroIdentificacion(entity.getNumeroIdentificacion())
                .digitoVerificacion(entity.getDigitoVerificacion())
                .razonSocial(entity.getRazonSocial())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .idTipoPersona(entity.getIdTipoPersona())
                .telefonoPrincipal(entity.getTelefonoPrincipal())
                .idTipoTelefono(entity.getIdTipoTelefono())
                .correoPrincipal(entity.getCorreoPrincipal())
                .idActualizacionProveedor(entity.getIdActualizacionProveedor())
                .requiereActualizacion(entity.getRequiereActualizacion())
                .descripcion(entity.getDescripcion())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
