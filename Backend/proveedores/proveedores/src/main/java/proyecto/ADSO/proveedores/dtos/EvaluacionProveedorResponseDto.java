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
    private Integer calidad;
    private String obsCalidad;
    private Integer tiempo;
    private String obsTiempo;
    private Integer documenta;
    private String obsDocumental;
    private Integer sarlaft;
    private String obsSarlaft;
    private Integer comercial;
    private String obsComercial;
    private Integer social;
    private String obsSocial;
    private Integer mejora;
    private String obsMejora;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
