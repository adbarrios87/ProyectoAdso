package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.ProveedorContactoEntity;
import proyecto.ADSO.proveedores.repositories.ProveedorContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProveedorContactoService {

    @Autowired
    private ProveedorContactoRepository repository;

    public boolean create(ProveedorContactoCreateRequestDto dto){
        ProveedorContactoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ProveedorContactoResponseDto> getAll(){
        List<ProveedorContactoEntity> entities = this.repository.findAll();
        List<ProveedorContactoResponseDto> dtos = new ArrayList<>();
        for (ProveedorContactoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ProveedorContactoResponseDto getDetail(Integer id){
        ProveedorContactoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, ProveedorContactoCreateRequestDto dto) {
        ProveedorContactoEntity entity = validateIfExist(id);
        ProveedorContactoEntity newEntity = dtoToEntity(dto);
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdContacto(newEntity.getIdContacto());
        entity.setEstadoContacto(newEntity.getEstadoContacto());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        ProveedorContactoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ProveedorContactoEntity validateIfExist(Integer id){
        Optional<ProveedorContactoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ProveedorContactoEntity dtoToEntity(ProveedorContactoCreateRequestDto dto){
        return ProveedorContactoEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .idContacto(dto.getIdContacto())
                .estadoContacto(dto.getEstadoContacto())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public ProveedorContactoResponseDto entityToDto(ProveedorContactoEntity entity){
        return ProveedorContactoResponseDto.builder()
                .idProveedor(entity.getIdProveedor())
                .idContacto(entity.getIdContacto())
                .estadoContacto(entity.getEstadoContacto())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
