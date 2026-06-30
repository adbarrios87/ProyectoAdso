package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoIdentificacionCreateRequestDto {
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
