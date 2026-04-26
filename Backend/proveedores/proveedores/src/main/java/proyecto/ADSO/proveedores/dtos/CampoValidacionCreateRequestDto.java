package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampoValidacionCreateRequestDto {
    private Integer idTipoDocumento;
    private String campo;
    private Boolean obligatorio;
    private Boolean activo;
}
