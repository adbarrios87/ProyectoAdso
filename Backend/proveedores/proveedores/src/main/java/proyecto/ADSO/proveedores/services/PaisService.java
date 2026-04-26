package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.PaisEntity;
import proyecto.ADSO.proveedores.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    public boolean create(PaisCreateRequestDto dto){
        PaisEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<PaisResponseDto> getAll(){
        List<PaisEntity> entities = this.repository.findAll();
        List<PaisResponseDto> dtos = new ArrayList<>();
        for (PaisEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public PaisResponseDto getDetail(Integer id){
        PaisEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, PaisCreateRequestDto dto) {
        PaisEntity entity = validateIfExist(id);
        PaisEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setNombre(newEntity.getNombre());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        PaisEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public PaisEntity validateIfExist(Integer id){
        Optional<PaisEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public PaisEntity dtoToEntity(PaisCreateRequestDto dto){
        return PaisEntity.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .activo(dto.getActivo())
                .build();
    }

    public PaisResponseDto entityToDto(PaisEntity entity){
        return PaisResponseDto.builder()
                .idPais(entity.getIdPais())
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .activo(entity.getActivo())
                .build();
    }
}
