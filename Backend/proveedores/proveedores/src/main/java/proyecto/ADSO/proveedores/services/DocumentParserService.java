package proyecto.ADSO.proveedores.services;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proyecto.ADSO.proveedores.dtos.ProveedorPreFillDto;
import proyecto.ADSO.proveedores.dtos.ProveedorPreFillDto.RepresentantePreFill;
import proyecto.ADSO.proveedores.dtos.ProveedorPreFillDto.SocioPreFill;
import proyecto.ADSO.proveedores.dtos.ValidacionCreateRequestDto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DocumentParserService {

    public ProveedorPreFillDto preProcesarExpediente(
            MultipartFile camaraFile,
            MultipartFile rutFile,
            MultipartFile cedulaFile,
            MultipartFile bancoFile,
            MultipartFile refComFile,
            String tipoPersona) {
        ProveedorPreFillDto.ProveedorPreFillDtoBuilder builder = ProveedorPreFillDto.builder();
        builder.tipoPersona(tipoPersona);
        builder.extraccionExitosa(true);

        String textCamara = extractText(camaraFile);
        String textRut = extractText(rutFile);
        String textBanco = extractText(bancoFile);
        String textRefCom = extractText(refComFile);

        List<String> erroresValidacion = new ArrayList<>();
        List<ValidacionCreateRequestDto> validaciones = new ArrayList<>();

        // 1. Procesar RUT (Obligatorio para Natural y Jurídica)
        String nitRut = "";
        String razonSocialRut = "";
        String ciiuRut = "";
        if ("[PASSWORD_PROTECTED]".equals(textRut)) {
            erroresValidacion.add(
                    "El documento RUT está protegido con contraseña. Por favor, cargue una versión sin contraseña.");
        } else if (textRut != null && !textRut.trim().isEmpty()) {
            nitRut = buscarPatron(textRut, "(?i)NIT\\s*[:.-]?\\s*(\\d{8,12})");
            razonSocialRut = buscarPatron(textRut,
                    "(?i)35\\\\.\\\\s*Raz[oó]n\\\\s+social\\\\s*\\\\r?\\\\n+([A-Z0-9Ñ&., -]{3,100})");
            ciiuRut = buscarPatron(textRut, "(?i)46\\.\\s*C[oó]digo\\s*\\r?\\n+\\s*(\\d\\s*\\d\\s*\\d\\s*\\d)\\b");

            builder.nit(nitRut);
            builder.razonSocial(razonSocialRut);
            builder.ciiu(ciiuRut);
            builder.pais(buscarPatron(textRut,
                    "(?i)38\\\\.\\\\s*Pa[ií]s\\\\s*(?:\\\\r?\\\\n)?\\\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{4,30})"));
            builder.departamento(buscarPatron(textRut,
                    "(?i)39\\\\.\\\\s*Departamento\\\\s*(?:\\\\r?\\\\n)?\\\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{4,30})"));
            builder.municipio(buscarPatron(textRut,
                    "(?i)40\\\\.\\\\s*Ciudad/Municipio\\\\s*(?:\\\\r?\\\\n)?\\\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{4,30})"));

            agregarValidacion(validaciones, 1, nitRut, nitRut, !nitRut.isEmpty(), nitRut.isEmpty() ? "No se pudo extraer el NIT del RUT" : "NIT extraído exitosamente del RUT");
            agregarValidacion(validaciones, 2, razonSocialRut, razonSocialRut, !razonSocialRut.isEmpty(), razonSocialRut.isEmpty() ? "No se pudo extraer la Razón Social del RUT" : "Razón Social extraída exitosamente del RUT");
            agregarValidacion(validaciones, 3, "", "", null, "Firma y representante en RUT: Requiere verificación manual");
            agregarValidacion(validaciones, 4, "", "", null, "Composición accionaria en RUT: No aplica o requiere verificación manual");
        } else {
            erroresValidacion.add("El documento RUT es ilegible o no se pudo cargar.");
        }

        // 2. Procesar Cámara de Comercio (Solo para Personas Jurídicas)
        if ("juridica".equalsIgnoreCase(tipoPersona)) {
            if ("[PASSWORD_PROTECTED]".equals(textCamara)) {
                erroresValidacion.add(
                        "El Certificado de Cámara de Comercio está protegido con contraseña. Por favor, cargue una versión sin contraseña.");
            } else if (textCamara != null && !textCamara.trim().isEmpty()) {
                // Cruzar NIT y Razón Social
                String nitCamara = buscarPatron(textCamara, "(?i)NIT\\s*[:.-]?\\s*(\\d{8,12})");
                String razonSocialCamara = buscarPatron(textCamara,
                        "(?i)(?:Raz[oó]n\\s+Social|Denominaci[oó]n|Nombre)[\\s\\S]{1,50}?\\r?\\n([A-Z0-9 ]{3,50})");

                if (nitCamara != null && !nitCamara.isEmpty() && !nitCamara.equalsIgnoreCase(nitRut)) {
                    erroresValidacion.add("Discrepancia de NIT: Cámara de Comercio (" + nitCamara
                            + ") no coincide con el RUT (" + nitRut + ").");
                }

                // Extraer y asignar dirección, correo y teléfono desde la Cámara de Comercio
                String direccionCamara = buscarPatron(textCamara,
                        "(?i)Direcci[oó]n\\s+del\\s+domicilio\\s+principal\\s*:\\s*([A-Za-z0-9 #,.-]{5,100})");
                String correoCamara = buscarPatron(textCamara,
                        "(?i)Correo\\s+electr[oó]nico\\s*:\\s*([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})");
                String telefonoCamara = buscarPatron(textCamara,
                        "(?i)Tel[eé]fono\\s+comercial\\s+1\\s*:\\s*(\\d{7,15})");

                if (direccionCamara != null && !direccionCamara.isEmpty()) {
                    builder.direccion(direccionCamara);
                }
                if (correoCamara != null && !correoCamara.isEmpty()) {
                    builder.correo(correoCamara);
                }
                if (telefonoCamara != null && !telefonoCamara.isEmpty()) {
                    builder.telefono(telefonoCamara);
                }

                String ciiuCamara = buscarPatron(textCamara,
                        "(?i)Actividad\\s+principal\\s+(?:C[oó]digo\\s+)?CIIU\\s*[:.-]?\\s*(\\d{4})");
                if (ciiuCamara != null && !ciiuCamara.isEmpty()) {
                    builder.ciiu(ciiuCamara);
                }

                // Mapear socios
                List<SocioPreFill> socios = extraerSocios(textCamara);
                builder.socios(socios);

                // Mapear representantes
                List<RepresentantePreFill> representantes = extraerRepresentantes(textCamara);
                builder.representantes(representantes);

                // Validaciones de Cámara de Comercio
                boolean nitCoincide = nitCamara != null && !nitCamara.isEmpty() && nitCamara.equalsIgnoreCase(nitRut);
                agregarValidacion(validaciones, 5, nitRut, nitCamara, nitCoincide, nitCoincide ? "NIT de Cámara coincide con RUT" : "NIT de Cámara no coincide con el RUT (" + nitCamara + " vs " + nitRut + ")");
                
                boolean razonCoincide = razonSocialCamara != null && !razonSocialCamara.isEmpty() && razonSocialCamara.equalsIgnoreCase(razonSocialRut);
                agregarValidacion(validaciones, 6, razonSocialRut, razonSocialCamara, razonCoincide, razonCoincide ? "Razón Social de Cámara coincide con RUT" : "Razón Social de Cámara no coincide con RUT");
                
                boolean tieneRep = representantes != null && !representantes.isEmpty();
                agregarValidacion(validaciones, 7, "", tieneRep ? representantes.get(0).getNombres() : "", tieneRep, tieneRep ? "Representante legal extraído: " + representantes.get(0).getNombres() : "No se encontró representante legal en Cámara");
                
                boolean tieneSocios = socios != null && !socios.isEmpty();
                agregarValidacion(validaciones, 8, "", tieneSocios ? "Socios extraídos: " + socios.size() : "", tieneSocios, tieneSocios ? "Composición accionaria extraída con " + socios.size() + " socios" : "No se encontró composición accionaria en Cámara");
                
                LocalDate fechaExp = parseFechaDocumento(textCamara);
                if (fechaExp != null) {
                    long dias = ChronoUnit.DAYS.between(fechaExp, LocalDate.now());
                    boolean vigente = dias <= 90;
                    agregarValidacion(validaciones, 9, LocalDate.now().toString(), fechaExp.toString(), vigente, vigente ? "Certificado vigente (hace " + dias + " días)" : "Certificado vencido (hace " + dias + " días)");
                } else {
                    agregarValidacion(validaciones, 9, LocalDate.now().toString(), "", false, "No se pudo extraer la fecha de expedición de la Cámara");
                }

            } else {
                erroresValidacion.add(
                        "El documento de Cámara de Comercio es obligatorio para personas jurídicas y no se pudo procesar.");
                agregarValidacion(validaciones, 9, LocalDate.now().toString(), "", false, "Documento de Cámara obligatorio no cargado o ilegible");
            }
        }

        // 3. Procesar Certificación Bancaria
        if ("[PASSWORD_PROTECTED]".equals(textBanco)) {
            erroresValidacion.add(
                    "La Certificación Bancaria está protegida con contraseña. Por favor, cargue una versión sin contraseña.");
        } else if (textBanco != null && !bancoFile.isEmpty()) {
            String nitBancoDoc = buscarPatron(textBanco, "(?i)NIT\\s*[:.-]?\\s*(\\d{8,12})");
            
            builder.banco(buscarPatron(textBanco,
                    "(?i)(Bancolombia|Davivienda|BBVA|Colpatria|Nequi|Daviplata|Lulo\\s*Bank|Nu\\s*Bank|Nu|Ual[aá]|RappiPay|Banco\\s+(?:de\\s+)?[A-Za-zÁÉÍÓÚñÑ]+)"));
            builder.tipoCuenta(buscarPatron(textBanco, "(?i)(ahorros|corriente)"));
            builder.numeroCuenta(buscarPatron(textBanco,
                    "(?i)(?:cuenta|n[uú]mero|n[oº\\.]+|nro|num)\\s*(?:de\\s*)?(?:ahorros|corriente)?\\s*[:.|-]?\\s*(\\d{8,20})"));

            boolean nitCoincide = nitBancoDoc != null && nitRut != null && !nitRut.isEmpty() && nitBancoDoc.contains(nitRut.substring(0, Math.min(8, nitRut.length())));
            agregarValidacion(validaciones, 10, nitRut, nitBancoDoc, nitCoincide, nitCoincide ? "NIT en Certificación Bancaria coincide con el del proveedor" : "NIT en Certificación Bancaria no coincide");
            
            boolean razonCoincide = razonSocialRut != null && !razonSocialRut.isEmpty() && textBanco.toLowerCase().contains(razonSocialRut.toLowerCase().substring(0, Math.min(10, razonSocialRut.length())));
            agregarValidacion(validaciones, 11, razonSocialRut, "", razonCoincide, razonCoincide ? "Razón Social del proveedor mencionada en Certificación Bancaria" : "Razón Social del proveedor no mencionada en Certificación Bancaria");
            
            LocalDate fechaBanco = parseFechaDocumento(textBanco);
            if (fechaBanco != null) {
                long dias = ChronoUnit.DAYS.between(fechaBanco, LocalDate.now());
                boolean vigente = dias <= 180;
                agregarValidacion(validaciones, 12, LocalDate.now().toString(), fechaBanco.toString(), vigente, vigente ? "Certificación bancaria vigente (hace " + dias + " días)" : "Certificación bancaria vencida (hace " + dias + " días)");
            } else {
                agregarValidacion(validaciones, 12, LocalDate.now().toString(), "", null, "Fecha de certificación bancaria no detectada. Requiere verificación visual");
            }
        }

        // 5. Procesar Referencia Comercial (Validar Razón Social y NIT)
        if ("[PASSWORD_PROTECTED]".equals(textRefCom)) {
            erroresValidacion.add(
                    "La Referencia Comercial está protegida con contraseña. Por favor, cargue una versión sin contraseña.");
        } else if (textRefCom != null && !refComFile.isEmpty()) {
            String nitRefCom = buscarPatron(textRefCom, "(?i)NIT\\s*[:.-]?\\s*(\\d{8,12})");
            
            builder.refComercialNit(nitRefCom);
            builder.refComercialRazonSocial(buscarPatron(textRefCom,
                    "(?i)(?:a\\s+nombre\\s+de|certifica\\s+a|hace\\s+referencia\\s+a)\\s*[:.-]?\\s*([A-Za-z0-9 ]{3,40})"));

            agregarValidacion(validaciones, 13, "", nitRefCom, nitRefCom != null && !nitRefCom.isEmpty(), nitRefCom == null || nitRefCom.isEmpty() ? "No se detectó el NIT de la empresa que referencia" : "NIT de la empresa que referencia: " + nitRefCom);
            
            boolean razonCoincide = razonSocialRut != null && !razonSocialRut.isEmpty() && textRefCom.toLowerCase().contains(razonSocialRut.toLowerCase().substring(0, Math.min(10, razonSocialRut.length())));
            agregarValidacion(validaciones, 14, razonSocialRut, "", razonCoincide, razonCoincide ? "Menciona al proveedor en la referencia comercial" : "No menciona al proveedor en la referencia comercial");
            
            LocalDate fechaRef = parseFechaDocumento(textRefCom);
            if (fechaRef != null) {
                long dias = ChronoUnit.DAYS.between(fechaRef, LocalDate.now());
                boolean vigente = dias <= 90;
                agregarValidacion(validaciones, 15, LocalDate.now().toString(), fechaRef.toString(), vigente, vigente ? "Referencia comercial vigente (hace " + dias + " días)" : "Referencia comercial vencida (hace " + dias + " días)");
            } else {
                agregarValidacion(validaciones, 15, LocalDate.now().toString(), "", null, "Fecha de referencia comercial no detectada. Requiere verificación visual");
            }
        }

        // 6. Procesar Cédula (Criterio 16)
        if (cedulaFile != null && !cedulaFile.isEmpty()) {
            agregarValidacion(validaciones, 16, "", "Imagen", null, "Validación manual pendiente del Representante Legal contra la Cédula cargada");
        }

        builder.validaciones(validaciones);

        if (!erroresValidacion.isEmpty()) {
            builder.extraccionExitosa(false);
            builder.mensaje(String.join(" | ", erroresValidacion));
        } else {
            builder.mensaje("Extracción y validación cruzada completadas exitosamente.");
        }

        return builder.build();
    }

    private String extractText(MultipartFile file) {
        if (file == null || file.isEmpty())
            return null;
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            return "";
        }

        // 1. Intentar cargar sin contraseña
        try (PDDocument document = Loader.loadPDF(bytes)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException e) {
            // 2. Si tiene contraseña, intentar con la clave por defecto "1"
            try (PDDocument document = Loader.loadPDF(bytes, "1")) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            } catch (org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException e2) {
                // Clave diferente a "1"
                return "[PASSWORD_PROTECTED]";
            } catch (IOException e2) {
                return "";
            }
        } catch (IOException e) {
            return "";
        }
    }

    private String buscarPatron(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }

    private List<SocioPreFill> extraerSocios(String text) {
        List<SocioPreFill> socios = new ArrayList<>();

        // 1. Extraer el Capital Total
        double totalCapital = 1.0; // Valor por defecto para evitar división por cero
        Pattern pCapital = Pattern
                .compile("(?i)(?:Total\\s+del\\s+capital|Capital\\s+y\\s+socios)\\s*[:.-]?\\s*\\$\\s*([0-9,.]+)");
        Matcher mCapital = pCapital.matcher(text);
        if (mCapital.find()) {
            String capStr = mCapital.group(1).replaceAll("[.,]", "");
            try {
                totalCapital = Double.parseDouble(capStr);
            } catch (NumberFormatException e) {
                // Si no se puede parsear, queda en 1.0
            }
        }

        // 2. Aislar la sección de Socios
        int startIdx = text.toLowerCase().indexOf("capitalista");
        if (startIdx == -1) {
            startIdx = text.toLowerCase().indexOf("socios");
        }
        if (startIdx == -1) {
            startIdx = text.toLowerCase().indexOf("capital");
        }

        if (startIdx != -1) {
            int endIdx = text.toLowerCase().indexOf("total del capital", startIdx);
            if (endIdx == -1) {
                endIdx = text.toLowerCase().indexOf("representacion legal", startIdx);
            }
            if (endIdx == -1) {
                endIdx = Math.min(startIdx + 2000, text.length());
            }

            if (endIdx > startIdx) {
                String section = text.substring(startIdx, endIdx);
                String[] lines = section.split("\\r?\\n");

                String currentName = "";
                // Regex para buscar el documento y aporte
                Pattern pDocAporte = Pattern.compile(
                        "(?i)(C\\.C\\.|C\\.E\\.|NIT|CC|CE|PASAPORTE)\\s*[:.-]?\\s*([0-9.-]+)\\s+\\$\\s*([0-9,.]+)");

                for (String line : lines) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }

                    Matcher mDoc = pDocAporte.matcher(line);
                    if (mDoc.find()) {
                        if (!currentName.isEmpty()) {
                            String tipoDocStr = mDoc.group(1).replace(".", "").toUpperCase().trim(); // CC, CE, NIT etc.
                            String numDoc = mDoc.group(2).replace(".", "").replace("-", "").trim();
                            String aporteStr = mDoc.group(3).replaceAll("[.,]", "");

                            double aporte = 0.0;
                            double participacion = 0.0;
                            try {
                                aporte = Double.parseDouble(aporteStr);
                                if (totalCapital > 0) {
                                    participacion = (aporte / totalCapital) * 100.0;
                                    // Redondear a dos decimales
                                    participacion = Math.round(participacion * 100.0) / 100.0;
                                }
                            } catch (NumberFormatException e) {
                                // Ignorar error de parseo de aporte
                            }

                            socios.add(SocioPreFill.builder()
                                    .nombreCompleto(currentName)
                                    .tipoDocumento(tipoDocStr)
                                    .numeroDocumento(numDoc)
                                    .participacion(participacion)
                                    .tipoPersona(
                                            tipoDocStr.contains("NIT") || numDoc.length() > 9 ? "Jurídica" : "Natural")
                                    .nacionalidad("Colombiana")
                                    .build());

                            currentName = ""; // Resetear para el siguiente socio
                        }
                    } else {
                        // Si la línea es un candidato para nombre de socio:
                        // No debe contener palabras clave de control y debe contener solo letras,
                        // espacios o acentos, y tener cierta longitud.
                        String lowerLine = line.toLowerCase();
                        if (!lowerLine.contains("socios") &&
                                !lowerLine.contains("capital") &&
                                !lowerLine.contains("distribuidos") &&
                                !lowerLine.contains("valor_aportes") &&
                                !lowerLine.contains("capitalista") &&
                                line.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ ]{5,60}$")) {
                            currentName = line;
                        }
                    }
                }
            }
        }

        return socios;
    }

    private List<RepresentantePreFill> extraerRepresentantes(String text) {
        List<RepresentantePreFill> reps = new ArrayList<>();
        int startIdx = text.toLowerCase().indexOf("representacion legal");
        if (startIdx != -1) {
            String section = text.substring(startIdx, Math.min(startIdx + 2000, text.length()));
            Pattern p = Pattern.compile("([A-Za-z ]{5,40})\\s+(?:CC|CE)\\s*[:.-]?\\s*(\\d{6,12})");
            Matcher m = p.matcher(section);
            while (m.find()) {
                reps.add(RepresentantePreFill.builder()
                        .nombres(m.group(1).trim())
                        .numeroDocumento(m.group(2).trim())
                        .cargo("Representante Legal")
                        .tipoDocumento("CC")
                        .build());
            }
        }
        return reps;
    }

    private void agregarValidacion(List<ValidacionCreateRequestDto> list, int idCampo, String valorWeb, String valorDoc, Boolean resultado, String comentarios) {
        list.add(ValidacionCreateRequestDto.builder()
                .idCampoValidacion(idCampo)
                .valorWeb(valorWeb != null ? valorWeb : "")
                .valorDocumento(valorDoc != null ? valorDoc : "")
                .resultadoValidacion(resultado)
                .fechaValidacion(java.time.LocalDate.now())
                .comentarios(comentarios != null ? comentarios : "")
                .fechaCreado(java.time.LocalDateTime.now())
                .activo(true)
                .build());
    }

    private LocalDate parseFechaDocumento(String text) {
        if (text == null) return null;
        String fechaStr = buscarPatron(text, "(?i)(?:fecha|expedido|expedici[oó]n|emisi[oó]n|del\\s+d[ií]a)\\s*[:.-]?\\s*(\\d{2}/\\d{2}/\\d{4}|\\d{4}-\\d{2}-\\d{2})");
        if (fechaStr == null || fechaStr.isEmpty()) {
            fechaStr = buscarPatron(text, "\\b(\\d{2}/\\d{2}/\\d{4}|\\d{4}-\\d{2}-\\d{2})\\b");
        }
        if (fechaStr != null && !fechaStr.isEmpty()) {
            try {
                DateTimeFormatter formatter = fechaStr.contains("/")
                        ? DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        : DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(fechaStr, formatter);
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }
}
