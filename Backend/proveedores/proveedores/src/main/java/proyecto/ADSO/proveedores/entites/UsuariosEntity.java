package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "cargo_usuario")
    private String cargoUsuario;

    @Column(name = "correo_usuario")
    private String correoUsuario;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "id_rol")
    private Integer idRol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private RolesEntity roles;

    @Column(name = "foto_url", columnDefinition = "LONGTEXT")
    private String fotoUrl;

    @Column(name = "ultimo_ingreso")
    private java.time.LocalDateTime ultimoIngreso;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "fecha_creado")
    private java.time.LocalDateTime fechaCreado;

    @Column(name = "modificado_por")
    private Integer modificadoPor;

    @Column(name = "fecha_modificado")
    private java.time.LocalDateTime fechaModificado;

    @Column(name = "estado_usuario")
    private Boolean estadoUsuario;

    // Nuevos campos de preferencias de notificación
    @Column(name = "notif_status")
    private Boolean notifStatus = true;

    @Column(name = "notif_docs")
    private Boolean notifDocs = true;

    @Column(name = "notif_expiry")
    private Boolean notifExpiry = true;

    @Column(name = "notif_news")
    private Boolean notifNews = true;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    private java.time.LocalDateTime resetTokenExpiry;

}
