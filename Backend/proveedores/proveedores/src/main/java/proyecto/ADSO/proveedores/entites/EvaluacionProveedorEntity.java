package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluacion_proveedor")
public class EvaluacionProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    private Integer idEvaluacion;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_calificacion")
    private Integer idCalificacion;

    @Column(name = "puntaje")
    private Integer puntaje;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "url_calificacion")
    private String urlCalificacion;

    @Column(name = "calidad")
    private Integer calidad;

    @Column(name = "obs_calidad")
    private String obsCalidad;

    @Column(name = "tiempo")
    private Integer tiempo;

    @Column(name = "obs_tiempo")
    private String obsTiempo;

    @Column(name = "documenta")
    private Integer documenta;

    @Column(name = "obs_documental")
    private String obsDocumental;

    @Column(name = "sarlaft")
    private Integer sarlaft;

    @Column(name = "obs_sarlaft")
    private String obsSarlaft;

    @Column(name = "comercial")
    private Integer comercial;

    @Column(name = "obs_comercial")
    private String obsComercial;

    @Column(name = "social")
    private Integer social;

    @Column(name = "obs_social")
    private String obsSocial;

    @Column(name = "mejora")
    private Integer mejora;

    @Column(name = "obs_mejora")
    private String obsMejora;

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
