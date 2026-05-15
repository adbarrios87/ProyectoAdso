package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "socios_proveedor")
public class SociosProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio_proveedor")
    private Integer idSocioProveedor;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "id_tipo_identificacion")
    private Integer idTipoIdentificacion;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "consulta")
    private Integer consulta;

    @Column(name = "fecha_consulta")
    private java.time.LocalDate fechaConsulta;

    @Column(name = "resultado_consulta")
    private Boolean resultadoConsulta;

    @Column(name = "id_origen")
    private Integer idOrigen;

    @Column(name = "fecha_extraccion")
    private java.time.LocalDateTime fechaExtraccion;

    @Column(name = "validado")
    private Boolean validado;

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

    @Column(name = "id_tipo_persona")
    private Integer idTipoPersona;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "participacion")
    private java.math.BigDecimal participacion;

}

