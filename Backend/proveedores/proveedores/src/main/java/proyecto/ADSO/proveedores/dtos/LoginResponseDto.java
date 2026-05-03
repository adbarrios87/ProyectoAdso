package proyecto.ADSO.proveedores.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private boolean successful;
    private String message;
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private Integer idRol;
    private String fotoUrl;
}
