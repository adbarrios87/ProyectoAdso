package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoCreateRequestDto {
    private String codigo;
    private String nombre;
    private Integer idPais;
    private Boolean activo;
}
