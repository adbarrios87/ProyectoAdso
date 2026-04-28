package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoIdentificacionCreateRequestDto {
    private String codigo;
    private String descripcion;
    private Boolean estado;
    private java.time.LocalDateTime fechaCreado;
    private java.time.LocalDateTime fechaModificado;
    private Boolean activo;
}
