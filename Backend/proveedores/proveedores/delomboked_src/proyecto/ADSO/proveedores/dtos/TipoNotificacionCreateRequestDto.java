package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoNotificacionCreateRequestDto {
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
