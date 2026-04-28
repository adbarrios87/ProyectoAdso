package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoResponseDto {
    private Integer idTipoDocumento;
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
