package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.*;
import proyecto.ADSO.proveedores.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProveedoresService {

    @Autowired
    private ProveedoresRepository repository;
    
    @Autowired
    private UbicacionRepository ubicacionRepository;
    
    @Autowired
    private FormaDePagoRepository formaDePagoRepository;
    
    @Autowired
    private ContactoRepository contactoRepository;
    
    @Autowired
    private ProveedorContactoRepository proveedorContactoRepository;
    
    @Autowired
    private RepresentanteLegalRepository representanteLegalRepository;
    
    @Autowired
    private RepresentanteProveedorRepository representanteProveedorRepository;
    
    @Autowired
    private SociosProveedorRepository sociosProveedorRepository;

    public boolean create(ProveedoresCreateRequestDto dto){
        ProveedoresEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ProveedoresResponseDto> getAll(){
        List<ProveedoresEntity> entities = this.repository.findAll();
        List<ProveedoresResponseDto> dtos = new ArrayList<>();
        for (ProveedoresEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ProveedoresResponseDto getDetail(Integer id){
        ProveedoresEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Integer id, ProveedoresCreateRequestDto dto) {
        ProveedoresEntity entity = validateIfExist(id);
        ProveedoresEntity newEntity = dtoToEntity(dto);
        entity.setIdTipoIdentificacion(newEntity.getIdTipoIdentificacion());
        entity.setNumeroIdentificacion(newEntity.getNumeroIdentificacion());
        entity.setDigitoVerificacion(newEntity.getDigitoVerificacion());
        entity.setRazonSocial(newEntity.getRazonSocial());
        entity.setNombres(newEntity.getNombres());
        entity.setApellidos(newEntity.getApellidos());
        entity.setIdTipoPersona(newEntity.getIdTipoPersona());
        entity.setTelefonoPrincipal(newEntity.getTelefonoPrincipal());
        entity.setIdTipoTelefono(newEntity.getIdTipoTelefono());
        entity.setCorreoPrincipal(newEntity.getCorreoPrincipal());
        entity.setIdActualizacionProveedor(newEntity.getIdActualizacionProveedor());
        entity.setRequiereActualizacion(newEntity.getRequiereActualizacion());
        entity.setDescripcion(newEntity.getDescripcion());
        entity.setFechaCreado(newEntity.getFechaCreado());
        entity.setCreadoPor(newEntity.getCreadoPor());
        entity.setFechaModificado(newEntity.getFechaModificado());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setActivo(newEntity.getActivo());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        ProveedoresEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    @org.springframework.transaction.annotation.Transactional
    public boolean registroCompleto(proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto dto) {
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        Integer idUsuario = 1; // Por defecto
        try {
            if (dto.getIdUsuarioAsignado() != null && !dto.getIdUsuarioAsignado().isEmpty()) {
                idUsuario = Integer.parseInt(dto.getIdUsuarioAsignado());
            }
        } catch(Exception e) {}

        // 1. Proveedor
        ProveedoresEntity proveedor = ProveedoresEntity.builder()
            .razonSocial(dto.getEmpresa().getNombre())
            .numeroIdentificacion(dto.getEmpresa().getNumeroDocumento())
            .correoPrincipal(dto.getEmpresa().getCorreo())
            .telefonoPrincipal(dto.getEmpresa().getTelefono())
            .idTipoPersona("Juridica".equalsIgnoreCase(dto.getEmpresa().getTipoPersona()) ? 1 : 2)
            .idTipoIdentificacion(mapDocType(dto.getEmpresa().getTipoDocumento()))
            .idTipoTelefono(1)
            .fechaCreado(ahora)
            .creadoPor(idUsuario)
            .activo(true)
            .build();
        proveedor = this.repository.save(proveedor);

        // 2. Ubicacion
        UbicacionEntity ubicacion = UbicacionEntity.builder()
            .idProveedor(proveedor.getIdProveedor())
            .direccion(dto.getUbicacion().getDireccion() + " (" + dto.getUbicacion().getCiudad() + ", " + dto.getUbicacion().getDepartamento() + ")")
            .idMunicipio(1) // Default para no romper constraints
            .fechaCreado(ahora)
            .creadoPor(idUsuario)
            .activo(true)
            .build();
        ubicacionRepository.save(ubicacion);

        // 3. Forma de Pago
        FormaDePagoEntity pago = FormaDePagoEntity.builder()
            .idProveedor(proveedor.getIdProveedor())
            .idTipoPago(1) // Default
            .fechaCreado(ahora)
            .creadoPor(idUsuario)
            .activo(true)
            .build();
        formaDePagoRepository.save(pago);

        // 4. Contactos
        saveContacto(dto.getContacto1(), proveedor.getIdProveedor(), ahora, idUsuario);
        saveContacto(dto.getContacto2(), proveedor.getIdProveedor(), ahora, idUsuario);

        // 5. Representantes Legales
        saveRepresentante(dto.getRepresentante1(), proveedor.getIdProveedor(), ahora, idUsuario);
        saveRepresentante(dto.getRepresentante2(), proveedor.getIdProveedor(), ahora, idUsuario);

        // 6. Socios
        saveSocio(dto.getSocio1(), proveedor.getIdProveedor(), ahora, idUsuario);
        saveSocio(dto.getSocio2(), proveedor.getIdProveedor(), ahora, idUsuario);

        return true;
    }

    private Integer mapDocType(String type) {
        if (type == null) return 1;
        switch(type.toLowerCase()) {
            case "cc": return 2;
            case "ce": return 3;
            default: return 1; // nit
        }
    }

    private void saveContacto(proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto.PersonaDto p, Integer idProv, java.time.LocalDateTime ahora, Integer idUsr) {
        if (p == null || p.getNombres() == null || p.getNombres().isEmpty()) return;
        ContactoEntity c = ContactoEntity.builder()
            .nombreContacto(p.getNombres() + " " + p.getApellidos())
            .cargoContacto(p.getCargo())
            .telefonoContacto(p.getTelefono())
            .correoContacto(p.getCorreo())
            .idTipoTelefono(1)
            .fechaCreado(ahora)
            .creadoPor(idUsr)
            .activo(true)
            .build();
        c = contactoRepository.save(c);
        proveedorContactoRepository.save(ProveedorContactoEntity.builder()
            .idProveedor(idProv).idContacto(c.getIdContacto()).estadoContacto(true)
            .fechaCreado(ahora).creadoPor(idUsr).activo(true).build());
    }

    private void saveRepresentante(proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto.PersonaDto p, Integer idProv, java.time.LocalDateTime ahora, Integer idUsr) {
        if (p == null || p.getNombres() == null || p.getNombres().isEmpty()) return;
        RepresentanteLegalEntity r = RepresentanteLegalEntity.builder()
            .nombres(p.getNombres())
            .apellidos(p.getApellidos())
            .numeroIdentificacion(p.getNumeroDocumento())
            .telefono(p.getTelefono())
            .correo(p.getCorreo())
            .idTipoIdentificacion(mapDocType(p.getTipoDocumento()))
            .idTipoTelefono(1)
            .fechaCreado(ahora)
            .creadoPor(idUsr)
            .activo(true)
            .build();
        r = representanteLegalRepository.save(r);
        representanteProveedorRepository.save(RepresentanteProveedorEntity.builder()
            .idProveedor(idProv).idRepresentanteLegal(r.getIdRepresentanteLegal())
            .fechaCreado(ahora).creadoPor(idUsr).activo(true).build());
    }

    private void saveSocio(proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto.PersonaDto p, Integer idProv, java.time.LocalDateTime ahora, Integer idUsr) {
        if (p == null || p.getNombres() == null || p.getNombres().isEmpty()) return;
        SociosProveedorEntity s = SociosProveedorEntity.builder()
            .idProveedor(idProv)
            .nombres(p.getNombres())
            .apellidos(p.getApellidos())
            .numeroIdentificacion(p.getNumeroDocumento())
            .idTipoIdentificacion(mapDocType(p.getTipoDocumento()))
            .fechaCreado(ahora)
            .creadoPor(idUsr)
            .activo(true)
            .build();
        sociosProveedorRepository.save(s);
    }

    public ProveedoresEntity validateIfExist(Integer id){
        Optional<ProveedoresEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ProveedoresEntity dtoToEntity(ProveedoresCreateRequestDto dto){
        return ProveedoresEntity.builder()
                .idTipoIdentificacion(dto.getIdTipoIdentificacion())
                .numeroIdentificacion(dto.getNumeroIdentificacion())
                .digitoVerificacion(dto.getDigitoVerificacion())
                .razonSocial(dto.getRazonSocial())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .idTipoPersona(dto.getIdTipoPersona())
                .telefonoPrincipal(dto.getTelefonoPrincipal())
                .idTipoTelefono(dto.getIdTipoTelefono())
                .correoPrincipal(dto.getCorreoPrincipal())
                .idActualizacionProveedor(dto.getIdActualizacionProveedor())
                .requiereActualizacion(dto.getRequiereActualizacion())
                .descripcion(dto.getDescripcion())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .activo(dto.getActivo())
                .build();
    }

    public ProveedoresResponseDto entityToDto(ProveedoresEntity entity){
        return ProveedoresResponseDto.builder()
                .idProveedor(entity.getIdProveedor())
                .idTipoIdentificacion(entity.getIdTipoIdentificacion())
                .numeroIdentificacion(entity.getNumeroIdentificacion())
                .digitoVerificacion(entity.getDigitoVerificacion())
                .razonSocial(entity.getRazonSocial())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .idTipoPersona(entity.getIdTipoPersona())
                .telefonoPrincipal(entity.getTelefonoPrincipal())
                .idTipoTelefono(entity.getIdTipoTelefono())
                .correoPrincipal(entity.getCorreoPrincipal())
                .idActualizacionProveedor(entity.getIdActualizacionProveedor())
                .requiereActualizacion(entity.getRequiereActualizacion())
                .descripcion(entity.getDescripcion())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .activo(entity.getActivo())
                .build();
    }
}
