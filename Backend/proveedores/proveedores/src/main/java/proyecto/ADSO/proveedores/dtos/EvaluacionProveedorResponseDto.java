package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionProveedorResponseDto {
    private Integer idEvaluacion;
    private Integer idProveedor;
    private Integer idUsuario;
    private Integer idCalificacion;
    private Integer puntaje;
    private String observaciones;
    private String urlCalificacion;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
