package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.MunicipioEntity;
import proyecto.ADSO.proveedores.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;

    public boolean create(MunicipioCreateRequestDto dto){
        MunicipioEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<MunicipioResponseDto> getAll(){
        List<MunicipioEntity> entities = this.repository.findAll();
        List<MunicipioResponseDto> dtos = new ArrayList<>();
        for (MunicipioEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public MunicipioResponseDto getDetail(Integer id){
        MunicipioEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, MunicipioCreateRequestDto dto) {
        MunicipioEntity entity = validateIfExist(id);
        MunicipioEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setNombre(newEntity.getNombre());
        entity.setIdDepartamento(newEntity.getIdDepartamento());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        MunicipioEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public MunicipioEntity validateIfExist(Integer id){
        Optional<MunicipioEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public MunicipioEntity dtoToEntity(MunicipioCreateRequestDto dto){
        return MunicipioEntity.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .idDepartamento(dto.getIdDepartamento())
                .activo(dto.getActivo())
                .build();
    }

    public MunicipioResponseDto entityToDto(MunicipioEntity entity){
        return MunicipioResponseDto.builder()
                .idMunicipio(entity.getIdMunicipio())
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .idDepartamento(entity.getIdDepartamento())
                .activo(entity.getActivo())
                .build();
    }
}
