package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    @Autowired
    private ContactoService service;

    @PostMapping
    public ResponseDto<ContactoResponseDto> create(
            @RequestBody @Validated ContactoCreateRequestDto request
    ){
        proyecto.ADSO.proveedores.entites.ContactoEntity entity = this.service.create(request);
        ContactoResponseDto response = this.service.entityToDto(entity);

        return ResponseDto.<ContactoResponseDto>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<ContactoResponseDto>> getAll(){
        List<ContactoResponseDto> response = this.service.getAll();

        return ResponseDto.<List<ContactoResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<ContactoResponseDto> getDetail(@PathVariable Integer id){
        ContactoResponseDto response = this.service.getDetail(id);
        return ResponseDto.<ContactoResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated ContactoCreateRequestDto dto
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
