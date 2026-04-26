package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoNotificacionResponseDto {
    private Integer idTipoNotificacion;
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
