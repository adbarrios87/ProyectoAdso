package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.TipoDocumentoEntity;
import proyecto.ADSO.proveedores.repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository repository;

    public boolean create(TipoDocumentoCreateRequestDto dto){
        TipoDocumentoEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<TipoDocumentoResponseDto> getAll(){
        List<TipoDocumentoEntity> entities = this.repository.findAll();
        List<TipoDocumentoResponseDto> dtos = new ArrayList<>();
        for (TipoDocumentoEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public TipoDocumentoResponseDto getDetail(Integer id){
        TipoDocumentoEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, TipoDocumentoCreateRequestDto dto) {
        TipoDocumentoEntity entity = validateIfExist(id);
        TipoDocumentoEntity newEntity = dtoToEntity(dto);
        entity.setCodigo(newEntity.getCodigo());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        TipoDocumentoEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public TipoDocumentoEntity validateIfExist(Integer id){
        Optional<TipoDocumentoEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public TipoDocumentoEntity dtoToEntity(TipoDocumentoCreateRequestDto dto){
        return TipoDocumentoEntity.builder()
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .build();
    }

    public TipoDocumentoResponseDto entityToDto(TipoDocumentoEntity entity){
        return TipoDocumentoResponseDto.builder()
                .idTipoDocumento(entity.getIdTipoDocumento())
                .codigo(entity.getCodigo())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
