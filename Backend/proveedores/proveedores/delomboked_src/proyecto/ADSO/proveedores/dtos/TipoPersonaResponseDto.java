package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoPersonaResponseDto {
    private Integer idTipoPersona;
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
