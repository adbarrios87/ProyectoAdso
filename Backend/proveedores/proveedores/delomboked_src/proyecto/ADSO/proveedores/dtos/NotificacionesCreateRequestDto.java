package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionesCreateRequestDto {
    private Integer idUsuario;
    private Integer idTipoNotificacion;
    private java.time.LocalDateTime fechaNotificacion;
    private String mensaje;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
