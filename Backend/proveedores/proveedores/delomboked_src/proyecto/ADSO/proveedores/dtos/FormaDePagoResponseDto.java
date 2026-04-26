package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaDePagoResponseDto {
    private Integer idFormaPago;
    private Integer idProveedor;
    private Integer idTipoPago;
    private java.math.BigDecimal monto;
    private Integer plazo;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
