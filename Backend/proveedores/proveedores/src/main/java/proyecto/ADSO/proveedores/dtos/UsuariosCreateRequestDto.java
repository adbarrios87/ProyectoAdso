package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosCreateRequestDto {
    private String nombreUsuario;
    private String cargoUsuario;
    private String correoUsuario;
    private String contrasena;
    private Boolean estadoUsuario;
    private Integer idRol;
    private String fotoUrl;
    private java.time.LocalDateTime ultimoIngreso;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
}
