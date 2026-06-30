package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepresentanteLegalResponseDto {
    private Integer idRepresentanteLegal;
    private Integer idTipoIdentificacion;
    private Integer idDocumento;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private Integer idTipoTelefono;
    private String telefono;
    private String correo;
    private Integer idUsuario;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
    private String nacionalidad;
}
