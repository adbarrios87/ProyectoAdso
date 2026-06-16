package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.dto.MenuDto;
import proyecto.ADSO.proveedores.entites.*;
import proyecto.ADSO.proveedores.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<MenuDto> getMenuByRole(Integer idRol) {
        List<MenuDto> menu = new ArrayList<>();

        // 1. Administrador
        if (idRol == 1) {
            menu.add(MenuDto.builder().titulo("Inicio").url("admin_dashboard.html").icono("fa-house").build());
            menu.add(MenuDto.builder().titulo("Usuarios").icono("fa-users")
                    .submenus(Arrays.asList(
                            MenuDto.builder().titulo("Nuevo usuario").url("user_new.html").build(),
                            MenuDto.builder().titulo("Lista de usuarios").url("user_list.html").build()))
                    .build());
            menu.add(MenuDto.builder().titulo("Proveedores").icono("fa-truck-field")
                    .submenus(Arrays.asList(
                            MenuDto.builder().titulo("Lista Maestra").url("buyer_supplier_list.html").build(),
                            MenuDto.builder().titulo("Dashboard Compras").url("buyer_dashboard.html").build(),
                            MenuDto.builder().titulo("Dashboard Proveedor").url("supplier_dashboard.html").build()))
                    .build());
            menu.add(MenuDto.builder().titulo("Seguridad y Riesgos").icono("fa-shield-halved")
                    .submenus(Arrays.asList(
                            MenuDto.builder().titulo("Analista de riesgos").url("risk_dashboard.html").build(),
                            MenuDto.builder().titulo("Oficial de cumplimiento").url("compliance_officer_dashboard.html")
                                    .build(),
                            MenuDto.builder().titulo("Historial de Riesgos").url("risk_historial.html").build(),
                            MenuDto.builder().titulo("Registros de Auditoría").url("admin_audit_logs.html").build()))
                    .build());
            menu.add(MenuDto.builder().titulo("Reportes Globales").url("buyer_reports.html").icono("fa-chart-line")
                    .build());
            menu.add(MenuDto.builder().titulo("Alertas de Sistema").url("expiration_alerts.html").icono("fa-bell")
                    .build());
        }
        // 2. Comprador
        else if (idRol == 2) {
            menu.add(MenuDto.builder().titulo("Inicio").url("buyer_dashboard.html").icono("fa-house").build());
            menu.add(MenuDto.builder().titulo("Lista de usuarios").url("user_list.html").icono("fa-users").build());
            menu.add(MenuDto.builder().titulo("Proveedores").url("buyer_supplier_list.html").icono("fa-building-user")
                    .build());
            menu.add(MenuDto.builder().titulo("Reportes").url("buyer_reports.html").icono("fa-chart-column").build());
        }
        // 3. Proveedor
        else if (idRol == 3) {
            menu.add(MenuDto.builder().titulo("Inicio").url("supplier_dashboard.html").icono("fa-house").build());
            menu.add(MenuDto.builder().titulo("Actualizar información").url("supplier_form.html")
                    .icono("fa-spinner fa-spin").build());
            menu.add(MenuDto.builder().titulo("Cargar documentos").url("supplier_upload_documents.html")
                    .icono("fa-upload").build());
            menu.add(MenuDto.builder().titulo("Generar certificación").url("supplier_certification.html")
                    .icono("fa-certificate").build());
            menu.add(MenuDto.builder().titulo("Historial de calificaciones").url("supplier_qualification_history.html")
                    .icono("fa-history").build());
            menu.add(MenuDto.builder().titulo("Notificaciones").url("notifications.html").icono("fa-bell").build());
        }
        // 4. Analista
        else if (idRol == 4) {
            menu.add(MenuDto.builder().titulo("Inicio").url("risk_dashboard.html").icono("fa-house").build());
            menu.add(MenuDto.builder().titulo("Histórico de aprobaciones").url("approval_history.html")
                    .icono("fa-check-double").build());
        }
        // 5. Oficial de Cumplimiento
        else if (idRol == 5) {
            menu.add(MenuDto.builder().titulo("Inicio").url("compliance_officer_dashboard.html").icono("fa-house")
                    .build());
            menu.add(MenuDto.builder().titulo("Histórico de aprobaciones").url("compliance_officer_history.html")
                    .icono("fa-check-double").build());
        }

        // Todos tienen acceso al perfil
        // menu.add(MenuDto.builder().titulo("Configuración").url("user_profile.html").icono("fa-gear").build());

        return menu;
    }

    public boolean create(UsuariosCreateRequestDto dto) {
        UsuariosEntity entity = this.dtoToEntity(dto);
        entity.setFechaCreado(java.time.LocalDateTime.now());
        entity.setEstadoUsuario(true); // Activo por defecto
        entity.setNotifStatus(true);// Inicializar Notificaciones por defecto
        entity.setNotifDocs(true);
        entity.setNotifExpiry(true);
        entity.setNotifNews(true);
        this.repository.save(entity);

        // Si el rol es Proveedor (ID 3), crear registro en la tabla proveedor
        if (dto.getIdRol() != null && dto.getIdRol() == 3) {
            ProveedorEntity proveedor = ProveedorEntity.builder()
                    .idUsuario(entity.getIdUsuario()) // Relacionar con el nuevo id_usuario
                    .correoPrincipal(dto.getCorreoUsuario())
                    .idTipoPersona(dto.getIdTipoPersona())
                    .idTipoIdentificacion(dto.getIdTipoIdentificacion())
                    .numeroIdentificacion(dto.getNumeroIdentificacion())
                    .razonSocial(dto.getRazonSocial())
                    .nombres(dto.getNombres())
                    .apellidos(dto.getApellidos())
                    .activo(true)
                    .requiereActualizacion(true)
                    .fechaCreado(java.time.LocalDateTime.now())
                    .creadoPor(dto.getCreadoPor())
                    .idEstadoProveedor(4) // Estado "Sin documentación" por defecto
                    .build();

            this.proveedorRepository.save(proveedor);
        }

        return true;
    }

    // listar usuarios
    public List<UsuariosResponseDto> getAll() {
        List<UsuariosEntity> entities = this.repository.findAll();
        List<UsuariosResponseDto> dtos = new ArrayList<>();
        for (UsuariosEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    // detalle de usuario
    public UsuariosResponseDto getDetail(Integer id) {
        UsuariosEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    // actualizar usuario
    public boolean update(Integer id, UsuariosCreateRequestDto dto) {
        UsuariosEntity entity = validateIfExist(id);
        UsuariosEntity newEntity = dtoToEntity(dto);

        entity.setNombreUsuario(newEntity.getNombreUsuario());
        entity.setCargoUsuario(newEntity.getCargoUsuario());
        entity.setCorreoUsuario(newEntity.getCorreoUsuario());
        entity.setContrasena(newEntity.getContrasena());
        entity.setEstadoUsuario(newEntity.getEstadoUsuario());
        entity.setIdRol(newEntity.getIdRol());
        entity.setFotoUrl(newEntity.getFotoUrl());
        entity.setFechaModificado(java.time.LocalDateTime.now());
        entity.setModificadoPor(newEntity.getModificadoPor());
        entity.setNotifStatus(newEntity.getNotifStatus());
        entity.setNotifDocs(newEntity.getNotifDocs());
        entity.setNotifExpiry(newEntity.getNotifExpiry());
        entity.setNotifNews(newEntity.getNotifNews());
        this.repository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        UsuariosEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    @jakarta.transaction.Transactional
    public boolean updateFoto(Integer id, String fotoUrl) {
        UsuariosEntity entity = validateIfExist(id);
        entity.setFotoUrl(fotoUrl);
        this.repository.saveAndFlush(entity);
        return true;
    }

    public boolean updateEstado(Integer id, Boolean estado) {
        UsuariosEntity entity = validateIfExist(id);
        entity.setEstadoUsuario(estado);
        this.repository.save(entity);
        return true;
    }

    @jakarta.transaction.Transactional
    public boolean updateRequiereActualizacion(Integer idUsuario, Boolean requiereActualizacion) {
        Optional<ProveedorEntity> optProv = this.proveedorRepository.findByIdUsuario(idUsuario);
        if (optProv.isPresent()) {
            ProveedorEntity proveedor = optProv.get();
            proveedor.setRequiereActualizacion(requiereActualizacion);
            this.proveedorRepository.save(proveedor);
            return true;
        }
        return false;
    }

    public UsuariosEntity validateIfExist(Integer id) {
        Optional<UsuariosEntity> optEntity = this.repository.findById(id);
        if (optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Optional<UsuariosEntity> optEntity = this.repository.findByCorreoUsuarioAndContrasena(
                request.getUsuario(), request.getContrasena());

        if (optEntity.isPresent()) {
            UsuariosEntity entity = optEntity.get();
            // Actualizamos solo el último ingreso en la BD de forma ligera
            this.repository.updateUltimoIngreso(entity.getIdUsuario(), java.time.LocalDateTime.now());

            return LoginResponseDto.builder()
                    .successful(true)
                    .message("Login exitoso")
                    .idUsuario(entity.getIdUsuario())
                    .nombreUsuario(entity.getNombreUsuario())
                    .correoUsuario(entity.getCorreoUsuario())
                    .idRol(entity.getIdRol())
                    .fotoUrl(entity.getFotoUrl())
                    .build();
        } else {
            return LoginResponseDto.builder()
                    .successful(false)
                    .message("Usuario o contraseña incorrectos")
                    .build();
        }
    }

    public UsuariosEntity dtoToEntity(UsuariosCreateRequestDto dto) {
        return UsuariosEntity.builder()
                .nombreUsuario(dto.getNombreUsuario())
                .cargoUsuario(dto.getCargoUsuario())
                .correoUsuario(dto.getCorreoUsuario())
                .contrasena(dto.getContrasena())
                .idRol(dto.getIdRol())
                .fotoUrl(dto.getFotoUrl())
                .ultimoIngreso(dto.getUltimoIngreso())
                .fechaCreado(dto.getFechaCreado())
                .creadoPor(dto.getCreadoPor())
                .fechaModificado(dto.getFechaModificado())
                .modificadoPor(dto.getModificadoPor())
                .estadoUsuario(dto.getEstadoUsuario())
                .notifStatus(dto.getNotifStatus())
                .notifDocs(dto.getNotifDocs())
                .notifExpiry(dto.getNotifExpiry())
                .notifNews(dto.getNotifNews())
                .build();
    }

    public UsuariosResponseDto entityToDto(UsuariosEntity entity) {
        Boolean requiereActualizacion = null;
        if (entity.getIdRol() != null && entity.getIdRol() == 3) {
            Optional<ProveedorEntity> optProv = this.proveedorRepository.findByIdUsuario(entity.getIdUsuario());
            if (optProv.isPresent()) {
                requiereActualizacion = optProv.get().getRequiereActualizacion();
            }
        }

        return UsuariosResponseDto.builder()
                .idUsuario(entity.getIdUsuario())
                .nombreUsuario(entity.getNombreUsuario())
                .cargoUsuario(entity.getCargoUsuario())
                .correoUsuario(entity.getCorreoUsuario())
                .contrasena(entity.getContrasena())
                .idRol(entity.getIdRol())
                .nombreRol(entity.getRoles() != null ? entity.getRoles().getRol() : "Sin rol")
                .fotoUrl(entity.getFotoUrl())
                .ultimoIngreso(entity.getUltimoIngreso())
                .fechaCreado(entity.getFechaCreado())
                .creadoPor(entity.getCreadoPor())
                .fechaModificado(entity.getFechaModificado())
                .modificadoPor(entity.getModificadoPor())
                .estadoUsuario(entity.getEstadoUsuario())
                .notifStatus(entity.getNotifStatus())
                .notifDocs(entity.getNotifDocs())
                .notifExpiry(entity.getNotifExpiry())
                .notifNews(entity.getNotifNews())
                .requiereActualizacion(requiereActualizacion)
                .build();
    }
}
