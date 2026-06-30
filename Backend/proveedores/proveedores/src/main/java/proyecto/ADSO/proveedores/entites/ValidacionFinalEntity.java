package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "validacion_final")
public class ValidacionFinalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validacion_final")
    private Integer idValidacionFinal;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "comentario_final")
    private String comentarioFinal;

    @Column(name = "estado_validacion")
    private String estadoValidacion;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "fecha_creado")
    private LocalDateTime fechaCreado;

    @Column(name = "modificado_por")
    private Integer modificadoPor;

    @Column(name = "fecha_modificado")
    private LocalDateTime fechaModificado;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_validacion_final")
    private Set<ValidacionEntity> validaciones;
}
