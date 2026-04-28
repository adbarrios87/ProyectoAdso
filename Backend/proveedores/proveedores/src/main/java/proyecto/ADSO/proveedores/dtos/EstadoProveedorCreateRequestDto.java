package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoProveedorCreateRequestDto {
    private String estado;
    private String descripcion;
    private Boolean activo;
}
