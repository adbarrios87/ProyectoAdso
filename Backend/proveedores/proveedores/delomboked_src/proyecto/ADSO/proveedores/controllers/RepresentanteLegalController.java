package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.RepresentanteLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/representante_legal")
public class RepresentanteLegalController {

    @Autowired
    private RepresentanteLegalService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated RepresentanteLegalCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<RepresentanteLegalResponseDto>> getAll(){
        List<RepresentanteLegalResponseDto> response = this.service.getAll();

        return ResponseDto.<List<RepresentanteLegalResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<RepresentanteLegalResponseDto> getDetail(@PathVariable Integer id){
        RepresentanteLegalResponseDto response = this.service.getDetail(id);
        return ResponseDto.<RepresentanteLegalResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated RepresentanteLegalCreateRequestDto dto
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
