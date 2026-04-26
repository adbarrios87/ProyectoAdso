package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.DocumentosSociosProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos_socios_proveedor")
public class DocumentosSociosProveedorController {

    @Autowired
    private DocumentosSociosProveedorService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated DocumentosSociosProveedorCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<DocumentosSociosProveedorResponseDto>> getAll(){
        List<DocumentosSociosProveedorResponseDto> response = this.service.getAll();

        return ResponseDto.<List<DocumentosSociosProveedorResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<DocumentosSociosProveedorResponseDto> getDetail(@PathVariable Integer id){
        DocumentosSociosProveedorResponseDto response = this.service.getDetail(id);
        return ResponseDto.<DocumentosSociosProveedorResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated DocumentosSociosProveedorCreateRequestDto dto
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
