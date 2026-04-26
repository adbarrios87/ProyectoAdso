package proyecto.ADSO.proveedores.entites;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documentos")
public class DocumentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer idDocumento;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "fecha_emision")
    private java.time.LocalDate fechaEmision;

    @Column(name = "url_documento")
    private String urlDocumento;

    @Column(name = "almacenamiento")
    private String almacenamiento;

    @Column(name = "archivo_blob")
    private byte[] archivoBlob;

    @Column(name = "hash")
    private String hash;

    @Column(name = "tamano_bytes")
    private Long tamanoBytes;

    @Column(name = "fecha_carga")
    private java.time.LocalDate fechaCarga;

    @Column(name = "estado_documento")
    private Boolean estadoDocumento;

    @Column(name = "validado")
    private Boolean validado;

    @Column(name = "fecha_creado")
    private java.time.LocalDateTime fechaCreado;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "fecha_modificado")
    private java.time.LocalDateTime fechaModificado;

    @Column(name = "modificado_por")
    private Integer modificadoPor;

}
