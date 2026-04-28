package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactoCreateRequestDto {
    private String nombreContacto;
    private String cargoContacto;
    private Integer idTipoTelefono;
    private String telefonoContacto;
    private String correoContacto;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
}
