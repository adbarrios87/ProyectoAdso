package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponseDto {
    private Integer idProveedor;
    private Integer idUsuario;
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
    private Integer idEstadoProveedor;
    private java.time.LocalDateTime fechaAprobacion;
    private String bancoReferencia;
    private String tipoCuenta;
    private String numCuenta;
    private String ciiu;
    private String paginaWeb;
    private java.math.BigDecimal activos;
    private java.math.BigDecimal pasivos;
    private java.math.BigDecimal patrimonio;
    private java.math.BigDecimal totalIngresos;
    private java.math.BigDecimal totalGastos;
    private Boolean laftP1;
    private Boolean laftP2;
    private Boolean laftP3;
    private Boolean laftP4;
    private Boolean laftP5;
}
