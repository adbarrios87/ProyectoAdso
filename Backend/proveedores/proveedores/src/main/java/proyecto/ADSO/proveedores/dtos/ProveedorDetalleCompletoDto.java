package proyecto.ADSO.proveedores.dtos;

import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDetalleCompletoDto {
    private ProveedorResponseDto proveedor;
    private List<ContactoResponseDto> contactos;
    private List<RepresentanteLegalResponseDto> representantes;
    private List<SociosProveedorResponseDto> socios;
    private List<DocumentosResponseDto> documentos;
    private List<EvaluacionProveedorResponseDto> evaluaciones;
    private List<ValidacionResponseDto> validaciones;
}
