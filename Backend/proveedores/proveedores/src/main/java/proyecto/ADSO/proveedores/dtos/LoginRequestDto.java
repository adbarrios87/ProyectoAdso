package proyecto.ADSO.proveedores.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    private String usuario; // Puede ser correo o nombre de usuario
    private String contrasena;
}
