package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.TipoNotificacionEntity;
import proyecto.ADSO.proveedores.repositories.TipoNotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoNotificacionService {

    @Autowired
    private TipoNotificacionRepository repository;

    public boolean create(TipoNotificacionCreateRequestDto dto){
        TipoNotificacionEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<TipoNotificacionResponseDto> getAll(){
        List<TipoNotificacionEntity> entities = this.repository.findAll();
        List<TipoNotificacionResponseDto> dtos = new ArrayList<>();
        for (TipoNotificacionEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public TipoNotificacionResponseDto getDetail(Integer id){
        TipoNotificacionEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, TipoNotificacionCreateRequestDto dto) {
        TipoNotificacionEntity entity = validateIfExist(id);
        TipoNotificacionEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        TipoNotificacionEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public TipoNotificacionEntity validateIfExist(Integer id){
        Optional<TipoNotificacionEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public TipoNotificacionEntity dtoToEntity(TipoNotificacionCreateRequestDto dto){
        return TipoNotificacionEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public TipoNotificacionResponseDto entityToDto(TipoNotificacionEntity entity){
        return TipoNotificacionResponseDto.builder()
                .idTipoNotificacion(entity.getIdTipoNotificacion())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
