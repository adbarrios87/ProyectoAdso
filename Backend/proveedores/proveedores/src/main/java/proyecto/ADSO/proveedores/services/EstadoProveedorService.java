package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.EstadoProveedorEntity;
import proyecto.ADSO.proveedores.repositories.EstadoProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstadoProveedorService {

    @Autowired
    private EstadoProveedorRepository repository;

    public boolean create(EstadoProveedorCreateRequestDto dto){
        EstadoProveedorEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<EstadoProveedorResponseDto> getAll(){
        List<EstadoProveedorEntity> entities = this.repository.findAll();
        List<EstadoProveedorResponseDto> dtos = new ArrayList<>();
        for (EstadoProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public EstadoProveedorResponseDto getDetail(Integer id){
        EstadoProveedorEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, EstadoProveedorCreateRequestDto dto) {
        EstadoProveedorEntity entity = validateIfExist(id);
        EstadoProveedorEntity newEntity = dtoToEntity(dto);
        entity.setEstado(newEntity.getEstado());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        EstadoProveedorEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public EstadoProveedorEntity validateIfExist(Integer id){
        Optional<EstadoProveedorEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public EstadoProveedorEntity dtoToEntity(EstadoProveedorCreateRequestDto dto){
        return EstadoProveedorEntity.builder()
                .estado(dto.getEstado())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public EstadoProveedorResponseDto entityToDto(EstadoProveedorEntity entity){
        return EstadoProveedorResponseDto.builder()
                .idEstadoProveedor(entity.getIdEstadoProveedor())
                .estado(entity.getEstado())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
