package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.EstadoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado_usuario")
public class EstadoUsuarioController {

    @Autowired
    private EstadoUsuarioService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated EstadoUsuarioCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<EstadoUsuarioResponseDto>> getAll(){
        List<EstadoUsuarioResponseDto> response = this.service.getAll();

        return ResponseDto.<List<EstadoUsuarioResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<EstadoUsuarioResponseDto> getDetail(@PathVariable Integer id){
        EstadoUsuarioResponseDto response = this.service.getDetail(id);
        return ResponseDto.<EstadoUsuarioResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated EstadoUsuarioCreateRequestDto dto
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
