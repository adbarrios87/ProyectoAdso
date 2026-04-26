package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.HistorialUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial_usuario")
public class HistorialUsuarioController {

    @Autowired
    private HistorialUsuarioService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated HistorialUsuarioCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<HistorialUsuarioResponseDto>> getAll(){
        List<HistorialUsuarioResponseDto> response = this.service.getAll();

        return ResponseDto.<List<HistorialUsuarioResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<HistorialUsuarioResponseDto> getDetail(@PathVariable Integer id){
        HistorialUsuarioResponseDto response = this.service.getDetail(id);
        return ResponseDto.<HistorialUsuarioResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated HistorialUsuarioCreateRequestDto dto
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
