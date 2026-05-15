package proyecto.ADSO.proveedores.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private String titulo;
    private String url;
    private String icono;
    private List<MenuDto> submenus; // Para los dropdowns
}
