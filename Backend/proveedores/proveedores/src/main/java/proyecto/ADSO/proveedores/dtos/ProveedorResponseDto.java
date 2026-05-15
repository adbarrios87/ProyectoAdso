package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponseDto {
    private Integer idProveedor;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;
    private String digitoVerificacion;
    private String razonSocial;
    private String nombres;
    private String apellidos;
    private Integer idTipoPersona;
    private String telefonoPrincipal;
    private Integer idTipoTelefono;
    private String correoPrincipal;
    private Integer idActualizacionProveedor;
    private Boolean requiereActualizacion;
    private String descripcion;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
