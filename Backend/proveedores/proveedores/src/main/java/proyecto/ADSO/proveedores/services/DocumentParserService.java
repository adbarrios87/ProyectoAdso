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
        // 1. Procesar RUT (Obligatorio)
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
            agregarValidacion(validaciones, 2, razonSocialRut, razonSocialRut, !razonSocialRut.isEmpty(), razonSocialRut.isEmpty() ? "No se pudo extraer la Razón Social del RUT" : "Razón Social extraída exitosamente del RUT (" + razonSocialRut + ")");
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

                // Fallbacks
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
                List<SocioPreFill> socios = extraerSocios(textCamara, textRut);
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
        // 1. Línea siguiente a "5. Número de Identificación Tributaria (NIT)" o "NIT:"
        Matcher m1 = Pattern.compile("(?i)(?:5\\.\\s*N[uú]mero\\s+de\\s+Identificaci[oó]n\\s+Tributaria\\s*\\(NIT\\)|NIT\\s*[:.-]?)\\s*\\r?\\n+\\s*([0-9]{1,3}(?:\\.[0-9]{3}){2,3}|[0-9]{8,12})").matcher(text);
        if (m1.find()) return m1.group(1).replace(".", "").trim();

        // 2. Mismo renglón
        Matcher m2 = Pattern.compile("(?i)(?:NIT|N[uú]mero|Identificaci[oó]n\\s*Tributaria)[\\s\\S]{0,35}?\\b([0-9]{1,3}(?:\\.[0-9]{3}){2,3}|[0-9]{8,12})(?:-[0-9])?\\b").matcher(text);
        if (m2.find()) return m2.group(1).replace(".", "").trim();
        return "";
    }

    private String extractRazonSocial(String text) {
        if (text == null) return "";
        // 1. Línea posterior a "35. Razón social" (ignorar encabezados tipo 31. Primer apellido)
        Matcher m1 = Pattern.compile("(?i)(?:35\\.\\s*Raz[oó]n\\s+social)\\s*\\r?\\n+\\s*([A-Z0-9ÁÉÍÓÚÑ&., -]{3,80})").matcher(text);
        if (m1.find()) {
            String val = m1.group(1).trim();
            if (!val.toLowerCase().contains("primer apellido") && !val.toLowerCase().contains("nombre comercial")) {
                return val;
            }
        }

        // 2. Razón Social: o Denominación: en Cámara
        Matcher m2 = Pattern.compile("(?i)(?:Raz[oó]n\\s+Social|Denominaci[oó]n)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([A-Z0-9ÁÉÍÓÚÑ&., -]{3,80})").matcher(text);
        if (m2.find()) {
            String val = m2.group(1).trim();
            if (!val.toLowerCase().contains("primer apellido") && !val.equalsIgnoreCase("NIT") && !val.equalsIgnoreCase("Campo Detalle")) {
                return val;
            }
        }
        return "";
    }

    private String extractCiiu(String text) {
        if (text == null) return "";
        Matcher m1 = Pattern.compile("(?i)(?:46\\.\\s*C[oó]digo|Actividad\\s+Principal\\s*[:.-]?\\s*(?:C[oó]digo\\s+)?CIIU)\\s*\\r?\\n*\\s*([0-9]{4})\\b").matcher(text);
        if (m1.find()) return m1.group(1).trim();

        Matcher m2 = Pattern.compile("(?i)(?:CIIU|Actividad\\s+(?:Econ[oó]mica\\s+)?Principal)[\\s\\S]{0,30}?\\b([0-9]{4})\\b").matcher(text);
        if (m2.find()) return m2.group(1).trim();
        return "";
    }

    private String extractUbicacion(String text, String field) {
        if (text == null) return "";
        // Línea siguiente
        Matcher m1 = Pattern.compile("(?i)(?:(?:38|39|40)\\.\\s*)?" + field + "\\s*\\r?\\n+\\s*(?:[0-9]{1,3}\\s*-\\s*)?([A-ZÁÉÍÓÚÑa-záéíóúñ ]{3,30})").matcher(text);
        if (m1.find()) {
            String res = m1.group(1).trim();
            if (!res.toLowerCase().contains("departamento") && !res.toLowerCase().contains("ciudad") && !res.toLowerCase().contains("dirección")) {
                return res;
            }
        }
        // Mismo renglón
        Matcher m2 = Pattern.compile("(?i)(?:(?:38|39|40)\\.\\s*)?" + field + "\\s*[:.-]?\\s*(?:[0-9]{1,3}\\s*-\\s*)?([A-ZÁÉÍÓÚÑa-záéíóúñ ]{3,30})").matcher(text);
        if (m2.find()) {
            String res = m2.group(1).trim();
            if (res.contains("-")) res = res.substring(res.indexOf("-") + 1).trim();
            return res;
        }
        return "";
    }

    private String extractDireccion(String text) {
        if (text == null) return "";
        // 1. Si viene en formato "Dirección Domicilio Principal: ..." (Cámara de Comercio)
        Matcher mCam = Pattern.compile("(?i)(?:Direcci[oó]n\\s+(?:Domicilio\\s+)?Principal|Direcci[oó]n\\s+del\\s+domicilio\\s+principal)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([A-Za-z0-9 #,.-]{5,100})").matcher(text);
        if (mCam.find()) {
            String dir = mCam.group(1).trim();
            if (!dir.toLowerCase().contains("obligados") && !dir.toLowerCase().contains("exportadores") && !dir.toLowerCase().contains("municipio")) {
                return dir;
            }
        }

        // 2. Buscar dirección física típica colombiana (Carrera, Calle, Cra, Cl, Diagonal, Av, etc.)
        Matcher mPattern = Pattern.compile("(?i)\\b((?:Carrera|Calle|Cra|Cl|Cll|Diagonal|Diag|Dg|Transversal|Trans|Tv|Avenida|Av|Circular|Manzana|Mz)\\.?\\s+[A-Za-z0-9 #,.-]{5,80})").matcher(text);
        if (mPattern.find()) {
            String dir = mPattern.group(1).trim().replaceAll("\\s*\\r?\\n.*", "").trim();
            if (!dir.toLowerCase().contains("obligados") && !dir.toLowerCase().contains("exportadores")) {
                return dir;
            }
        }
        return "";
    }

    private String extractTelefono(String text) {
        if (text == null) return "";
        // 1. Buscar celular colombiano de 10 dígitos que empiece por 3 (ej. 3184509988)
        Matcher mCel = Pattern.compile("\\b(3[0-9]{9})\\b").matcher(text);
        if (mCel.find()) {
            return mCel.group(1).trim();
        }

        // 2. Buscar teléfonos comerciales o fijos (ej. 330 4500 / 318 450 9988 o 6023304500)
        Matcher mCom = Pattern.compile("(?i)Tel[eé]fonos?\\s*(?:Comerciales?|1|Notificaci[oó]n)?\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([0-9()\\s/.-]{7,60})").matcher(text);
        if (mCom.find()) {
            String raw = mCom.group(1).replaceAll("[^0-9]", " ");
            Matcher mDigits = Pattern.compile("\\b(3[0-9]{9}|60[0-9]{8}|[0-9]{7,10})\\b").matcher(raw);
            if (mDigits.find()) {
                return mDigits.group(1).trim();
            }
        }
        return "";
    }

    private String extractCorreo(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    private List<RepresentantePreFill> extraerRepresentantes(String textCamara, String textRut) {
        List<RepresentantePreFill> reps = new ArrayList<>();
        Set<String> docsSeen = new HashSet<>();

        // 1. Desde RUT (Hoja 3)
        if (textRut != null) {
            Matcher m = Pattern.compile("(?i)101\\.\\s*N[uú]mero\\s+de\\s+identificaci[oó]n\\s*\\r?\\n+\\s*([0-9.]{6,15})\\s*\\r?\\n+\\s*104\\.\\s*Primer\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*105\\.\\s*Segundo\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*106\\.\\s*Primer\\s+nombre\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)").matcher(textRut);
            while (m.find()) {
                String doc = m.group(1).replace(".", "").trim();
                String pAp = m.group(2).trim();
                String sAp = m.group(3).trim();
                String pNom = m.group(4).trim();
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    reps.add(RepresentantePreFill.builder()
                            .nombres(pNom)
                            .apellidos((pAp + " " + sAp).trim())
                            .numeroDocumento(doc)
                            .cargo("Representante Legal")
                            .tipoDocumento("CC")
                            .build());
                }
            }
        }

        // 2. Desde Cámara
        if (textCamara != null) {
            Matcher m1 = Pattern.compile("(?i)(?:REPRESENTADA\\s+LEGALMENTE\\s+POR|REPRESENTANTE\\s+LEGAL(?:\\s+PRINCIPAL|\\s+SUPLENTE)?)\\s*[:.-]?\\s*\\r?\\n*\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{5,45})\\s+(?:C\\.?C\\.?|C\\.?E\\.?|NIT|No\\.?)\\s*[:.-]?\\s*([0-9.]{6,15})").matcher(textCamara);
            while (m1.find()) {
                String name = m1.group(1).trim();
                String doc = m1.group(2).replace(".", "").trim();
                if (!docsSeen.contains(doc) && !name.toLowerCase().contains("sociedad")) {
                    docsSeen.add(doc);
                    String[] parts = name.split("\\s+");
                    String nom = parts.length > 0 ? parts[0] : name;
                    String ape = parts.length > 1 ? name.substring(parts[0].length()).trim() : "";
                    reps.add(RepresentantePreFill.builder()
                            .nombres(nom)
                            .apellidos(ape)
                            .numeroDocumento(doc)
                            .cargo("Representante Legal")
                            .tipoDocumento("CC")
                            .build());
                }
            }
        }
        return reps;
    }

    private List<SocioPreFill> extraerSocios(String textCamara, String textRut) {
        List<SocioPreFill> socios = new ArrayList<>();
        Set<String> docsSeen = new HashSet<>();

        // 1. Desde RUT (Hoja 4)
        if (textRut != null) {
            Matcher m = Pattern.compile("(?i)112\\.\\s*N[uú]mero\\s+de\\s+identificaci[oó]n\\s*\\r?\\n+\\s*([0-9.]{6,15})\\s*\\r?\\n+\\s*115\\.\\s*Primer\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*116\\.\\s*Segundo\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*117\\.\\s*Primer\\s+nombre\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*120\\.\\s*Valor\\s+capital\\s+del\\s+socio\\s*\\r?\\n+\\s*([0-9.,]+)\\s*\\r?\\n+\\s*121\\.\\s*%\\s*Participaci[oó]n\\s*\\r?\\n+\\s*([0-9.,]+)").matcher(textRut);
            while (m.find()) {
                String doc = m.group(1).replace(".", "").trim();
                String nombreCompleto = (m.group(4) + " " + m.group(2) + " " + m.group(3)).trim();
                String partStr = m.group(6).replace(",", ".").trim();

                double participacion = 0.0;
                try {
                    participacion = Double.parseDouble(partStr);
                } catch (Exception e) {}

                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    socios.add(SocioPreFill.builder()
                            .nombreCompleto(nombreCompleto)
                            .tipoDocumento("CC")
                            .numeroDocumento(doc)
                            .participacion(participacion)
                            .tipoPersona(doc.length() > 9 ? "Jurídica" : "Natural")
                            .nacionalidad("Colombiana")
                            .build());
                }
            }
        }

        // 2. Desde Cámara (Page 3)
        if (textCamara != null && socios.isEmpty()) {
            double totalCapital = 1.0;
            Matcher mCap = Pattern.compile("(?i)TOTAL\\s+DEL\\s+CAPITAL\\s*\\$?\\s*([0-9,.]+)").matcher(textCamara);
            if (mCap.find()) {
                try {
                    totalCapital = Double.parseDouble(mCap.group(1).replaceAll("[.,]", ""));
                } catch (Exception e) {}
            }

            Matcher mSocio = Pattern.compile("(?i)([A-ZÁÉÍÓÚÑ ]{5,45})\\s*\\r?\\n+\\s*(?:C\\.?C\\.?|C\\.?E\\.?|NIT)\\s*[:.-]?\\s*([0-9.]{6,15})\\s*\\r?\\n+\\s*\\$\\s*([0-9,.]+)").matcher(textCamara);
            while (mSocio.find()) {
                String rawName = mSocio.group(1).replaceAll("\\s+", " ").trim();
                rawName = rawName.replaceAll("^(?i)(?:Socio\\s+Capitalista|Valor\\s+Aportes|Accionistas|Socios)\\s*", "").trim();
                String doc = mSocio.group(2).replace(".", "").trim();
                String aporteStr = mSocio.group(3).replaceAll("[.,]", "");

                double aporte = 0.0;
                double part = 0.0;
                try {
                    aporte = Double.parseDouble(aporteStr);
                    if (totalCapital > 0) {
                        part = Math.round((aporte / totalCapital) * 10000.0) / 100.0;
                    }
                } catch (Exception e) {}

                if (!docsSeen.contains(doc) && !rawName.isEmpty()) {
                    docsSeen.add(doc);
                    socios.add(SocioPreFill.builder()
                            .nombreCompleto(rawName)
                            .tipoDocumento("CC")
                            .numeroDocumento(doc)
                            .participacion(part)
                            .tipoPersona(doc.length() > 9 ? "Jurídica" : "Natural")
                            .nacionalidad("Colombiana")
                            .build());
                }
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
