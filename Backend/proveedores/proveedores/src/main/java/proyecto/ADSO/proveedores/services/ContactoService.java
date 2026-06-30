package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.ContactoEntity;
import proyecto.ADSO.proveedores.repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository repository;

    public ContactoEntity create(ContactoCreateRequestDto dto){
        ContactoEntity entity = this.dtoToEntity(dto);
        if (entity.getActivo() == null) {
            entity.setActivo(true);
        }
        if (entity.getFechaCreado() == null) {
            entity.setFechaCreado(java.time.LocalDateTime.now());
        }
        return this.repository.save(entity);
    }

    public List<ContactoResponseDto> getAll(){
        List<ContactoEntity> entities = this.repository.findAll();
        List<ContactoResponseDto> dtos = new ArrayList<>();
        for (ContactoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ContactoResponseDto getDetail(Integer id){
        ContactoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, ContactoCreateRequestDto dto) {
        ContactoEntity entity = validateIfExist(id);
        ContactoEntity newEntity = dtoToEntity(dto);
        entity.setNombreContacto(newEntity.getNombreContacto());
        entity.setCargoContacto(newEntity.getCargoContacto());
        entity.setIdTipoTelefono(newEntity.getIdTipoTelefono());
        entity.setTelefonoContacto(newEntity.getTelefonoContacto());
        entity.setCorreoContacto(newEntity.getCorreoContacto());
        
        // Conservar campos originales de creación
        if (entity.getFechaCreado() == null) {
            entity.setFechaCreado(newEntity.getFechaCreado() != null ? newEntity.getFechaCreado() : java.time.LocalDateTime.now());
        }
        if (entity.getCreadoPor() == null) {
            entity.setCreadoPor(newEntity.getCreadoPor());
        }
        
        entity.setFechaModificado(java.time.LocalDateTime.now());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        ContactoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ContactoEntity validateIfExist(Integer id){
        Optional<ContactoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ContactoEntity dtoToEntity(ContactoCreateRequestDto dto){
        return ContactoEntity.builder()
                .nombreContacto(dto.getNombreContacto())
                .cargoContacto(dto.getCargoContacto())
                .idTipoTelefono(dto.getIdTipoTelefono())
                .telefonoContacto(dto.getTelefonoContacto())
                .correoContacto(dto.getCorreoContacto())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public ContactoResponseDto entityToDto(ContactoEntity entity){
        return ContactoResponseDto.builder()
                .idContacto(entity.getIdContacto())
                .nombreContacto(entity.getNombreContacto())
                .cargoContacto(entity.getCargoContacto())
                .idTipoTelefono(entity.getIdTipoTelefono())
                .telefonoContacto(entity.getTelefonoContacto())
                .correoContacto(entity.getCorreoContacto())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
