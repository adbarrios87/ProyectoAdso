package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.DocumentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentosController {

    @Autowired
    private DocumentosService service;

    @PostMapping("/upload")
    public ResponseDto<DocumentosResponseDto> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idProveedor") Integer idProveedor,
            @RequestParam("idTipoDocumento") Integer idTipoDocumento,
            @RequestParam("creadoPor") Integer creadoPor
    ) throws IOException {
        DocumentosResponseDto response = this.service.uploadDocumento(file, idProveedor, idTipoDocumento, creadoPor);
        return ResponseDto.<DocumentosResponseDto>builder()
                .data(response)
                .build();
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseDto<List<DocumentosResponseDto>> getByProveedor(@PathVariable Integer idProveedor) {
        List<DocumentosResponseDto> response = this.service.getByIdProveedor(idProveedor);
        return ResponseDto.<List<DocumentosResponseDto>>builder()
                .data(response)
                .build();
    }

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated DocumentosCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<DocumentosResponseDto>> getAll(){
        List<DocumentosResponseDto> response = this.service.getAll();

        return ResponseDto.<List<DocumentosResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<DocumentosResponseDto> getDetail(@PathVariable Integer id){
        DocumentosResponseDto response = this.service.getDetail(id);
        return ResponseDto.<DocumentosResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated DocumentosCreateRequestDto dto
    ){
        boolean response = this.service.update(id, dto);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<GeneralResponseDto> delete(@PathVariable Integer id){
        this.service.delete(id);

        return ResponseDto.<GeneralResponseDto>builder()
                .data(GeneralResponseDto.builder()
                        .successful(true)
                        .build())
                .build();
    }
}
