package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluacion_riesgos")
public class EvaluacionRiesgosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion_riesgos")
    private Integer idEvaluacionRiesgos;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_validacion")
    private Integer idValidacion;

    @Column(name = "validacion_auditoria")
    private Boolean validacionAuditoria;

    @Column(name = "fecha")
    private java.time.LocalDateTime fecha;

    @Column(name = "comentarios_auditoria")
    private String comentariosAuditoria;

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
