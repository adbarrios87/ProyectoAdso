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

    @Autowired
    private proyecto.ADSO.proveedores.services.DocumentParserService parserService;

    @PostMapping("/pre-procesar")
    public ResponseDto<ProveedorPreFillDto> preProcesar(
            @RequestParam(value = "camara", required = false) org.springframework.web.multipart.MultipartFile camara,
            @RequestParam(value = "rut", required = false) org.springframework.web.multipart.MultipartFile rut,
            @RequestParam(value = "cedula", required = false) org.springframework.web.multipart.MultipartFile cedula,
            @RequestParam(value = "banco", required = false) org.springframework.web.multipart.MultipartFile banco,
            @RequestParam(value = "refCom", required = false) org.springframework.web.multipart.MultipartFile refCom,
            @RequestParam("tipoPersona") String tipoPersona
    ) {
        ProveedorPreFillDto result = this.parserService.preProcesarExpediente(camara, rut, cedula, banco, refCom, tipoPersona);
        return ResponseDto.<ProveedorPreFillDto>builder()
                .data(result)
                .build();
    }

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

    @GetMapping("/{id}/detalle-completo")
    public ResponseDto<ProveedorDetalleCompletoDto> getDetalleCompleto(@PathVariable Integer id){
        ProveedorDetalleCompletoDto response = this.service.getDetalleCompleto(id);
        return ResponseDto.<ProveedorDetalleCompletoDto>builder()
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

    @PatchMapping("/{id}/estado")
    public ResponseDto<Boolean> updateEstado(@PathVariable Integer id, @RequestBody java.util.Map<String, Object> body) {
        Integer idEstadoProveedor = body.get("idEstadoProveedor") != null ? Integer.valueOf(body.get("idEstadoProveedor").toString()) : null;
        Integer modificadoPor = body.get("modificadoPor") != null ? Integer.valueOf(body.get("modificadoPor").toString()) : null;
        boolean response = this.service.updateEstado(id, idEstadoProveedor, modificadoPor);
        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }
}
