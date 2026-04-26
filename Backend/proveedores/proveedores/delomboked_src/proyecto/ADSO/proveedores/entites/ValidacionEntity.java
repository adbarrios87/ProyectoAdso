package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "validacion")
public class ValidacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validacion")
    private Integer idValidacion;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_campo_validacion")
    private Integer idCampoValidacion;

    @Column(name = "valor_web")
    private String valorWeb;

    @Column(name = "valor_documento")
    private String valorDocumento;

    @Column(name = "id_documento")
    private Integer idDocumento;

    @Column(name = "resultado_validacion")
    private Boolean resultadoValidacion;

    @Column(name = "fecha_validacion")
    private java.time.LocalDate fechaValidacion;

    @Column(name = "comentarios")
    private String comentarios;

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
