package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.ResponseDto;
import proyecto.ADSO.proveedores.dtos.ValidacionFinalCreateRequestDto;
import proyecto.ADSO.proveedores.services.ValidacionFinalService;
import proyecto.ADSO.proveedores.entites.ValidacionFinalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/validacion-final")
public class ValidacionFinalController {

    @Autowired
    private ValidacionFinalService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated ValidacionFinalCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<ValidacionFinalEntity>> getAll(){
        List<ValidacionFinalEntity> response = this.service.getAll();
        return ResponseDto.<List<ValidacionFinalEntity>>builder()
                .data(response)
                .build();
    }
}
