package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoProveedorResponseDto {
    private Integer idEstadoProveedor;
    private String estado;
    private String descripcion;
    private Boolean activo;
}
