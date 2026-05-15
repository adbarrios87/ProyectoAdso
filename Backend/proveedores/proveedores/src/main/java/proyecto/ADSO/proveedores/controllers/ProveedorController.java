package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated ProveedorCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<ProveedorResponseDto>> getAll(){
        List<ProveedorResponseDto> response = this.service.getAll();

        return ResponseDto.<List<ProveedorResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<ProveedorResponseDto> getDetail(@PathVariable Integer id){
        ProveedorResponseDto response = this.service.getDetail(id);
        return ResponseDto.<ProveedorResponseDto>builder()
                .data(response)
                .build();
    }

    @GetMapping("/by-email")
    public ResponseDto<ProveedorResponseDto> getByEmail(@RequestParam String email){
        ProveedorResponseDto response = this.service.getByEmail(email);
        return ResponseDto.<ProveedorResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated ProveedorCreateRequestDto dto
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

    @PostMapping("/registro-completo")
    public ResponseDto<Boolean> registroCompleto(@RequestBody proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto request) {
        boolean success = this.service.registroCompleto(request);
        return ResponseDto.<Boolean>builder()
                .data(success)
                .build();
    }
}
