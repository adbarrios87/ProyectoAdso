package proyecto.ADSO.proveedores.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProveedorCompletoDto {
    private String idUsuarioAsignado;
    private EmpresaDto empresa;
    private ContactoDto contacto;
    private List<RepresentanteDto> representantes;
    private List<SocioDto> socios;
    private BancariaDto bancaria;
    private FinancieraDto financiera;
    private LaftDto laft;

    @Data
    public static class EmpresaDto {
        private String nombre;
        private Integer idTipoPersona;
        private String categoria;
        private Integer idTipoIdentificacion;
        private String numeroIdentificacion;
        private String ciiu;
        private String paginaWeb;
        private Integer idTipoTelefono;
        private String telefono;
        private String correo;
        private Integer idPais;
        private Integer idDepartamento;
        private Integer idMunicipio;
        private String direccion;
    }

    @Data
    public static class ContactoDto {
        private String nombres;
        private String apellidos;
        private String cargo;
        private Integer idTipoIdentificacion;
        private String numeroIdentificacion;
        private Integer idTipoTelefono;
        private String telefono;
        private String correo;
    }

    @Data
    public static class RepresentanteDto {
        private Boolean esPrincipal;
        private String nombres;
        private String apellidos;
        private String cargo;
        private Integer idTipoIdentificacion;
        private String numeroIdentificacion;
        private String nacionalidad;
        private Integer idTipoTelefono;
        private String telefono;
        private String correo;
    }

    @Data
    public static class SocioDto {
        private Integer idTipoPersona;
        private String nombreCompleto;
        private Integer idTipoIdentificacion;
        private String numeroIdentificacion;
        private BigDecimal participacion;
        private String nacionalidad;
    }

    @Data
    public static class BancariaDto {
        private String tipoCuenta;
        private String numeroCuenta;
        private String banco;
        private Integer idMetodoPago;
    }

    @Data
    public static class FinancieraDto {
        private BigDecimal activos;
        private BigDecimal pasivos;
        private BigDecimal patrimonio;
        private BigDecimal totalIngresos;
        private BigDecimal totalGastos;
    }

    @Data
    public static class LaftDto {
        private Boolean p1;
        private Boolean p2;
        private Boolean p3;
        private Boolean p4;
        private Boolean p5;
    }
}
