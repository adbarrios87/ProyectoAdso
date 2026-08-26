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

    @PostMapping("/recuperar-contrasena")
    public ResponseDto<Boolean> recuperarContrasena(
            @RequestParam String correo,
            @RequestHeader(value = "Origin", required = false) String origin,
            @RequestHeader(value = "Referer", required = false) String referer
    ) {
        String baseUrl = origin;
        if (baseUrl == null && referer != null) {
            try {
                java.net.URI uri = new java.net.URI(referer);
                baseUrl = uri.getScheme() + "://" + uri.getAuthority();
            } catch (Exception e) {
                // ignore
            }
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:3000"; // fallback default
        }
        try {
            boolean response = this.service.solicitarRecuperacion(correo, baseUrl);
            return ResponseDto.<Boolean>builder()
                    .data(response)
                    .build();
        } catch (Exception ex) {
            System.err.println("Error en recuperarContrasena: " + ex.getMessage());
            ex.printStackTrace();
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("/validar-token-recuperacion")
    public ResponseDto<Boolean> validarTokenRecuperacion(@RequestParam String token) {
        boolean response = this.service.validarTokenRecuperacion(token);
        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @PostMapping("/restablecer-contrasena")
    public ResponseDto<Boolean> restablecerContrasena(@RequestBody java.util.Map<String, String> body) {
        String token = body.get("token");
        String contrasena = body.get("contrasena");
        boolean response = this.service.restablecerContrasena(token, contrasena);
        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }
}
