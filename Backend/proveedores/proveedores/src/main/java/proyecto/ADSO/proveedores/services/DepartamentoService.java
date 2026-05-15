package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.DepartamentoEntity;
import proyecto.ADSO.proveedores.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    public boolean create(DepartamentoCreateRequestDto dto){
        DepartamentoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<DepartamentoResponseDto> getAll(Integer idPais){
        List<DepartamentoEntity> entities;
        if (idPais != null) {
            entities = this.repository.findByIdPais(idPais);
        } else {
            entities = this.repository.findAll();
        }
        
        List<DepartamentoResponseDto> dtos = new ArrayList<>();
        for (DepartamentoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public DepartamentoResponseDto getDetail(Integer id){
        DepartamentoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, DepartamentoCreateRequestDto dto) {
        DepartamentoEntity entity = validateIfExist(id);
        DepartamentoEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setNombre(newEntity.getNombre());
        entity.setIdPais(newEntity.getIdPais());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        DepartamentoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public DepartamentoEntity validateIfExist(Integer id){
        Optional<DepartamentoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public DepartamentoEntity dtoToEntity(DepartamentoCreateRequestDto dto){
        return DepartamentoEntity.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .idPais(dto.getIdPais())
                .activo(dto.getActivo())
                .build();
    }

    public DepartamentoResponseDto entityToDto(DepartamentoEntity entity){
        return DepartamentoResponseDto.builder()
                .idDepartamento(entity.getIdDepartamento())
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .idPais(entity.getIdPais())
                .activo(entity.getActivo())
                .build();
    }
}
