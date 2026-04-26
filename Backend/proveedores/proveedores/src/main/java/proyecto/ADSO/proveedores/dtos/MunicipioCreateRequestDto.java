package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioCreateRequestDto {
    private String codigo;
    private String nombre;
    private Integer idDepartamento;
    private Boolean activo;
}
