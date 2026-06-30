package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "firma_token")
public class FirmaTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Integer idToken;

    @Column(name = "id_proveedor", nullable = false)
    private Integer idProveedor;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(name = "utilizado", nullable = false)
    private Boolean utilizado;

    @Column(name = "fecha_firmado")
    private LocalDateTime fechaFirmado;

    @Column(name = "ip_firma")
    private String ipFirma;
}
