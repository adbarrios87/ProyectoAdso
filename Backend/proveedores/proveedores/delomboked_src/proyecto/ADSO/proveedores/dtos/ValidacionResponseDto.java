package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidacionResponseDto {
    private Integer idValidacion;
    private Integer idUsuario;
    private Integer idProveedor;
    private Integer idCampoValidacion;
    private String valorWeb;
    private String valorDocumento;
    private Integer idDocumento;
    private Boolean resultadoValidacion;
    private java.time.LocalDate fechaValidacion;
    private String comentarios;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
