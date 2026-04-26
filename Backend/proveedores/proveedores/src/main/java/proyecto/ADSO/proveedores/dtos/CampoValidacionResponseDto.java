package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampoValidacionResponseDto {
    private Integer idCampoValidacion;
    private Integer idTipoDocumento;
    private String campo;
    private Boolean obligatorio;
    private Boolean activo;
}
