package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionRiesgosResponseDto {
    private Integer idEvaluacionRiesgos;
    private Integer idUsuario;
    private Integer idProveedor;
    private Integer idValidacion;
    private Boolean validacionAuditoria;
    private java.time.LocalDateTime fecha;
    private String comentariosAuditoria;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
