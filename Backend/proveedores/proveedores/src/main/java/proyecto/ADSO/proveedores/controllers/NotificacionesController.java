package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated NotificacionesCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<NotificacionesResponseDto>> getAll(){
        List<NotificacionesResponseDto> response = this.service.getAll();

        return ResponseDto.<List<NotificacionesResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<NotificacionesResponseDto> getDetail(@PathVariable Integer id){
        NotificacionesResponseDto response = this.service.getDetail(id);
        return ResponseDto.<NotificacionesResponseDto>builder()
                .data(response)
                .build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseDto<List<NotificacionesResponseDto>> getByUserId(@PathVariable Integer id){
        List<NotificacionesResponseDto> response = this.service.getByUserId(id);
        return ResponseDto.<List<NotificacionesResponseDto>>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated NotificacionesCreateRequestDto dto
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
