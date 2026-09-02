import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.util.regex.*;
import java.util.*;

public class TestParser {
    public static void main(String[] args) throws Exception {
        String textRut = getText("Documentos/innovasoft/RUT innovasoft.pdf");
        String textCamara = getText("Documentos/innovasoft/Camara y comercio innovadoft.pdf");
        String textBanco = getText("Documentos/innovasoft/Referencia bancaria INNOVASOFT.pdf");
        String textRefCom = getText("Documentos/innovasoft/Referencia comercial  INNOVASOFT.pdf");

        System.out.println("=== PREFILL SIMULATION ===");
        String nit = extractNit(textRut);
        if (nit.isEmpty()) nit = extractNit(textCamara);

        String razonSocial = extractRazonSocial(textRut);
        if (razonSocial.isEmpty()) razonSocial = extractRazonSocial(textCamara);

        String ciiu = extractCiiu(textRut);
        if (ciiu.isEmpty()) ciiu = extractCiiu(textCamara);

        String pais = extractUbicacion(textRut, "Pa[ií]s");
        String depto = extractUbicacion(textRut, "Departamento");
        String mun = extractUbicacion(textRut, "(?:Ciudad/Municipio|Municipio)");
        if (mun.isEmpty()) mun = extractUbicacion(textCamara, "(?:Domicilio\\s+Principal|Municipio)");

        String dir = extractDireccion(textRut);
        if (dir.isEmpty()) dir = extractDireccion(textCamara);

        String correo = extractCorreo(textRut);
        if (correo.isEmpty()) correo = extractCorreo(textCamara);

        String tel = extractTelefono(textRut);
        if (tel.isEmpty()) tel = extractTelefono(textCamara);

        System.out.println("NIT: " + nit);
        System.out.println("Razon Social: " + razonSocial);
        System.out.println("CIIU: " + ciiu);
        System.out.println("Pais: " + pais);
        System.out.println("Departamento: " + depto);
        System.out.println("Municipio: " + mun);
        System.out.println("Direccion: " + dir);
        System.out.println("Correo: " + correo);
        System.out.println("Telefono: " + tel);

        System.out.println("\n=== REPRESENTANTES ===");
        List<String> reps = extractReps(textCamara, textRut);
        for (String r : reps) System.out.println("  * " + r);

        System.out.println("\n=== SOCIOS ===");
        List<String> socios = extractSocios(textCamara);
        for (String s : socios) System.out.println("  * " + s);

        System.out.println("\n=== BANCO ===");
        System.out.println("Banco: " + extractBanco(textBanco));
        System.out.println("Tipo: " + extractTipoCuenta(textBanco));
        System.out.println("Numero: " + extractNumCuenta(textBanco));
    }

    static String getText(String path) throws Exception {
        PDDocument doc = Loader.loadPDF(new File(path));
        PDFTextStripper s = new PDFTextStripper();
        String t = s.getText(doc);
        doc.close();
        return t;
    }

    static String extractNit(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:NIT|N[uú]mero|Identificaci[oó]n\\s*Tributaria)[\\s\\S]{0,35}?\\b([0-9]{1,3}(?:\\.[0-9]{3}){2,3}|[0-9]{8,12})(?:-[0-9])?\\b").matcher(text);
        if (m.find()) {
            return m.group(1).replace(".", "").trim();
        }
        return "";
    }

    static String extractRazonSocial(String text) {
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

    static String extractCiiu(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:46\\.\\s*C[oó]digo|CIIU|Actividad\\s+(?:Econ[oó]mica\\s+)?Principal)[\\s\\S]{0,30}?\\b([0-9]{4})\\b").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    static String extractUbicacion(String text, String field) {
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

    static String extractDireccion(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:Direcci[oó]n\\s+(?:Domicilio\\s+)?Principal|Direcci[oó]n\\s+del\\s+domicilio\\s+principal|41\\.\\s*Direcci[oó]n)[\\s\\S]{0,25}?\\s*:\\s*([A-Za-z0-9 #,.-]{5,100})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    static String extractCorreo(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    static String extractTelefono(String text) {
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

    static List<String> extractReps(String textCamara, String textRut) {
        List<String> reps = new ArrayList<>();
        Set<String> docsSeen = new HashSet<>();

        // 1. Desde Cámara
        if (textCamara != null) {
            Matcher m = Pattern.compile("(?i)REPRESENTANTE\\s+LEGAL\\s+(?:PRINCIPAL|SUPLENTE)?\\s*:\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{5,50}?)(?:\\s*,|\\s*MAYOR)[\\s\\S]{0,150}?(?:C\\.?C\\.?|C\\.?E\\.?|NIT|No\\.?)\\s*[:.-]?\\s*([0-9.]{6,15})").matcher(textCamara);
            while (m.find()) {
                String name = m.group(1).trim().replaceAll("^[^A-Za-zÁÉÍÓÚÑáéíóúñ]+|[^A-Za-zÁÉÍÓÚÑáéíóúñ]+$", "");
                String doc = m.group(2).replace(".", "").trim();
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    reps.add(name + " | CC: " + doc);
                }
            }
        }

        // 2. Desde RUT
        if (textRut != null) {
            Matcher m = Pattern.compile("(?i)(?:Nombres\\s+y\\s+Apellidos|Representante\\s+Legal)\\s*:\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{5,50})[\\s\\S]{0,100}?(?:N[uú]mero(?:\\s+de\\s+Documento)?)\\s*:\\s*([0-9.]{6,15})").matcher(textRut);
            while (m.find()) {
                String name = m.group(1).trim();
                String doc = m.group(2).replace(".", "").trim();
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    reps.add(name + " | CC: " + doc);
                }
            }
        }
        return reps;
    }

    static List<String> extractSocios(String text) {
        List<String> socios = new ArrayList<>();
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
                rawName = rawName.replaceAll("^(?i)(?:Accionistas\\s*/\\s*Socios|Identificaci[oó]n|Valor|Aportes|Participaci[oó]n|Campo|Detalle)\\s*", "").trim();
                socios.add(rawName + " | Doc: " + m.group(2).replace(".", "") + " | Aporte: $" + m.group(3) + " | Part: " + m.group(4));
            }
        }
        return socios;
    }

    static String extractBanco(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(Bancolombia|Davivienda|BBVA|Colpatria|Nequi|Daviplata|Lulo\\s*Bank|Nu\\s*Bank|Nu|Ual[aá]|RappiPay|Banco\\s+(?:de\\s+)?[A-Za-zÁÉÍÓÚñÑ]+(?:\\s+S\\.?A\\.?)?)").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    static String extractTipoCuenta(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(ahorros|corriente)").matcher(text);
        if (m.find()) return m.group(1).substring(0, 1).toUpperCase() + m.group(1).substring(1).toLowerCase();
        return "";
    }

    static String extractNumCuenta(String text) {
        if (text == null) return "";
        Matcher m = Pattern.compile("(?i)(?:cuenta|n[uú]mero|n[oº\\.]+|nro|num)\\s*(?:de\\s*)?(?:ahorros|corriente)?\\s*[:.|-]?\\s*(\\d{8,20})").matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }
}
