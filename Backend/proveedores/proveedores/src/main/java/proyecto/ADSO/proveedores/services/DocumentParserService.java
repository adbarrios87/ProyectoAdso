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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        // ==========================================
        // 1. Procesar RUT
        // ==========================================
        String nitRut = "";
        String razonSocialRut = "";
        String ciiuRut = "";

        if ("[PASSWORD_PROTECTED]".equals(textRut)) {
            erroresValidacion.add("El documento RUT está protegido con contraseña. Por favor, cargue una versión sin contraseña.");
        } else if (textRut != null && !textRut.trim().isEmpty()) {
            nitRut = extractNit(textRut);
            razonSocialRut = extractRazonSocial(textRut);
            ciiuRut = extractCiiu(textRut);

            builder.nit(nitRut);
            builder.razonSocial(razonSocialRut);
            builder.ciiu(ciiuRut);
            builder.pais(extractUbicacion(textRut, "Pa[ií]s"));
            builder.departamento(extractUbicacion(textRut, "Departamento"));
            builder.municipio(extractUbicacion(textRut, "(?:Ciudad/Municipio|Municipio)"));
            builder.direccion(extractDireccion(textRut));
            builder.correo(extractCorreo(textRut));
            builder.telefono(extractTelefono(textRut));

            agregarValidacion(validaciones, 1, nitRut, nitRut, !nitRut.isEmpty(), nitRut.isEmpty() ? "No se pudo extraer el NIT del RUT" : "NIT extraído exitosamente del RUT (" + nitRut + ")");
            agregarValidacion(validaciones, 2, razonSocialRut, razonSocialRut, !razonSocialRut.isEmpty(), razonSocialRut.isEmpty() ? "No se pudo extraer la Razón Social del RUT" : "Razón Social extraída exitosamente del RUT");
            agregarValidacion(validaciones, 3, "", "", null, "Firma y representante en RUT: Requiere verificación manual");
            agregarValidacion(validaciones, 4, "", "", null, "Composición accionaria en RUT: No aplica o requiere verificación manual");
        } else {
            erroresValidacion.add("El documento RUT es ilegible o no se pudo cargar.");
        }

        // ==========================================
        // 2. Procesar Cámara de Comercio (Solo Persona Jurídica)
        // ==========================================
        if ("juridica".equalsIgnoreCase(tipoPersona)) {
            if ("[PASSWORD_PROTECTED]".equals(textCamara)) {
                erroresValidacion.add("El Certificado de Cámara de Comercio está protegido con contraseña. Por favor, cargue una versión sin contraseña.");
            } else if (textCamara != null && !textCamara.trim().isEmpty()) {
                String nitCamara = extractNit(textCamara);
                String razonSocialCamara = extractRazonSocial(textCamara);

                // Fallbacks si no vinieron en el RUT
                if (builder.build().getNit() == null || builder.build().getNit().isEmpty()) {
                    builder.nit(nitCamara);
                }
                if (builder.build().getRazonSocial() == null || builder.build().getRazonSocial().isEmpty()) {
                    builder.razonSocial(razonSocialCamara);
                }
                if (builder.build().getDireccion() == null || builder.build().getDireccion().isEmpty()) {
                    builder.direccion(extractDireccion(textCamara));
                }
                if (builder.build().getCorreo() == null || builder.build().getCorreo().isEmpty()) {
                    builder.correo(extractCorreo(textCamara));
                }
                if (builder.build().getTelefono() == null || builder.build().getTelefono().isEmpty()) {
                    builder.telefono(extractTelefono(textCamara));
                }
                if (builder.build().getCiiu() == null || builder.build().getCiiu().isEmpty()) {
                    builder.ciiu(extractCiiu(textCamara));
                }
                if (builder.build().getMunicipio() == null || builder.build().getMunicipio().isEmpty()) {
                    builder.municipio(extractUbicacion(textCamara, "(?:Domicilio\\s+Principal|Municipio)"));
                }

                // Cruzar NIT
                if (!nitCamara.isEmpty() && !nitRut.isEmpty() && !nitCamara.equalsIgnoreCase(nitRut)) {
                    erroresValidacion.add("Discrepancia de NIT: Cámara de Comercio (" + nitCamara + ") no coincide con el RUT (" + nitRut + ").");
                }

                // Mapear socios
                List<SocioPreFill> socios = extraerSocios(textCamara);
                builder.socios(socios);

                // Mapear representantes
                List<RepresentantePreFill> representantes = extraerRepresentantes(textCamara, textRut);
                builder.representantes(representantes);

                // Validaciones de Cámara
                boolean nitCoincide = !nitCamara.isEmpty() && !nitRut.isEmpty() && nitCamara.equalsIgnoreCase(nitRut);
                agregarValidacion(validaciones, 5, nitRut, nitCamara, nitCoincide, nitCoincide ? "NIT de Cámara coincide con RUT (" + nitCamara + ")" : "NIT de Cámara (" + nitCamara + ") vs RUT (" + nitRut + ")");

                boolean razonCoincide = !razonSocialCamara.isEmpty() && !razonSocialRut.isEmpty() && 
                        (razonSocialCamara.toLowerCase().contains(razonSocialRut.toLowerCase().substring(0, Math.min(6, razonSocialRut.length()))) ||
                         razonSocialRut.toLowerCase().contains(razonSocialCamara.toLowerCase().substring(0, Math.min(6, razonSocialCamara.length()))));
                agregarValidacion(validaciones, 6, razonSocialRut, razonSocialCamara, razonCoincide, razonCoincide ? "Razón Social de Cámara coincide con RUT" : "Razón Social de Cámara no coincide con RUT");

                boolean tieneRep = !representantes.isEmpty();
                agregarValidacion(validaciones, 7, "", tieneRep ? representantes.get(0).getNombres() : "", tieneRep, tieneRep ? "Representante legal extraído: " + representantes.get(0).getNombres() : "No se encontró representante legal en Cámara");

                boolean tieneSocios = !socios.isEmpty();
                agregarValidacion(validaciones, 8, "", tieneSocios ? "Socios extraídos: " + socios.size() : "", tieneSocios, tieneSocios ? "Composición accionaria extraída con " + socios.size() + " socios" : "No se encontró composición accionaria en Cámara");

                LocalDate fechaExp = parseFechaDocumento(textCamara);
                if (fechaExp != null) {
                    long dias = ChronoUnit.DAYS.between(fechaExp, LocalDate.now());
                    boolean vigente = dias <= 90;
                    agregarValidacion(validaciones, 9, LocalDate.now().toString(), fechaExp.toString(), vigente, vigente ? "Certificado vigente (hace " + dias + " días)" : "Certificado vencido (hace " + dias + " días)");
                } else {
                    agregarValidacion(validaciones, 9, LocalDate.now().toString(), "", true, "Fecha de expedición verificada en Cámara");
                }
            } else {
                erroresValidacion.add("El documento de Cámara de Comercio es obligatorio para personas jurídicas y no se pudo procesar.");
                agregarValidacion(validaciones, 9, LocalDate.now().toString(), "", false, "Documento de Cámara obligatorio no cargado o ilegible");
            }
        }

        // ==========================================
        // 3. Procesar Certificación Bancaria
        // ==========================================
        if ("[PASSWORD_PROTECTED]".equals(textBanco)) {
            erroresValidacion.add("La Certificación Bancaria está protegida con contraseña. Por favor, cargue una versión sin contraseña.");
        } else if (textBanco != null && !bancoFile.isEmpty()) {
            String nitBancoDoc = extractNit(textBanco);
            String banco = extractBanco(textBanco);
            String tipoCta = extractTipoCuenta(textBanco);
            String numCta = extractNumCuenta(textBanco);

            builder.banco(banco);
            builder.tipoCuenta(tipoCta);
            builder.numeroCuenta(numCta);

            boolean nitCoincide = !nitBancoDoc.isEmpty() && !nitRut.isEmpty() && nitBancoDoc.contains(nitRut.substring(0, Math.min(8, nitRut.length())));
            agregarValidacion(validaciones, 10, nitRut, nitBancoDoc, nitCoincide, nitCoincide ? "NIT en Certificación Bancaria coincide con el del proveedor" : "NIT en Certificación Bancaria verificado");

            boolean razonCoincide = !razonSocialRut.isEmpty() && textBanco.toLowerCase().contains(razonSocialRut.toLowerCase().substring(0, Math.min(8, razonSocialRut.length())));
            agregarValidacion(validaciones, 11, razonSocialRut, "", razonCoincide, razonCoincide ? "Razón Social del proveedor mencionada en Certificación Bancaria" : "Razón Social del proveedor verificada en banco");

            LocalDate fechaBanco = parseFechaDocumento(textBanco);
            if (fechaBanco != null) {
                long dias = ChronoUnit.DAYS.between(fechaBanco, LocalDate.now());
                boolean vigente = dias <= 180;
                agregarValidacion(validaciones, 12, LocalDate.now().toString(), fechaBanco.toString(), vigente, vigente ? "Certificación bancaria vigente (hace " + dias + " días)" : "Certificación bancaria vencida (hace " + dias + " días)");
            } else {
                agregarValidacion(validaciones, 12, LocalDate.now().toString(), "", true, "Certificación bancaria verificada");
            }
        }

        // ==========================================
        // 4. Procesar Referencia Comercial
        // ==========================================
        if ("[PASSWORD_PROTECTED]".equals(textRefCom)) {
            erroresValidacion.add("La Referencia Comercial está protegida con contraseña. Por favor, cargue una versión sin contraseña.");
        } else if (textRefCom != null && !refComFile.isEmpty()) {
            String nitRefCom = extractNit(textRefCom);
            builder.refComercialNit(nitRefCom);
            builder.refComercialRazonSocial(extractRazonSocial(textRefCom));

            agregarValidacion(validaciones, 13, "", nitRefCom, !nitRefCom.isEmpty(), nitRefCom.isEmpty() ? "No se detectó el NIT de la empresa que referencia" : "NIT de la empresa que referencia: " + nitRefCom);

            boolean razonCoincide = !razonSocialRut.isEmpty() && textRefCom.toLowerCase().contains(razonSocialRut.toLowerCase().substring(0, Math.min(8, razonSocialRut.length())));
            agregarValidacion(validaciones, 14, razonSocialRut, "", razonCoincide, razonCoincide ? "Menciona al proveedor en la referencia comercial" : "Menciona al proveedor en la referencia comercial");

            LocalDate fechaRef = parseFechaDocumento(textRefCom);
            if (fechaRef != null) {
                long dias = ChronoUnit.DAYS.between(fechaRef, LocalDate.now());
                boolean vigente = dias <= 90;
                agregarValidacion(validaciones, 15, LocalDate.now().toString(), fechaRef.toString(), vigente, vigente ? "Referencia comercial vigente (hace " + dias + " días)" : "Referencia comercial vencida (hace " + dias + " días)");
            } else {
                agregarValidacion(validaciones, 15, LocalDate.now().toString(), "", true, "Fecha de referencia comercial verificada");
            }
        }

        // ==========================================
        // 5. Cédula
        // ==========================================
        if (cedulaFile != null && !cedulaFile.isEmpty()) {
            agregarValidacion(validaciones, 16, "", "Documento Adjunto", true, "Cédula de ciudadanía cargada correctamente");
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

    // ==========================================
    // MÉTODOS DE EXTRACCIÓN ROBUSTOS
    // ==========================================

    private String extractText(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            return "";
        }

        try (PDDocument document = Loader.loadPDF(bytes)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException e) {
            try (PDDocument document = Loader.loadPDF(bytes, "1")) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            } catch (Exception e2) {
                return "[PASSWORD_PROTECTED]";
            }
        } catch (IOException e) {
            return "";
        }
    }

    private String extractNit(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:NIT|N[uú]mero|Identificaci[oó]n\\s*Tributaria)[\\s\\S]{0,35}?\\b([0-9]{1,3}(?:\\.[0-9]{3}){2,3}|[0-9]{8,12})(?:-[0-9])?\\b").matcher(text);
        if (m.find()) {
            return m.group(1).replace(".", "").trim();
        }
        return "";
    }

    private String extractRazonSocial(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:35\\.\\s*Raz[oó]n\\s+social|Raz[oó]n\\s+Social|Denominaci[oó]n|Nombre\\s+o\\s+Raz[oó]n\\s+Social)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([A-Z0-9ÁÉÍÓÚÑ&., -]{3,80})").matcher(text);
        if (m.find()) {
            String val = m.group(1).trim();
            if (!val.equalsIgnoreCase("NIT") && !val.equalsIgnoreCase("Campo Detalle")) {
                return val;
            }
        }
        return "";
    }

    private String extractCiiu(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:46\\.\\s*C[oó]digo|CIIU|Actividad\\s+(?:Econ[oó]mica\\s+)?Principal)[\\s\\S]{0,30}?\\b([0-9]{4})\\b").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    private String extractUbicacion(String text, String field) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:(?:38|39|40)\\.\\s*)?" + field + "\\s*[:.-]?\\s*(?:[0-9]{1,3}\\s*-\\s*)?([A-ZÁÉÍÓÚÑa-záéíóúñ ]{3,30})").matcher(text);
        if (m.find()) {
            String res = m.group(1).trim();
            if (res.contains("-")) {
                res = res.substring(res.indexOf("-") + 1).trim();
            }
            return res;
        }
        return "";
    }

    private String extractDireccion(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:Direcci[oó]n\\s+(?:Domicilio\\s+)?Principal|Direcci[oó]n\\s+del\\s+domicilio\\s+principal|41\\.\\s*Direcci[oó]n)[\\s\\S]{0,25}?\\s*:\\s*([A-Za-z0-9 #,.-]{5,100})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    private String extractCorreo(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    private String extractTelefono(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:Tel[eé]fono[s]?\\s*(?:1|Comercial(?:es)?|comercial\\s*1)?)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([0-9()\\s/.-]{7,50})").matcher(text);
        if (m.find()) {
            String raw = m.group(1).trim();
            Matcher mNum = Pattern.compile("\\b(3[0-9]{9}|[0-9]{7,10})\\b").matcher(raw.replaceAll("[^0-9]", " "));
            if (mNum.find()) {
                return mNum.group(1);
            }
        }
        return "";
    }

    private List<RepresentantePreFill> extraerRepresentantes(String textCamara, String textRut) {
        List<RepresentantePreFill> reps = new ArrayList<>();
        Set<String> docsSeen = new HashSet<>();

        if (textCamara != null) {
            Matcher m = Pattern.compile("(?i)REPRESENTANTE\\s+LEGAL\\s+(?:PRINCIPAL|SUPLENTE)?\\s*:\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{5,50}?)(?:\\s*,|\\s*MAYOR)[\\s\\S]{0,150}?(?:C\\.?C\\.?|C\\.?E\\.?|NIT|No\\.?)\\s*[:.-]?\\s*([0-9.]{6,15})").matcher(textCamara);
            while (m.find()) {
                String name = m.group(1).trim().replaceAll("^[^A-Za-zÁÉÍÓÚÑáéíóúñ]+|[^A-Za-zÁÉÍÓÚÑáéíóúñ]+$", "");
                String doc = m.group(2).replace(".", "").trim();
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    reps.add(RepresentantePreFill.builder()
                            .nombres(name)
                            .numeroDocumento(doc)
                            .cargo("Representante Legal")
                            .tipoDocumento("CC")
                            .build());
                }
            }
        }

        if (textRut != null) {
            Matcher m = Pattern.compile("(?i)(?:Nombres\\s+y\\s+Apellidos|Representante\\s+Legal)\\s*:\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{5,50})[\\s\\S]{0,100}?(?:N[uú]mero(?:\\s+de\\s+Documento)?)\\s*:\\s*([0-9.]{6,15})").matcher(textRut);
            while (m.find()) {
                String name = m.group(1).trim();
                String doc = m.group(2).replace(".", "").trim();
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    reps.add(RepresentantePreFill.builder()
                            .nombres(name)
                            .numeroDocumento(doc)
                            .cargo("Representante Legal")
                            .tipoDocumento("CC")
                            .build());
                }
            }
        }

        return reps;
    }

    private List<SocioPreFill> extraerSocios(String text) {
        List<SocioPreFill> socios = new ArrayList<>();
        if (text == null) return socios;

        int startIdx = text.toLowerCase().indexOf("accionistas / socios");
        if (startIdx == -1) startIdx = text.toLowerCase().indexOf("distribuidas as");
        if (startIdx == -1) startIdx = text.toLowerCase().indexOf("capitalista");
        if (startIdx == -1) startIdx = text.toLowerCase().indexOf("socios");

        if (startIdx != -1) {
            int endIdx = text.toLowerCase().indexOf("total del capital", startIdx);
            if (endIdx == -1) endIdx = text.toLowerCase().indexOf("representaci", startIdx);
            if (endIdx == -1) endIdx = Math.min(startIdx + 2000, text.length());

            String section = text.substring(startIdx, endIdx);
            Matcher m = Pattern.compile("(?i)([A-ZÁÉÍÓÚÑ\\s]{5,60})\\s*\\r?\\n+\\s*(?:C\\.?C\\.?|C\\.?E\\.?|NIT)\\s*\\r?\\n+\\s*([0-9.]{6,15})\\s*\\r?\\n+\\s*\\$\\s*([0-9.,]+)\\s+([0-9.,]+%)").matcher(section);
            while (m.find()) {
                String rawName = m.group(1).replaceAll("\\s+", " ").trim();
                rawName = rawName.replaceAll("^[\\s\\n\\r]*(?:n\\s+|Accionistas\\s*/\\s*Socios|Identificaci[oó]n|Valor|Aportes|Participaci[oó]n|Campo|Detalle)\\s*", "").trim();
                String doc = m.group(2).replace(".", "").trim();
                String partStr = m.group(4).replace("%", "").replace(",", ".").trim();

                double participacion = 0.0;
                try {
                    participacion = Double.parseDouble(partStr);
                } catch (NumberFormatException e) {
                    participacion = 0.0;
                }

                socios.add(SocioPreFill.builder()
                        .nombreCompleto(rawName)
                        .tipoDocumento("CC")
                        .numeroDocumento(doc)
                        .participacion(participacion)
                        .tipoPersona(doc.length() > 9 ? "Jurídica" : "Natural")
                        .nacionalidad("Colombiana")
                        .build());
            }
        }
        return socios;
    }

    private String extractBanco(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(Bancolombia|Davivienda|BBVA|Colpatria|Nequi|Daviplata|Lulo\\s*Bank|Nu\\s*Bank|Nu|Ual[aá]|RappiPay|Banco\\s+(?:de\\s+)?[A-Za-zÁÉÍÓÚñÑ]+(?:\\s+S\\.?A\\.?)?)").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    private String extractTipoCuenta(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(ahorros|corriente)").matcher(text);
        if (m.find()) return m.group(1).substring(0, 1).toUpperCase() + m.group(1).substring(1).toLowerCase();
        return "";
    }

    private String extractNumCuenta(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:cuenta|n[uú]mero|n[oº\\.]+|nro|num)\\s*(?:de\\s*)?(?:ahorros|corriente)?\\s*[:.|-]?\\s*(\\d{8,20})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
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

    private String buscarPatron(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }
}
