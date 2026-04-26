package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaisCreateRequestDto {
    private String codigo;
    private String nombre;
    private Boolean activo;
}
