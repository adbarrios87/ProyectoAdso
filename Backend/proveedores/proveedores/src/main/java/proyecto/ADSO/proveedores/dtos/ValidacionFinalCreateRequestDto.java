package proyecto.ADSO.proveedores.dtos;

import lombok.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidacionFinalCreateRequestDto {
    private Integer idProveedor;
    private String comentarioFinal;
    private String estadoValidacion;
    private Integer creadoPor;
    private Set<Integer> validationIds;
}
