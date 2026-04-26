package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.FormaDePagoEntity;
import proyecto.ADSO.proveedores.repositories.FormaDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FormaDePagoService {

    @Autowired
    private FormaDePagoRepository repository;

    public boolean create(FormaDePagoCreateRequestDto dto){
        FormaDePagoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<FormaDePagoResponseDto> getAll(){
        List<FormaDePagoEntity> entities = this.repository.findAll();
        List<FormaDePagoResponseDto> dtos = new ArrayList<>();
        for (FormaDePagoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public FormaDePagoResponseDto getDetail(Integer id){
        FormaDePagoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, FormaDePagoCreateRequestDto dto) {
        FormaDePagoEntity entity = validateIfExist(id);
        FormaDePagoEntity newEntity = dtoToEntity(dto);
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdTipoPago(newEntity.getIdTipoPago());
        entity.setMonto(newEntity.getMonto());
        entity.setPlazo(newEntity.getPlazo());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        FormaDePagoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public FormaDePagoEntity validateIfExist(Integer id){
        Optional<FormaDePagoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public FormaDePagoEntity dtoToEntity(FormaDePagoCreateRequestDto dto){
        return FormaDePagoEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .idTipoPago(dto.getIdTipoPago())
                .monto(dto.getMonto())
                .plazo(dto.getPlazo())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public FormaDePagoResponseDto entityToDto(FormaDePagoEntity entity){
        return FormaDePagoResponseDto.builder()
                .idFormaPago(entity.getIdFormaPago())
                .idProveedor(entity.getIdProveedor())
                .idTipoPago(entity.getIdTipoPago())
                .monto(entity.getMonto())
                .plazo(entity.getPlazo())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
