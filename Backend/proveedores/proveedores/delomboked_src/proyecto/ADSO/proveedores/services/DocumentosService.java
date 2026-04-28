package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.DocumentosEntity;
import proyecto.ADSO.proveedores.repositories.DocumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocumentosService {

    @Autowired
    private DocumentosRepository repository;

    public boolean create(DocumentosCreateRequestDto dto){
        DocumentosEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<DocumentosResponseDto> getAll(){
        List<DocumentosEntity> entities = this.repository.findAll();
        List<DocumentosResponseDto> dtos = new ArrayList<>();
        for (DocumentosEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public DocumentosResponseDto getDetail(Integer id){
        DocumentosEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, DocumentosCreateRequestDto dto) {
        DocumentosEntity entity = validateIfExist(id);
        DocumentosEntity newEntity = dtoToEntity(dto);
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdTipoDocumento(newEntity.getIdTipoDocumento());
        entity.setNumeroDocumento(newEntity.getNumeroDocumento());
        entity.setFechaEmision(newEntity.getFechaEmision());
        entity.setUrlDocumento(newEntity.getUrlDocumento());
        entity.setAlmacenamiento(newEntity.getAlmacenamiento());
        entity.setArchivoBlob(newEntity.getArchivoBlob());
        entity.setHash(newEntity.getHash());
        entity.setTamanoBytes(newEntity.getTamanoBytes());
        entity.setFechaCarga(newEntity.getFechaCarga());
        entity.setEstadoDocumento(newEntity.getEstadoDocumento());
        entity.setValidado(newEntity.getValidado());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        DocumentosEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public DocumentosEntity validateIfExist(Integer id){
        Optional<DocumentosEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public DocumentosEntity dtoToEntity(DocumentosCreateRequestDto dto){
        return DocumentosEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .idTipoDocumento(dto.getIdTipoDocumento())
                .numeroDocumento(dto.getNumeroDocumento())
                .fechaEmision(dto.getFechaEmision())
                .urlDocumento(dto.getUrlDocumento())
                .almacenamiento(dto.getAlmacenamiento())
                .archivoBlob(dto.getArchivoBlob())
                .hash(dto.getHash())
                .tamanoBytes(dto.getTamanoBytes())
                .fechaCarga(dto.getFechaCarga())
                .estadoDocumento(dto.getEstadoDocumento())
                .validado(dto.getValidado())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .build();
    }

    public DocumentosResponseDto entityToDto(DocumentosEntity entity){
        return DocumentosResponseDto.builder()
                .idDocumento(entity.getIdDocumento())
                .idProveedor(entity.getIdProveedor())
                .idTipoDocumento(entity.getIdTipoDocumento())
                .numeroDocumento(entity.getNumeroDocumento())
                .fechaEmision(entity.getFechaEmision())
                .urlDocumento(entity.getUrlDocumento())
                .almacenamiento(entity.getAlmacenamiento())
                .archivoBlob(entity.getArchivoBlob())
                .hash(entity.getHash())
                .tamanoBytes(entity.getTamanoBytes())
                .fechaCarga(entity.getFechaCarga())
                .estadoDocumento(entity.getEstadoDocumento())
                .validado(entity.getValidado())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .build();
    }
}
