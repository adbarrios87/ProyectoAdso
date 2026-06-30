package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.TipoIdentificacionEntity;
import proyecto.ADSO.proveedores.repositories.TipoIdentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoIdentificacionService {

    @Autowired
    private TipoIdentificacionRepository repository;

    public boolean create(TipoIdentificacionCreateRequestDto dto){
        TipoIdentificacionEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<TipoIdentificacionResponseDto> getAll(){
        List<TipoIdentificacionEntity> entities = this.repository.findAll();
        List<TipoIdentificacionResponseDto> dtos = new ArrayList<>();
        for (TipoIdentificacionEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public TipoIdentificacionResponseDto getDetail(Integer id){
        TipoIdentificacionEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, TipoIdentificacionCreateRequestDto dto) {
        TipoIdentificacionEntity entity = validateIfExist(id);
        TipoIdentificacionEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        TipoIdentificacionEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public TipoIdentificacionEntity validateIfExist(Integer id){
        Optional<TipoIdentificacionEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public TipoIdentificacionEntity dtoToEntity(TipoIdentificacionCreateRequestDto dto){
        return TipoIdentificacionEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public TipoIdentificacionResponseDto entityToDto(TipoIdentificacionEntity entity){
        return TipoIdentificacionResponseDto.builder()
                .idTipoIdentificacion(entity.getIdTipoIdentificacion())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
