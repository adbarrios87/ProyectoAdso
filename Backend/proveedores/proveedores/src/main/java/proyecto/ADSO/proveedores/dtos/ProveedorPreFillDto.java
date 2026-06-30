package proyecto.ADSO.proveedores.dtos;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorPreFillDto {
    private String nit;
    private String razonSocial;
    private String tipoPersona;
    private String direccion;
    private String correo;
    private String telefono;
    private String ciiu;
    private String municipio;
    private String departamento;
    private String pais;

    // Datos bancarios
    private String banco;
    private String tipoCuenta;
    private String numeroCuenta;

    // Datos de Referencia Comercial
    private String refComercialRazonSocial;
    private String refComercialNit;

    // Listas extraídas
    private List<RepresentantePreFill> representantes;
    private List<SocioPreFill> socios;
    private List<ValidacionCreateRequestDto> validaciones;

    // Control de validación
    private boolean extraccionExitosa;
    private String mensaje;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepresentantePreFill {
        private String nombres;
        private String apellidos;
        private String tipoDocumento; // CC, CE, etc.
        private String numeroDocumento;
        private String cargo;
        private String correo;
        private String telefono;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SocioPreFill {
        private String tipoPersona; // Natural o Jurídica
        private String nombreCompleto;
        private String tipoDocumento;
        private String numeroDocumento;
        private Double participacion;
        private String nacionalidad;
    }
}
