package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.EvaluacionRiesgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluacion_riesgos")
public class EvaluacionRiesgosController {

    @Autowired
    private EvaluacionRiesgosService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated EvaluacionRiesgosCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<EvaluacionRiesgosResponseDto>> getAll(){
        List<EvaluacionRiesgosResponseDto> response = this.service.getAll();

        return ResponseDto.<List<EvaluacionRiesgosResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<EvaluacionRiesgosResponseDto> getDetail(@PathVariable Integer id){
        EvaluacionRiesgosResponseDto response = this.service.getDetail(id);
        return ResponseDto.<EvaluacionRiesgosResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated EvaluacionRiesgosCreateRequestDto dto
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
