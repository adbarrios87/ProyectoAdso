package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.DocumentosSociosProveedorEntity;
import proyecto.ADSO.proveedores.repositories.DocumentosSociosProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocumentosSociosProveedorService {

    @Autowired
    private DocumentosSociosProveedorRepository repository;

    public boolean create(DocumentosSociosProveedorCreateRequestDto dto){
        DocumentosSociosProveedorEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<DocumentosSociosProveedorResponseDto> getAll(){
        List<DocumentosSociosProveedorEntity> entities = this.repository.findAll();
        List<DocumentosSociosProveedorResponseDto> dtos = new ArrayList<>();
        for (DocumentosSociosProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public DocumentosSociosProveedorResponseDto getDetail(Integer id){
        DocumentosSociosProveedorEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, DocumentosSociosProveedorCreateRequestDto dto) {
        DocumentosSociosProveedorEntity entity = validateIfExist(id);
        DocumentosSociosProveedorEntity newEntity = dtoToEntity(dto);
        entity.setIdSocioProveedor(newEntity.getIdSocioProveedor());
        entity.setIdDocumento(newEntity.getIdDocumento());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        DocumentosSociosProveedorEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public DocumentosSociosProveedorEntity validateIfExist(Integer id){
        Optional<DocumentosSociosProveedorEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public DocumentosSociosProveedorEntity dtoToEntity(DocumentosSociosProveedorCreateRequestDto dto){
        return DocumentosSociosProveedorEntity.builder()
                .idSocioProveedor(dto.getIdSocioProveedor())
                .idDocumento(dto.getIdDocumento())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public DocumentosSociosProveedorResponseDto entityToDto(DocumentosSociosProveedorEntity entity){
        return DocumentosSociosProveedorResponseDto.builder()
                .idSocioProveedor(entity.getIdSocioProveedor())
                .idDocumento(entity.getIdDocumento())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
