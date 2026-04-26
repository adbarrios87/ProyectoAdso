package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.EstadoUsuarioEntity;
import proyecto.ADSO.proveedores.repositories.EstadoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstadoUsuarioService {

    @Autowired
    private EstadoUsuarioRepository repository;

    public boolean create(EstadoUsuarioCreateRequestDto dto){
        EstadoUsuarioEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<EstadoUsuarioResponseDto> getAll(){
        List<EstadoUsuarioEntity> entities = this.repository.findAll();
        List<EstadoUsuarioResponseDto> dtos = new ArrayList<>();
        for (EstadoUsuarioEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public EstadoUsuarioResponseDto getDetail(Integer id){
        EstadoUsuarioEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, EstadoUsuarioCreateRequestDto dto) {
        EstadoUsuarioEntity entity = validateIfExist(id);
        EstadoUsuarioEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        EstadoUsuarioEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public EstadoUsuarioEntity validateIfExist(Integer id){
        Optional<EstadoUsuarioEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public EstadoUsuarioEntity dtoToEntity(EstadoUsuarioCreateRequestDto dto){
        return EstadoUsuarioEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public EstadoUsuarioResponseDto entityToDto(EstadoUsuarioEntity entity){
        return EstadoUsuarioResponseDto.builder()
                .idEstadoUsuario(entity.getIdEstadoUsuario())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
