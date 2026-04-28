package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.TipoNotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo_notificacion")
public class TipoNotificacionController {

    @Autowired
    private TipoNotificacionService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated TipoNotificacionCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<TipoNotificacionResponseDto>> getAll(){
        List<TipoNotificacionResponseDto> response = this.service.getAll();

        return ResponseDto.<List<TipoNotificacionResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<TipoNotificacionResponseDto> getDetail(@PathVariable Integer id){
        TipoNotificacionResponseDto response = this.service.getDetail(id);
        return ResponseDto.<TipoNotificacionResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated TipoNotificacionCreateRequestDto dto
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
