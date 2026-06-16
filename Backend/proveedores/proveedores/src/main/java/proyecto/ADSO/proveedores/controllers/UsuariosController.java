package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.*;
import proyecto.ADSO.proveedores.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import proyecto.ADSO.proveedores.dto.MenuDto;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService service;

    @GetMapping("/menu/{idRol}")
    public ResponseDto<List<MenuDto>> getMenu(@PathVariable Integer idRol) {
        List<MenuDto> response = this.service.getMenuByRole(idRol);
        return ResponseDto.<List<MenuDto>>builder()
                .data(response)
                .build();
    }

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated UsuariosCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<UsuariosResponseDto>> getAll(){
        List<UsuariosResponseDto> response = this.service.getAll();

        return ResponseDto.<List<UsuariosResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<UsuariosResponseDto> getDetail(@PathVariable Integer id){
        UsuariosResponseDto response = this.service.getDetail(id);
        return ResponseDto.<UsuariosResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Integer id,
            @RequestBody @Validated UsuariosCreateRequestDto dto
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

    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody @Validated LoginRequestDto request) {
        LoginResponseDto response = this.service.login(request);
        return ResponseDto.<LoginResponseDto>builder()
                .data(response)
                .build();
    }

    @PatchMapping("/{id}/foto")
    public ResponseDto<Boolean> updateFoto(@PathVariable Integer id, @RequestBody java.util.Map<String, String> body) {
        boolean response = this.service.updateFoto(id, body.get("fotoUrl"));
        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseDto<Boolean> updateEstado(@PathVariable Integer id, @RequestBody java.util.Map<String, Boolean> body) {
        boolean response = this.service.updateEstado(id, body.get("estadoUsuario"));
        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @PatchMapping("/{id}/requiere-actualizacion")
    public ResponseDto<Boolean> updateRequiereActualizacion(@PathVariable Integer id, @RequestBody java.util.Map<String, Boolean> body) {
        boolean response = this.service.updateRequiereActualizacion(id, body.get("requiereActualizacion"));
        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }
}
