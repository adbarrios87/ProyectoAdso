package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.entites.FirmaTokenEntity;
import proyecto.ADSO.proveedores.entites.ProveedorEntity;
import proyecto.ADSO.proveedores.repositories.FirmaTokenRepository;
import proyecto.ADSO.proveedores.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FirmaTokenService {

    @Autowired
    private FirmaTokenRepository repository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public FirmaTokenEntity solicitarFirma(Integer idProveedor) {
        // Verificar si el proveedor existe
        Optional<ProveedorEntity> optP = proveedorRepository.findById(idProveedor);
        if (optP.isEmpty()) {
            throw new RuntimeException("El proveedor con ID " + idProveedor + " no existe");
        }

        // Inactivar tokens anteriores
        List<FirmaTokenEntity> activeTokens = repository.findAll();
        for (FirmaTokenEntity tok : activeTokens) {
            if (tok.getIdProveedor().equals(idProveedor) && !tok.getUtilizado()) {
                tok.setUtilizado(true);
                repository.save(tok);
            }
        }

        // Crear token nuevo
        String tokenString = UUID.randomUUID().toString();
        FirmaTokenEntity tokenEntity = FirmaTokenEntity.builder()
                .idProveedor(idProveedor)
                .token(tokenString)
                .fechaExpiracion(LocalDateTime.now().plusHours(24))
                .utilizado(false)
                .build();

        return repository.save(tokenEntity);
    }

    public FirmaTokenEntity validarToken(String token) {
        Optional<FirmaTokenEntity> opt = repository.findByToken(token);
        if (opt.isEmpty()) {
            throw new RuntimeException("El token de firma no existe");
        }
        FirmaTokenEntity tokenEntity = opt.get();
        if (tokenEntity.getUtilizado()) {
            throw new RuntimeException("El enlace de firma ya ha sido utilizado");
        }
        if (LocalDateTime.now().isAfter(tokenEntity.getFechaExpiracion())) {
            throw new RuntimeException("El enlace de firma ha expirado");
        }
        return tokenEntity;
    }

    public boolean firmarFormulario(String token, String ip) {
        FirmaTokenEntity tokenEntity = validarToken(token);
        tokenEntity.setUtilizado(true);
        tokenEntity.setFechaFirmado(LocalDateTime.now());
        tokenEntity.setIpFirma(ip);
        repository.save(tokenEntity);

        // Actualizar estado del proveedor a "Firmado" (ID 5)
        Optional<ProveedorEntity> optP = proveedorRepository.findById(tokenEntity.getIdProveedor());
        if (optP.isPresent()) {
            ProveedorEntity proveedor = optP.get();
            proveedor.setIdEstadoProveedor(5); 
            proveedor.setFechaModificado(LocalDateTime.now());
            proveedorRepository.save(proveedor);
        }

        return true;
    }
}
