package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepresentanteProveedorCreateRequestDto {
    private Integer idRepresentanteLegal;
    private Integer idProveedor;
    private String cargo;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
    private Boolean activo;
    private String REFERENCES;
    private String REFERENCES;
    private String REFERENCES;
    private String REFERENCES;
}
