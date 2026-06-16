package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.DocumentosEntity;
import proyecto.ADSO.proveedores.repositories.DocumentosRepository;
import proyecto.ADSO.proveedores.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class DocumentosService {

    @Autowired
    private DocumentosRepository repository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<DocumentosResponseDto> getByIdProveedor(Integer idProveedor) {
        List<DocumentosEntity> entities = this.repository.findByIdProveedor(idProveedor);
        List<DocumentosResponseDto> dtos = new ArrayList<>();
        for (DocumentosEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public DocumentosResponseDto uploadDocumento(MultipartFile file, Integer idProveedor, Integer idTipoDocumento, Integer creadoPor) throws IOException {
        Optional<proyecto.ADSO.proveedores.entites.ProveedorEntity> optProv = proveedorRepository.findById(idProveedor);
        if (optProv.isEmpty()) {
            throw new RuntimeException("Proveedor no encontrado con ID: " + idProveedor);
        }
        proyecto.ADSO.proveedores.entites.ProveedorEntity proveedor = optProv.get();
        
        String proveedorName = "";
        if (proveedor.getRazonSocial() != null && !proveedor.getRazonSocial().trim().isEmpty()) {
            proveedorName = proveedor.getRazonSocial().trim();
        } else {
            String nom = proveedor.getNombres() != null ? proveedor.getNombres().trim() : "";
            String ape = proveedor.getApellidos() != null ? proveedor.getApellidos().trim() : "";
            proveedorName = (nom + " " + ape).trim();
        }
        if (proveedorName.isEmpty()) {
            proveedorName = proveedor.getNumeroIdentificacion();
        }
        
        int year = java.time.Year.now().getValue();
        String folderName = proveedorName.replaceAll("[\\\\/:*?\"<>|]", "_") + "_" + year;
        
        String baseDir = "G:\\My Drive\\0. SENA - ADSO\\DocumentosProyecto";
        File dir = new File(baseDir, folderName);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                throw new IOException("No se pudo crear la carpeta física en: " + dir.getAbsolutePath() + ". Verifique que la unidad G: esté conectada y con permisos de escritura.");
            }
        }
        
        File targetFile = new File(dir, file.getOriginalFilename());
        file.transferTo(targetFile);
        
        Optional<DocumentosEntity> existingOpt = repository.findFirstByIdProveedorAndIdTipoDocumentoAndEstadoDocumentoIsTrue(idProveedor, idTipoDocumento);
        DocumentosEntity doc;
        if (existingOpt.isPresent()) {
            doc = existingOpt.get();
            doc.setUrlDocumento(targetFile.getAbsolutePath());
            doc.setTamanoBytes(file.getSize());
            doc.setFechaCarga(java.time.LocalDate.now());
            doc.setFechaModificado(java.time.LocalDateTime.now());
            doc.setModificadoPor(creadoPor);
        } else {
            doc = DocumentosEntity.builder()
                    .idProveedor(idProveedor)
                    .idTipoDocumento(idTipoDocumento)
                    .urlDocumento(targetFile.getAbsolutePath())
                    .almacenamiento("local")
                    .tamanoBytes(file.getSize())
                    .fechaCarga(java.time.LocalDate.now())
                    .estadoDocumento(true)
                    .validado(false)
                    .fechaCreado(java.time.LocalDateTime.now())
                    .creadoPor(creadoPor)
                    .build();
        }
        
        DocumentosEntity saved = repository.save(doc);
        return entityToDto(saved);
    }

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
