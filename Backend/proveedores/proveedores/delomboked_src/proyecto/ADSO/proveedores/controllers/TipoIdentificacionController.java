package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.TipoIdentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo_identificacion")
public class TipoIdentificacionController {

    @Autowired
    private TipoIdentificacionService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated TipoIdentificacionCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<TipoIdentificacionResponseDto>> getAll(){
        List<TipoIdentificacionResponseDto> response = this.service.getAll();

        return ResponseDto.<List<TipoIdentificacionResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<TipoIdentificacionResponseDto> getDetail(@PathVariable Integer id){
        TipoIdentificacionResponseDto response = this.service.getDetail(id);
        return ResponseDto.<TipoIdentificacionResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated TipoIdentificacionCreateRequestDto dto
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
