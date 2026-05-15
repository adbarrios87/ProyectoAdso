package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosCreateRequestDto {
    private String nombreUsuario;
    private String cargoUsuario;
    private String correoUsuario;
    private String contrasena;
    private Boolean estadoUsuario;
    private Integer idRol;
    private String fotoUrl;
    private java.time.LocalDateTime ultimoIngreso;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    
    // Notificaciones
    private Boolean notifStatus;
    private Boolean notifDocs;
    private Boolean notifExpiry;
    private Boolean notifNews;

    // Campos para Proveedor (cuando el rol es Proveedor)
    private Integer idTipoPersona;
    private String razonSocial;
    private String nombres;
    private String apellidos;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;
}
