package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "campo_validacion")
public class CampoValidacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campo_validacion")
    private Integer idCampoValidacion;

    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "campo")
    private String campo;

    @Column(name = "obligatorio")
    private Boolean obligatorio;

    @Column(name = "activo")
    private Boolean activo;

}
