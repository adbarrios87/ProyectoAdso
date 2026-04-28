package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class ProveedoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_tipo_identificacion")
    private Integer idTipoIdentificacion;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "digito_verificacion")
    private String digitoVerificacion;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "id_tipo_persona")
    private Integer idTipoPersona;

    @Column(name = "telefono_principal")
    private String telefonoPrincipal;

    @Column(name = "id_tipo_telefono")
    private Integer idTipoTelefono;

    @Column(name = "correo_principal")
    private String correoPrincipal;

    @Column(name = "id_actualizacion_proveedor")
    private Integer idActualizacionProveedor;

    @Column(name = "requiere_actualizacion")
    private Boolean requiereActualizacion;

    @Column(name = "descripcion")
    private String descripcion;

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
