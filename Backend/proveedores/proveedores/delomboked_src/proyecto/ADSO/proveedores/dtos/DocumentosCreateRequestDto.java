package proyecto.ADSO.proveedores.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentosCreateRequestDto {
    private Integer idProveedor;
    private Integer idTipoDocumento;
    private String numeroDocumento;
    private java.time.LocalDate fechaEmision;
    private String urlDocumento;
    private String almacenamiento;
    private byte[] archivoBlob;
    private String hash;
    private Long tamanoBytes;
    private java.time.LocalDate fechaCarga;
    private Boolean estadoDocumento;
    private Boolean validado;
    private java.time.LocalDateTime fechaCreado;
    private Integer creadoPor;
    private java.time.LocalDateTime fechaModificado;
    private Integer modificadoPor;
}
