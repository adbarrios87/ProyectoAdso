package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.UbicacionEntity;
import proyecto.ADSO.proveedores.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository repository;

    public boolean create(UbicacionCreateRequestDto dto){
        UbicacionEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<UbicacionResponseDto> getAll(){
        List<UbicacionEntity> entities = this.repository.findAll();
        List<UbicacionResponseDto> dtos = new ArrayList<>();
        for (UbicacionEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public UbicacionResponseDto getDetail(Integer id){
        UbicacionEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, UbicacionCreateRequestDto dto) {
        UbicacionEntity entity = validateIfExist(id);
        UbicacionEntity newEntity = dtoToEntity(dto);
        entity.setIdProveedor(newEntity.getIdProveedor());
        entity.setIdMunicipio(newEntity.getIdMunicipio());
        entity.setDireccion(newEntity.getDireccion());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        UbicacionEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public UbicacionEntity validateIfExist(Integer id){
        Optional<UbicacionEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public UbicacionEntity dtoToEntity(UbicacionCreateRequestDto dto){
        return UbicacionEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .idMunicipio(dto.getIdMunicipio())
                .direccion(dto.getDireccion())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public UbicacionResponseDto entityToDto(UbicacionEntity entity){
        return UbicacionResponseDto.builder()
                .idUbicacion(entity.getIdUbicacion())
                .idProveedor(entity.getIdProveedor())
                .idMunicipio(entity.getIdMunicipio())
                .direccion(entity.getDireccion())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
