package proyecto.ADSO.proveedores.dtos;

import lombok.Data;

@Data
public class ProveedorCompletoDto {
    private String idUsuarioAsignado;
    private EmpresaDto empresa;
    private UbicacionDto ubicacion;
    private BancariaDto bancaria;
    private PersonaDto contacto1;
    private PersonaDto representante1;
    private PersonaDto contacto2;
    private PersonaDto representante2;
    private PersonaDto socio1;
    private PersonaDto socio2;

    @Data
    public static class EmpresaDto {
        private String nombre;
        private String tipoPersona;
        private String categoria;
        private String tipoDocumento;
        private String numeroDocumento;
        private String telefono;
        private String correo;
    }

    @Data
    public static class UbicacionDto {
        private String departamento;
        private String ciudad;
        private String direccion;
    }

    @Data
    public static class BancariaDto {
        private String tipoCuenta;
        private String numeroCuenta;
        private String metodoPago;
    }

    @Data
    public static class PersonaDto {
        private String nombres;
        private String apellidos;
        private String cargo;
        private String tipoDocumento;
        private String numeroDocumento;
        private String telefono;
        private String correo;
    }
}
