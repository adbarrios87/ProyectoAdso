package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoIdentificacionResponseDto {
    private Integer idTipoIdentificacion;
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
