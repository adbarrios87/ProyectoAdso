package proyecto.ADSO.proveedores.services;

import proyecto.ADSO.proveedores.entites.*;
import proyecto.ADSO.proveedores.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FirmaTokenService {

    @Autowired
    private FirmaTokenRepository repository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private ProveedorContactoRepository proveedorContactoRepository;
    
    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private RepresentanteProveedorRepository representanteProveedorRepository;

    @Autowired
    private RepresentanteLegalRepository representanteLegalRepository;

    @Autowired
    private SociosProveedorRepository sociosProveedorRepository;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private EmailService emailService;

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

            try {
                // 1. Obtener Forma de Pago
                Optional<FormaDePagoEntity> optPago = formaDePagoRepository.findByIdProveedor(proveedor.getIdProveedor());
                FormaDePagoEntity formaPago = optPago.orElse(null);

                // 2. Obtener Contactos
                List<ContactoEntity> contactos = new ArrayList<>();
                List<ProveedorContactoEntity> pcList = proveedorContactoRepository.findByIdProveedor(proveedor.getIdProveedor());
                if (pcList != null) {
                    for (ProveedorContactoEntity pc : pcList) {
                        Optional<ContactoEntity> optC = contactoRepository.findById(pc.getIdContacto());
                        optC.ifPresent(contactos::add);
                    }
                }

                // 3. Obtener Representantes
                List<RepresentanteLegalEntity> representantes = new ArrayList<>();
                List<RepresentanteProveedorEntity> rpList = representanteProveedorRepository.findByIdProveedor(proveedor.getIdProveedor());
                if (rpList != null) {
                    for (RepresentanteProveedorEntity rp : rpList) {
                        Optional<RepresentanteLegalEntity> optR = representanteLegalRepository.findById(rp.getIdRepresentanteLegal());
                        optR.ifPresent(representantes::add);
                    }
                }

                // 4. Obtener Socios
                List<SociosProveedorEntity> socios = sociosProveedorRepository.findByIdProveedor(proveedor.getIdProveedor());

                // 5. Determinar Correos de destino
                List<String> toEmails = new ArrayList<>();
                if (proveedor.getCorreoPrincipal() != null && !proveedor.getCorreoPrincipal().isEmpty()) {
                    toEmails.add(proveedor.getCorreoPrincipal());
                }
                for (RepresentanteLegalEntity r : representantes) {
                    if (r.getCorreo() != null && !r.getCorreo().isEmpty()) {
                        toEmails.add(r.getCorreo());
                    }
                }
                if (toEmails.isEmpty()) {
                    toEmails.add("pareregrc@gmail.com"); // fallback
                }

                // 6. Configurar ruta de guardado
                String providerName = proveedor.getRazonSocial() != null && !proveedor.getRazonSocial().isEmpty() 
                    ? proveedor.getRazonSocial() : (proveedor.getNombres() + " " + proveedor.getApellidos()).trim();
                if (providerName.isEmpty()) providerName = proveedor.getNumeroIdentificacion();
                String folderName = providerName.replaceAll("[\\\\/:*?\"<>|]", "_") + "_" + java.time.Year.now().getValue();
                String basePath = "G:\\My Drive\\0. SENA - ADSO\\DocumentosProyecto\\" + folderName;

                // 7. Generar PDF
                File pdfFile = pdfGenerationService.generateSignedPdf(proveedor, tokenEntity, formaPago, contactos, representantes, socios, basePath);

                // 8. Enviar Correo
                emailService.sendSignedPdfEmail(toEmails.toArray(new String[0]), pdfFile);

            } catch (Exception e) {
                System.err.println("Error generando PDF o enviando correo de firma: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return true;
    }
}
