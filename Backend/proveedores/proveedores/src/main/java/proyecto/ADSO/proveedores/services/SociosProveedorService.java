package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.SociosProveedorEntity;
import proyecto.ADSO.proveedores.repositories.SociosProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SociosProveedorService {

    @Autowired
    private SociosProveedorRepository repository;

    public boolean create(SociosProveedorCreateRequestDto dto){
        SociosProveedorEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<SociosProveedorResponseDto> getAll(){
        List<SociosProveedorEntity> entities = this.repository.findAll();
        List<SociosProveedorResponseDto> dtos = new ArrayList<>();
        for (SociosProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public SociosProveedorResponseDto getDetail(Integer id){
        SociosProveedorEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, SociosProveedorCreateRequestDto dto) {
        SociosProveedorEntity entity = validateIfExist(id);
        SociosProveedorEntity newEntity = dtoToEntity(dto);
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setNombres(newEntity.getNombres());
        entity.setApellidos(newEntity.getApellidos());
        entity.setIdTipoIdentificacion(newEntity.getIdTipoIdentificacion());
        entity.setNumeroIdentificacion(newEntity.getNumeroIdentificacion());
        entity.setConsulta(newEntity.getConsulta());
        entity.setFechaConsulta(newEntity.getFechaConsulta());
        entity.setResultadoConsulta(newEntity.getResultadoConsulta());
        entity.setIdOrigen(newEntity.getIdOrigen());
        entity.setFechaExtraccion(newEntity.getFechaExtraccion());
        entity.setValidado(newEntity.getValidado());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        SociosProveedorEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public SociosProveedorEntity validateIfExist(Integer id){
        Optional<SociosProveedorEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public SociosProveedorEntity dtoToEntity(SociosProveedorCreateRequestDto dto){
        return SociosProveedorEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .idTipoIdentificacion(dto.getIdTipoIdentificacion())
                .numeroIdentificacion(dto.getNumeroIdentificacion())
                .consulta(dto.getConsulta())
                .fechaConsulta(dto.getFechaConsulta())
                .resultadoConsulta(dto.getResultadoConsulta())
                .idOrigen(dto.getIdOrigen())
                .fechaExtraccion(dto.getFechaExtraccion())
                .validado(dto.getValidado())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public SociosProveedorResponseDto entityToDto(SociosProveedorEntity entity){
        return SociosProveedorResponseDto.builder()
                .idSocioProveedor(entity.getIdSocioProveedor())
                .idProveedor(entity.getIdProveedor())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .idTipoIdentificacion(entity.getIdTipoIdentificacion())
                .numeroIdentificacion(entity.getNumeroIdentificacion())
                .consulta(entity.getConsulta())
                .fechaConsulta(entity.getFechaConsulta())
                .resultadoConsulta(entity.getResultadoConsulta())
                .idOrigen(entity.getIdOrigen())
                .fechaExtraccion(entity.getFechaExtraccion())
                .validado(entity.getValidado())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
