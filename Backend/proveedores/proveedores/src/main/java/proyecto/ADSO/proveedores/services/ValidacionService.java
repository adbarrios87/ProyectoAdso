package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.ValidacionEntity;
import proyecto.ADSO.proveedores.repositories.ValidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidacionService {

    @Autowired
    private ValidacionRepository repository;

    public boolean create(ValidacionCreateRequestDto dto){
        ValidacionEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ValidacionResponseDto> getAll(){
        List<ValidacionEntity> entities = this.repository.findAll();
        List<ValidacionResponseDto> dtos = new ArrayList<>();
        for (ValidacionEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ValidacionResponseDto getDetail(Integer id){
        ValidacionEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, ValidacionCreateRequestDto dto) {
        ValidacionEntity entity = validateIfExist(id);
        ValidacionEntity newEntity = dtoToEntity(dto);
        entity.setIdUsuario(newEntity.getIdUsuario());
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdCampoValidacion(newEntity.getIdCampoValidacion());
        entity.setValorWeb(newEntity.getValorWeb());
        entity.setValorDocumento(newEntity.getValorDocumento());
        entity.setIdDocumento(newEntity.getIdDocumento());
        entity.setResultadoValidacion(newEntity.getResultadoValidacion());
        entity.setFechaValidacion(newEntity.getFechaValidacion());
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
        ValidacionEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ValidacionEntity validateIfExist(Integer id){
        Optional<ValidacionEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ValidacionEntity dtoToEntity(ValidacionCreateRequestDto dto){
        return ValidacionEntity.builder()
                .idUsuario(dto.getIdUsuario())
                .idProveedor(dto.getIdProveedor())
                .idCampoValidacion(dto.getIdCampoValidacion())
                .valorWeb(dto.getValorWeb())
                .valorDocumento(dto.getValorDocumento())
                .idDocumento(dto.getIdDocumento())
                .resultadoValidacion(dto.getResultadoValidacion())
                .fechaValidacion(dto.getFechaValidacion())
                .comentarios(dto.getComentarios())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public ValidacionResponseDto entityToDto(ValidacionEntity entity){
        return ValidacionResponseDto.builder()
                .idValidacion(entity.getIdValidacion())
                .idUsuario(entity.getIdUsuario())
                .idProveedor(entity.getIdProveedor())
                .idCampoValidacion(entity.getIdCampoValidacion())
                .valorWeb(entity.getValorWeb())
                .valorDocumento(entity.getValorDocumento())
                .idDocumento(entity.getIdDocumento())
                .resultadoValidacion(entity.getResultadoValidacion())
                .fechaValidacion(entity.getFechaValidacion())
                .comentarios(entity.getComentarios())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
