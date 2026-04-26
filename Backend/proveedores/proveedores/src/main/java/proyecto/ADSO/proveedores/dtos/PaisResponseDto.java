package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaisResponseDto {
    private Integer idPais;
    private String codigo;
    private String nombre;
    private Boolean activo;
}
