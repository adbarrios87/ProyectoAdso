package proyecto.ADSO.proveedores.controllers;

import proyecto.ADSO.proveedores.dtos.ResponseDto;
import proyecto.ADSO.proveedores.entites.FirmaTokenEntity;
import proyecto.ADSO.proveedores.services.FirmaTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/firmas")
public class FirmaTokenController {

    @Autowired
    private FirmaTokenService service;

    @PostMapping("/solicitar")
    public ResponseDto<Map<String, Object>> solicitarFirma(@RequestParam Integer idProveedor) {
        FirmaTokenEntity tokenEntity = service.solicitarFirma(idProveedor);
        
        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenEntity.getToken());
        result.put("fechaExpiracion", tokenEntity.getFechaExpiracion());
        // Enlace de firma apuntando al frontend local (se asume puerto 3000 o relativo)
        result.put("linkFirma", "/Frontend/sheets/sign_form.html?token=" + tokenEntity.getToken());

        return ResponseDto.<Map<String, Object>>builder()
                .data(result)
                .build();
    }

    @GetMapping("/validar")
    public ResponseDto<FirmaTokenEntity> validarToken(@RequestParam String token) {
        FirmaTokenEntity tokenEntity = service.validarToken(token);
        return ResponseDto.<FirmaTokenEntity>builder()
                .data(tokenEntity)
                .build();
    }

    @PostMapping("/firmar")
    public ResponseDto<Boolean> firmarFormulario(@RequestParam String token, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        boolean success = service.firmarFormulario(token, ip);
        return ResponseDto.<Boolean>builder()
                .data(success)
                .build();
    }
}
