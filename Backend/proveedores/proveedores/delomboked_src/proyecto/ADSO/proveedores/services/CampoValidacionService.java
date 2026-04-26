package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.CampoValidacionEntity;
import proyecto.ADSO.proveedores.repositories.CampoValidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampoValidacionService {

    @Autowired
    private CampoValidacionRepository repository;

    public boolean create(CampoValidacionCreateRequestDto dto){
        CampoValidacionEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<CampoValidacionResponseDto> getAll(){
        List<CampoValidacionEntity> entities = this.repository.findAll();
        List<CampoValidacionResponseDto> dtos = new ArrayList<>();
        for (CampoValidacionEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public CampoValidacionResponseDto getDetail(Integer id){
        CampoValidacionEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, CampoValidacionCreateRequestDto dto) {
        CampoValidacionEntity entity = validateIfExist(id);
        CampoValidacionEntity newEntity = dtoToEntity(dto);
        entity.setIdTipoDocumento(newEntity.getIdTipoDocumento());
        entity.setCampo(newEntity.getCampo());
        entity.setObligatorio(newEntity.getObligatorio());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        CampoValidacionEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public CampoValidacionEntity validateIfExist(Integer id){
        Optional<CampoValidacionEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public CampoValidacionEntity dtoToEntity(CampoValidacionCreateRequestDto dto){
        return CampoValidacionEntity.builder()
                .idTipoDocumento(dto.getIdTipoDocumento())
                .campo(dto.getCampo())
                .obligatorio(dto.getObligatorio())
                .activo(dto.getActivo())
                .build();
    }

    public CampoValidacionResponseDto entityToDto(CampoValidacionEntity entity){
        return CampoValidacionResponseDto.builder()
                .idCampoValidacion(entity.getIdCampoValidacion())
                .idTipoDocumento(entity.getIdTipoDocumento())
                .campo(entity.getCampo())
                .obligatorio(entity.getObligatorio())
                .activo(entity.getActivo())
                .build();
    }
}
