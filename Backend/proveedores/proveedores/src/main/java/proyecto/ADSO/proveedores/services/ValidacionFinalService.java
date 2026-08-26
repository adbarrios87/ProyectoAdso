package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.ValidacionFinalCreateRequestDto;
import proyecto.ADSO.proveedores.entites.ValidacionEntity;
import proyecto.ADSO.proveedores.entites.ValidacionFinalEntity;
import proyecto.ADSO.proveedores.entites.ProveedorEntity;
import proyecto.ADSO.proveedores.repositories.ValidacionFinalRepository;
import proyecto.ADSO.proveedores.repositories.ValidacionRepository;
import proyecto.ADSO.proveedores.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ValidacionFinalService {

    @Autowired
    private ValidacionFinalRepository repository;

    @Autowired
    private ValidacionRepository validacionRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Transactional
    public boolean create(ValidacionFinalCreateRequestDto dto) {
        // Find atomic validations
        List<ValidacionEntity> atomicValidations = validacionRepository.findAllById(dto.getValidationIds());

        // Create ValidacionFinalEntity
        ValidacionFinalEntity entity = ValidacionFinalEntity.builder()
                .idProveedor(dto.getIdProveedor())
                .comentarioFinal(dto.getComentarioFinal())
                .estadoValidacion(dto.getEstadoValidacion())
                .creadoPor(dto.getCreadoPor())
                .fechaCreado(LocalDateTime.now())
                .activo(true)
                .validaciones(new HashSet<>(atomicValidations))
                .build();

        entity = repository.save(entity);

        // Update each atomic validation individually with the new foreign key
        for (ValidacionEntity v : atomicValidations) {
            v.setIdValidacionFinal(entity.getIdValidacionFinal());
            validacionRepository.save(v);
        }

        // Update provider status dynamically based on the received state
        Optional<ProveedorEntity> optProv = proveedorRepository.findById(dto.getIdProveedor());
        if (optProv.isPresent()) {
            ProveedorEntity prov = optProv.get();
            try {
                int stateId = Integer.parseInt(dto.getEstadoValidacion());
                prov.setIdEstadoProveedor(stateId);
                proveedorRepository.save(prov);
            } catch (NumberFormatException e) {
                System.err.println("Invalid state ID received in ValidacionFinalService: " + dto.getEstadoValidacion());
            }
        }

        return true;
    }

    public List<ValidacionFinalEntity> getAll() {
        return this.repository.findAll();
    }
}
