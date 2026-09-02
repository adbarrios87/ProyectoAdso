import java.util.regex.*;
import java.util.*;

public class TestExactDocs {
    public static void main(String[] args) {
        String ocrCamara = """
CÁMARA DE COMERCIO DE CALI
CERTIFICADO DE EXISTENCIA Y REPRESENTACIÓN LEGAL
CERTIFICA
Fecha de expedición: 28/05/2026 11:37:05 am | Recibo No.: 10527944 | Valor: $12.100 | CÓDIGO DE VERIFICACIÓN: 08261DAFQ2
DATOS DE IDENTIFICACIÓN Y DOMICILIO
Razón Social: INNOVASOFT SOLUTIONS S.A.S.
NIT: 901845320-4
Domicilio Principal: Cali
Matrícula No.: 895270-6
Fecha de Matrícula: 17 de marzo de 2014
Último Año Renovado: 2026
Fecha de Renovación: 12 de marzo de 2026
Grupo NIIF: Grupo III.
INFORMACIÓN DE UBICACIÓN Y NOTIFICACIONES
Dirección Domicilio Principal: Carrera 100 # 16-20, Oficina 402, Edificio La Ceiba
Municipio Domicilio Principal: Cali, Valle
Correo Electrónico Comercial: contacto@innovasoft.com.co
Teléfonos Comerciales: 330 4500 / 318 450 9988
Dirección Notificación Judicial: Carrera 100 # 16-20, Oficina 402, Edificio La Ceiba
Municipio Notificación Judicial: Cali, Valle
Correo Notificación Judicial: contacto@innovasoft.com.co
""";

        String ocrRut = """
Exportadores
Para uso exclusivo de la DIAN
5. Número de Identificación Tributaria (NIT) 6. DV
901845320
38. País
Colombia
39. Departamento
Valle del Cauca
40. Ciudad/Municipio
Cali
35. Razón social
INNOVASOFT SOLUTIONS S.A.S.
41. Dirección principal
Obligados aduaneros Exportadores
Carrera 100 # 16-20 Oficina 402
42. Correo electrónico
facturacion@innovasoft.com.co
44. Teléfono 1
3184509988
""";

        System.out.println("--- DIRECCION ---");
        System.out.println("Dir Camara: [" + extractDireccion(ocrCamara) + "]");
        System.out.println("Dir RUT: [" + extractDireccion(ocrRut) + "]");

        System.out.println("--- TELEFONO ---");
        System.out.println("Tel Camara: [" + extractTelefono(ocrCamara) + "]");
        System.out.println("Tel RUT: [" + extractTelefono(ocrRut) + "]");

        System.out.println("--- UBICACION ---");
        System.out.println("Pais RUT: [" + extractUbicacion(ocrRut, "Pa[ií]s") + "]");
        System.out.println("Depto RUT: [" + extractUbicacion(ocrRut, "Departamento") + "]");
        System.out.println("Mun RUT: [" + extractUbicacion(ocrRut, "(?:Ciudad/Municipio|Municipio)") + "]");
    }

    static String extractDireccion(String text) {
        if (text == null) return "";
        // 1. Si viene como "Dirección Domicilio Principal: ..." en Cámara de Comercio
        Matcher mCam = Pattern.compile("(?i)(?:Direcci[oó]n\\s+(?:Domicilio\\s+)?Principal|Direcci[oó]n\\s+del\\s+domicilio\\s+principal)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([A-Za-z0-9 #,.-]{5,100})").matcher(text);
        if (mCam.find()) {
            String dir = mCam.group(1).trim();
            if (!dir.toLowerCase().contains("obligados") && !dir.toLowerCase().contains("exportadores")) {
                return dir;
            }
        }

        // 2. Buscar dirección típica colombiana (Carrera, Calle, Cra, Cl, Av, etc.)
        Matcher mPattern = Pattern.compile("(?i)\\b((?:Carrera|Calle|Cra|Cl|Cll|Diagonal|Diag|Dg|Transversal|Trans|Tv|Avenida|Av|Circular|Manzana|Mz)\\.?\\s+[A-Za-z0-9 #,.-]{5,80})").matcher(text);
        if (mPattern.find()) {
            String dir = mPattern.group(1).trim().replaceAll("\\s*\\r?\\n.*", "").trim();
            if (!dir.toLowerCase().contains("obligados") && !dir.toLowerCase().contains("exportadores")) {
                return dir;
            }
        }
        return "";
    }

    static String extractTelefono(String text) {
        if (text == null) return "";
        // 1. Buscar celular colombiano de 10 dígitos que empiece por 3 (ej. 3184509988)
        Matcher mCel = Pattern.compile("\\b(3[0-9]{9})\\b").matcher(text);
        if (mCel.find()) {
            return mCel.group(1).trim();
        }

        // 2. Buscar teléfonos comerciales (ej. 330 4500 / 318 450 9988)
        Matcher mCom = Pattern.compile("(?i)Tel[eé]fonos?\\s*(?:Comerciales?|1)?\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([0-9()\\s/.-]{7,40})").matcher(text);
        if (mCom.find()) {
            Matcher mDigits = Pattern.compile("\\b(3[0-9]{9}|[0-9]{7,10})\\b").matcher(mCom.group(1).replaceAll("[^0-9]", " "));
            if (mDigits.find()) {
                return mDigits.group(1).trim();
            }
        }
        return "";
    }

    static String extractUbicacion(String text, String field) {
        if (text == null) return "";
        Matcher m1 = Pattern.compile("(?i)(?:(?:38|39|40)\\.\\s*)?" + field + "\\s*\\r?\\n+\\s*(?:[0-9]{1,3}\\s*-\\s*)?([A-ZÁÉÍÓÚÑa-záéíóúñ ]{3,30})").matcher(text);
        if (m1.find()) {
            String res = m1.group(1).trim();
            if (!res.toLowerCase().contains("departamento") && !res.toLowerCase().contains("ciudad") && !res.toLowerCase().contains("dirección") && !res.toLowerCase().contains("ubicación")) {
                return res;
            }
        }
        Matcher m2 = Pattern.compile("(?i)(?:(?:38|39|40)\\.\\s*)?" + field + "\\s*[:.-]?\\s*(?:[0-9]{1,3}\\s*-\\s*)?([A-ZÁÉÍÓÚÑa-záéíóúñ ]{3,30})").matcher(text);
        if (m2.find()) {
            String res = m2.group(1).trim();
            if (res.contains("-")) res = res.substring(res.indexOf("-") + 1).trim();
            return res;
        }
        return "";
    }
}
