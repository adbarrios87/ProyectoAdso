package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.RepresentanteProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/representante_proveedor")
public class RepresentanteProveedorController {

    @Autowired
    private RepresentanteProveedorService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated RepresentanteProveedorCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<RepresentanteProveedorResponseDto>> getAll(){
        List<RepresentanteProveedorResponseDto> response = this.service.getAll();

        return ResponseDto.<List<RepresentanteProveedorResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<RepresentanteProveedorResponseDto> getDetail(@PathVariable Integer id){
        RepresentanteProveedorResponseDto response = this.service.getDetail(id);
        return ResponseDto.<RepresentanteProveedorResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated RepresentanteProveedorCreateRequestDto dto
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
