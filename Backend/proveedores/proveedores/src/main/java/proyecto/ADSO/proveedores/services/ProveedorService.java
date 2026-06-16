package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.entites.*;
import proyecto.ADSO.proveedores.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;
    
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

    public boolean create(ProveedorCreateRequestDto dto){
        ProveedorEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ProveedorResponseDto> getAll(){
        List<ProveedorEntity> entities = this.repository.findAll();
        List<ProveedorResponseDto> dtos = new ArrayList<>();
        for (ProveedorEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ProveedorResponseDto getDetail(Integer id){
        ProveedorEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public ProveedorResponseDto getByEmail(String email){
        Optional<ProveedorEntity> opt = this.repository.findByCorreoPrincipal(email);
        if(opt.isEmpty()) return null;
        return entityToDto(opt.get());
    }

    public boolean update(Integer id, ProveedorCreateRequestDto dto) {
        ProveedorEntity entity = validateIfExist(id);
        ProveedorEntity newEntity = dtoToEntity(dto);
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
        
        // Asignar automáticamente fecha de aprobación si pasa a APROBADO (6)
        if (newEntity.getIdEstadoProveedor() != null && newEntity.getIdEstadoProveedor() == 6) {
            if (entity.getIdEstadoProveedor() == null || entity.getIdEstadoProveedor() != 6) {
                entity.setFechaAprobacion(java.time.LocalDateTime.now());
            }
        } else {
            entity.setFechaAprobacion(newEntity.getFechaAprobacion());
        }
        
        entity.setIdEstadoProveedor(newEntity.getIdEstadoProveedor());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        ProveedorEntity entity = validateIfExist(id);
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

        // 1. Proveedor (Buscar si ya existe para actualizar, o crear en su defecto)
        Optional<ProveedorEntity> optProveedor = this.repository.findByIdUsuario(idUsuario);
        ProveedorEntity proveedor;
        if (optProveedor.isPresent()) {
            proveedor = optProveedor.get();
        } else {
            // Buscar por correo como plan de respaldo
            Optional<ProveedorEntity> optMail = this.repository.findByCorreoPrincipal(dto.getEmpresa().getCorreo());
            if (optMail.isPresent()) {
                proveedor = optMail.get();
            } else {
                proveedor = new ProveedorEntity();
                proveedor.setFechaCreado(ahora);
                proveedor.setCreadoPor(idUsuario);
            }
        }

        proveedor.setRazonSocial(dto.getEmpresa().getNombre());
        proveedor.setNumeroIdentificacion(dto.getEmpresa().getNumeroIdentificacion());
        proveedor.setCorreoPrincipal(dto.getEmpresa().getCorreo());
        proveedor.setTelefonoPrincipal(dto.getEmpresa().getTelefono());
        proveedor.setIdTipoPersona(dto.getEmpresa().getIdTipoPersona() != null ? dto.getEmpresa().getIdTipoPersona() : 1);
        proveedor.setIdTipoIdentificacion(dto.getEmpresa().getIdTipoIdentificacion() != null ? dto.getEmpresa().getIdTipoIdentificacion() : 1);
        proveedor.setIdTipoTelefono(dto.getEmpresa().getIdTipoTelefono() != null ? dto.getEmpresa().getIdTipoTelefono() : 1);
        proveedor.setDescripcion(dto.getEmpresa().getCategoria());
        proveedor.setCiiu(dto.getEmpresa().getCiiu());
        proveedor.setPaginaWeb(dto.getEmpresa().getPaginaWeb());
        proveedor.setBancoReferencia(dto.getBancaria() != null ? dto.getBancaria().getBanco() : null);
        proveedor.setActivos(dto.getFinanciera() != null ? dto.getFinanciera().getActivos() : null);
        proveedor.setPasivos(dto.getFinanciera() != null ? dto.getFinanciera().getPasivos() : null);
        proveedor.setPatrimonio(dto.getFinanciera() != null ? dto.getFinanciera().getPatrimonio() : null);
        proveedor.setTotalIngresos(dto.getFinanciera() != null ? dto.getFinanciera().getTotalIngresos() : null);
        proveedor.setTotalGastos(dto.getFinanciera() != null ? dto.getFinanciera().getTotalGastos() : null);
        proveedor.setLaftP1(dto.getLaft() != null ? dto.getLaft().getP1() : null);
        proveedor.setLaftP2(dto.getLaft() != null ? dto.getLaft().getP2() : null);
        proveedor.setLaftP3(dto.getLaft() != null ? dto.getLaft().getP3() : null);
        proveedor.setLaftP4(dto.getLaft() != null ? dto.getLaft().getP4() : null);
        proveedor.setLaftP5(dto.getLaft() != null ? dto.getLaft().getP5() : null);
        proveedor.setFechaModificado(ahora);
        proveedor.setModificadoPor(idUsuario);
        proveedor.setActivo(true);
        if (proveedor.getIdUsuario() == null) {
            proveedor.setIdUsuario(idUsuario);
        }
        proveedor = this.repository.save(proveedor);

        // 2. Ubicacion
        Integer idMunicipio = dto.getEmpresa().getIdMunicipio() != null ? dto.getEmpresa().getIdMunicipio() : 1;
        String fullDireccion = dto.getEmpresa().getDireccion();

        Optional<UbicacionEntity> optUbicacion = ubicacionRepository.findByIdProveedor(proveedor.getIdProveedor());
        UbicacionEntity ubicacion;
        if (optUbicacion.isPresent()) {
            ubicacion = optUbicacion.get();
        } else {
            ubicacion = new UbicacionEntity();
            ubicacion.setIdProveedor(proveedor.getIdProveedor());
            ubicacion.setFechaCreado(ahora);
            ubicacion.setCreadoPor(idUsuario);
        }
        ubicacion.setDireccion(fullDireccion);
        ubicacion.setIdMunicipio(idMunicipio);
        ubicacion.setFechaModificado(ahora);
        ubicacion.setModificadoPor(idUsuario);
        ubicacion.setActivo(true);
        ubicacionRepository.save(ubicacion);

        // 3. Forma de Pago
        Integer idPago = (dto.getBancaria() != null && dto.getBancaria().getIdMetodoPago() != null) ? dto.getBancaria().getIdMetodoPago() : 1;
        Optional<FormaDePagoEntity> optPago = formaDePagoRepository.findByIdProveedor(proveedor.getIdProveedor());
        FormaDePagoEntity pago;
        if (optPago.isPresent()) {
            pago = optPago.get();
        } else {
            pago = new FormaDePagoEntity();
            pago.setIdProveedor(proveedor.getIdProveedor());
            pago.setFechaCreado(ahora);
            pago.setCreadoPor(idUsuario);
        }
        pago.setIdTipoPago(idPago);
        pago.setFechaModificado(ahora);
        pago.setModificadoPor(idUsuario);
        pago.setActivo(true);
        formaDePagoRepository.save(pago);

        // 4. Limpiar contactos anteriores y sus relaciones
        List<ProveedorContactoEntity> contactosAnteriores = proveedorContactoRepository.findByIdProveedor(proveedor.getIdProveedor());
        for (ProveedorContactoEntity pc : contactosAnteriores) {
            try {
                contactoRepository.deleteById(pc.getIdContacto());
            } catch(Exception e) {}
        }
        proveedorContactoRepository.deleteByIdProveedor(proveedor.getIdProveedor());

        // Insertar Contacto Nuevo
        if (dto.getContacto() != null && dto.getContacto().getNombres() != null && !dto.getContacto().getNombres().isEmpty()) {
            ContactoEntity c = ContactoEntity.builder()
                .nombreContacto(dto.getContacto().getNombres() + " " + dto.getContacto().getApellidos())
                .cargoContacto(dto.getContacto().getCargo())
                .telefonoContacto(dto.getContacto().getTelefono())
                .correoContacto(dto.getContacto().getCorreo())
                .idTipoTelefono(dto.getContacto().getIdTipoTelefono() != null ? dto.getContacto().getIdTipoTelefono() : 1)
                .fechaCreado(ahora)
                .creadoPor(idUsuario)
                .activo(true)
                .build();
            c = contactoRepository.save(c);
            proveedorContactoRepository.save(ProveedorContactoEntity.builder()
                .idProveedor(proveedor.getIdProveedor()).idContacto(c.getIdContacto()).estadoContacto(true)
                .fechaCreado(ahora).creadoPor(idUsuario).activo(true).build());
        }

        // 5. Limpiar representantes anteriores y relaciones
        List<RepresentanteProveedorEntity> representantesAnteriores = representanteProveedorRepository.findByIdProveedor(proveedor.getIdProveedor());
        for (RepresentanteProveedorEntity rp : representantesAnteriores) {
            try {
                representanteLegalRepository.deleteById(rp.getIdRepresentanteLegal());
            } catch(Exception e) {}
        }
        representanteProveedorRepository.deleteByIdProveedor(proveedor.getIdProveedor());

        // Insertar Representantes Nuevos (Principal y Suplentes)
        if (dto.getRepresentantes() != null) {
            for (proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto.RepresentanteDto rep : dto.getRepresentantes()) {
                if (rep.getNombres() != null && !rep.getNombres().isEmpty()) {
                    RepresentanteLegalEntity r = RepresentanteLegalEntity.builder()
                        .nombres(rep.getNombres())
                        .apellidos(rep.getApellidos())
                        .numeroIdentificacion(rep.getNumeroIdentificacion())
                        .telefono(rep.getTelefono())
                        .correo(rep.getCorreo())
                        .idTipoIdentificacion(rep.getIdTipoIdentificacion() != null ? rep.getIdTipoIdentificacion() : 1)
                        .idTipoTelefono(rep.getIdTipoTelefono() != null ? rep.getIdTipoTelefono() : 1)
                        .nacionalidad(rep.getNacionalidad())
                        .fechaCreado(ahora)
                        .creadoPor(idUsuario)
                        .activo(true)
                        .build();
                    r = representanteLegalRepository.save(r);
                    representanteProveedorRepository.save(RepresentanteProveedorEntity.builder()
                        .idProveedor(proveedor.getIdProveedor()).idRepresentanteLegal(r.getIdRepresentanteLegal())
                        .fechaCreado(ahora).creadoPor(idUsuario).activo(true).build());
                }
            }
        }

        // 6. Limpiar socios anteriores
        sociosProveedorRepository.deleteByIdProveedor(proveedor.getIdProveedor());

        // Insertar Socios Nuevos
        if (dto.getSocios() != null) {
            for (proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto.SocioDto socio : dto.getSocios()) {
                if (socio.getNombreCompleto() != null && !socio.getNombreCompleto().isEmpty()) {
                    String nombres = socio.getNombreCompleto();
                    String apellidos = "";
                    int idx = nombres.lastIndexOf(' ');
                    if (idx > 0) {
                        apellidos = nombres.substring(idx + 1);
                        nombres = nombres.substring(0, idx);
                    }
                    SociosProveedorEntity s = SociosProveedorEntity.builder()
                        .idProveedor(proveedor.getIdProveedor())
                        .idTipoPersona(socio.getIdTipoPersona())
                        .nombres(nombres)
                        .apellidos(apellidos)
                        .numeroIdentificacion(socio.getNumeroIdentificacion())
                        .idTipoIdentificacion(socio.getIdTipoIdentificacion() != null ? socio.getIdTipoIdentificacion() : 1)
                        .nacionalidad(socio.getNacionalidad())
                        .participacion(socio.getParticipacion())
                        .fechaCreado(ahora)
                        .creadoPor(idUsuario)
                        .activo(true)
                        .build();
                    sociosProveedorRepository.save(s);
                }
            }
        }

        return true;
    }


    public ProveedorEntity validateIfExist(Integer id){
        Optional<ProveedorEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ProveedorEntity dtoToEntity(ProveedorCreateRequestDto dto){
        return ProveedorEntity.builder()
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
                .idEstadoProveedor(dto.getIdEstadoProveedor())
                .fechaAprobacion(dto.getFechaAprobacion())
                .build();
    }

    public ProveedorResponseDto entityToDto(ProveedorEntity entity){
        return ProveedorResponseDto.builder()
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
                .idEstadoProveedor(entity.getIdEstadoProveedor())
                .fechaAprobacion(entity.getFechaAprobacion())
                .build();
    }
}
