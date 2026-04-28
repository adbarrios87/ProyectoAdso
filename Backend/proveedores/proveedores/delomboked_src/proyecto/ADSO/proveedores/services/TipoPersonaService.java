package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.TipoPersonaEntity;
import proyecto.ADSO.proveedores.repositories.TipoPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoPersonaService {

    @Autowired
    private TipoPersonaRepository repository;

    public boolean create(TipoPersonaCreateRequestDto dto){
        TipoPersonaEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<TipoPersonaResponseDto> getAll(){
        List<TipoPersonaEntity> entities = this.repository.findAll();
        List<TipoPersonaResponseDto> dtos = new ArrayList<>();
        for (TipoPersonaEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public TipoPersonaResponseDto getDetail(Integer id){
        TipoPersonaEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, TipoPersonaCreateRequestDto dto) {
        TipoPersonaEntity entity = validateIfExist(id);
        TipoPersonaEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        TipoPersonaEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public TipoPersonaEntity validateIfExist(Integer id){
        Optional<TipoPersonaEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public TipoPersonaEntity dtoToEntity(TipoPersonaCreateRequestDto dto){
        return TipoPersonaEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public TipoPersonaResponseDto entityToDto(TipoPersonaEntity entity){
        return TipoPersonaResponseDto.builder()
                .idTipoPersona(entity.getIdTipoPersona())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
