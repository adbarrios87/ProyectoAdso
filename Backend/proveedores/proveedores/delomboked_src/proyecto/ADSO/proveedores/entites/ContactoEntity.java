package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacto")
public class ContactoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Integer idContacto;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column(name = "cargo_contacto")
    private String cargoContacto;

    @Column(name = "id_tipo_telefono")
    private Integer idTipoTelefono;

    @Column(name = "telefono_contacto")
    private String telefonoContacto;

    @Column(name = "correo_contacto")
    private String correoContacto;

    @Column(name = "fecha_creado")
    private java.time.LocalDateTime fechaCreado;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "fecha_modificado")
    private java.time.LocalDateTime fechaModificado;

    @Column(name = "modificado_por")
    private Integer modificadoPor;

    @Column(name = "activo")
    private Boolean activo;

}
