package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.EvaluacionProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluacion_proveedor")
public class EvaluacionProveedorController {

    @Autowired
    private EvaluacionProveedorService service;

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseDto<List<EvaluacionProveedorResponseDto>> getByProveedor(@PathVariable Integer idProveedor) {
        List<EvaluacionProveedorResponseDto> response = this.service.getByIdProveedor(idProveedor);
        return ResponseDto.<List<EvaluacionProveedorResponseDto>>builder()
                .data(response)
                .build();
    }

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated EvaluacionProveedorCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<EvaluacionProveedorResponseDto>> getAll(){
        List<EvaluacionProveedorResponseDto> response = this.service.getAll();

        return ResponseDto.<List<EvaluacionProveedorResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<EvaluacionProveedorResponseDto> getDetail(@PathVariable Integer id){
        EvaluacionProveedorResponseDto response = this.service.getDetail(id);
        return ResponseDto.<EvaluacionProveedorResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated EvaluacionProveedorCreateRequestDto dto
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
