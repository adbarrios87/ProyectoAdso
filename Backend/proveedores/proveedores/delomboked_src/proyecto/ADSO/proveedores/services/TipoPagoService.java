package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.TipoPagoEntity;
import proyecto.ADSO.proveedores.repositories.TipoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoPagoService {

    @Autowired
    private TipoPagoRepository repository;

    public boolean create(TipoPagoCreateRequestDto dto){
        TipoPagoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<TipoPagoResponseDto> getAll(){
        List<TipoPagoEntity> entities = this.repository.findAll();
        List<TipoPagoResponseDto> dtos = new ArrayList<>();
        for (TipoPagoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public TipoPagoResponseDto getDetail(Integer id){
        TipoPagoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, TipoPagoCreateRequestDto dto) {
        TipoPagoEntity entity = validateIfExist(id);
        TipoPagoEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        TipoPagoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public TipoPagoEntity validateIfExist(Integer id){
        Optional<TipoPagoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public TipoPagoEntity dtoToEntity(TipoPagoCreateRequestDto dto){
        return TipoPagoEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public TipoPagoResponseDto entityToDto(TipoPagoEntity entity){
        return TipoPagoResponseDto.builder()
                .idTipoPago(entity.getIdTipoPago())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
