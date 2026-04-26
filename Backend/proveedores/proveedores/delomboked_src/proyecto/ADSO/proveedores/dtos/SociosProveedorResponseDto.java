package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SociosProveedorResponseDto {
    private Integer idSocioProveedor;
    private Integer idProveedor;
    private String nombres;
    private String apellidos;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;
    private Integer consulta;
    private java.time.LocalDate fechaConsulta;
    private Boolean resultadoConsulta;
    private Integer idOrigen;
    private java.time.LocalDateTime fechaExtraccion;
    private Boolean validado;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
