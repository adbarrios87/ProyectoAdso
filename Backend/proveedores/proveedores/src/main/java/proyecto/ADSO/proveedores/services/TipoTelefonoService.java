package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.TipoTelefonoEntity;
import proyecto.ADSO.proveedores.repositories.TipoTelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoTelefonoService {

    @Autowired
    private TipoTelefonoRepository repository;

    public boolean create(TipoTelefonoCreateRequestDto dto){
        TipoTelefonoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<TipoTelefonoResponseDto> getAll(){
        List<TipoTelefonoEntity> entities = this.repository.findAll();
        List<TipoTelefonoResponseDto> dtos = new ArrayList<>();
        for (TipoTelefonoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public TipoTelefonoResponseDto getDetail(Integer id){
        TipoTelefonoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, TipoTelefonoCreateRequestDto dto) {
        TipoTelefonoEntity entity = validateIfExist(id);
        TipoTelefonoEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        TipoTelefonoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public TipoTelefonoEntity validateIfExist(Integer id){
        Optional<TipoTelefonoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public TipoTelefonoEntity dtoToEntity(TipoTelefonoCreateRequestDto dto){
        return TipoTelefonoEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public TipoTelefonoResponseDto entityToDto(TipoTelefonoEntity entity){
        return TipoTelefonoResponseDto.builder()
                .idTipoTelefono(entity.getIdTipoTelefono())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
