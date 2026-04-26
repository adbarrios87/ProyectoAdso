package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoResponseDto {
    private Integer idDepartamento;
    private String codigo;
    private String nombre;
    private Integer idPais;
    private Boolean activo;
}
