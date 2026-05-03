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

    @Column(name = "estado_usuario")
    private Boolean estadoUsuario;

    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Column(name = "ultimo_ingreso")
    private java.time.LocalDateTime ultimoIngreso;

    @Column(name = "fecha_creado")
    private java.time.LocalDateTime fechaCreado;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "fecha_modificado")
    private java.time.LocalDateTime fechaModificado;

    @Column(name = "modificado_por")
    private Integer modificadoPor;

}
