package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forma_de_pago")
public class FormaDePagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pago")
    private Integer idFormaPago;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_tipo_pago")
    private Integer idTipoPago;

    @Column(name = "monto")
    private java.math.BigDecimal monto;

    @Column(name = "plazo")
    private Integer plazo;

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
