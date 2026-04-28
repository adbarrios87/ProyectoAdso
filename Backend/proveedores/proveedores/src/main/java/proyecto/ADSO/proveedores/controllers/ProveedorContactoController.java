package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.ProveedorContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor_contacto")
public class ProveedorContactoController {

    @Autowired
    private ProveedorContactoService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated ProveedorContactoCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<ProveedorContactoResponseDto>> getAll(){
        List<ProveedorContactoResponseDto> response = this.service.getAll();

        return ResponseDto.<List<ProveedorContactoResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<ProveedorContactoResponseDto> getDetail(@PathVariable Integer id){
        ProveedorContactoResponseDto response = this.service.getDetail(id);
        return ResponseDto.<ProveedorContactoResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated ProveedorContactoCreateRequestDto dto
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
