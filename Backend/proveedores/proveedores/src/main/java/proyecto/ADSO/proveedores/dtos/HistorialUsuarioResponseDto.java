package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistorialUsuarioResponseDto {
    private Integer idHistorialUsuario;
    private Integer idUsuario;
    private Integer idEstadoUsuario;
    private String comentarios;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
