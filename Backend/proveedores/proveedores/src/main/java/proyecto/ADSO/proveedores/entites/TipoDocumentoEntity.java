package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_documento")
public class TipoDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tipo_documento_persona",
        joinColumns = @JoinColumn(name = "id_tipo_documento"),
        inverseJoinColumns = @JoinColumn(name = "id_tipo_persona")
    )
    private java.util.Set<TipoPersonaEntity> tiposPersona;

}
