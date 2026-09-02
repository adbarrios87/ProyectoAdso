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

CAPITAL
Capital y socios: $1,000,000 Dividido en 10 Cuotas de valor nominal $100,000 Cada una, Distribuidos así:
Socio Capitalista Valor Aportes
ALEJANDRO JARAMILLO RESTREPO
C.C. 1.144.528.910
$600,000
EMILY ÑUSTES BARRIOS
C.C. 1.109.938.925
$250,000
JUAN CADENA CADENA
C.C. 12.345.678.900
$150,000
TOTAL DEL CAPITAL $1,000,000
LA RESPONSABILIDAD DE LOS SOCIOS COMANDITARIOS QUEDA LIMITADA AL MONTO DE SUS RESPECTIVOS APORTES.
REPRESENTACIÓN LEGAL Y ADMINISTRACIÓN
LA SOCIEDAD SERÁ ADMINISTRADA Y REPRESENTADA LEGALMENTE POR:
ALEJANDRO JARAMILLO RESTREPO C.C. 1.144.528.910.

ACTIVIDADES ECONÓMICAS (CIIU) Y TAMAÑO EMPRESARIAL
Actividad Principal: Código CIIU 6621
Actividad Secundaria: Código CIIU 6511
""";

        String ocrRut = """
5. Número de Identificación Tributaria (NIT)
901845320
35. Razón social
INNOVASOFT SOLUTIONS S.A.S.
36. Nombre comercial
INNOVASOFT SOLUTIONS
38. País
Colombia
39. Departamento
Valle del Cauca
40. Ciudad/Municipio
Cali
41. Dirección principal
Carrera 100 # 16-20 Oficina 402
42. Correo electrónico
facturacion@innovasoft.com.co
44. Teléfono 1
3184509988
46. Código
6201

Hoja 3
Representación
1
98. Representación
REPRS LEGAL PRIN
100. Tipo de documento
Cédula de Ciudadania
101. Número de identificación
1144528910
104. Primer apellido
JARAMILLO
105. Segundo apellido
RESTREPO
106. Primer nombre
ALEJANDRO
2
98. Representación
REPRS LEGAL SUPL
100. Tipo de documento
Cédula de Ciudadania
101. Número de identificación
1109938925
104. Primer apellido
ÑUSTES
105. Segundo apellido
BARRIOS
106. Primer nombre
EMILY

Hoja 4
Socios y/o Miembros de Juntas Directivas, Consorcios, Uniones Temporales
1
111. Tipo de documento
Cédula de Ciudadania
112. Número de identificación
1144528910
115. Primer apellido
JARAMILLO
116. Segundo apellido
RESTREPO
117. Primer nombre
ALEJANDRO
120. Valor capital del socio
600,000
121. % Participación
60
2
111. Tipo de documento
Cédula de Ciudadania
112. Número de identificación
1109938925
115. Primer apellido
ÑUSTES
116. Segundo apellido
BARRIOS
117. Primer nombre
EMILY
120. Valor capital del socio
250,000
121. % Participación
25
3
111. Tipo de documento
Cédula de Ciudadania
112. Número de identificación
12345678900
115. Primer apellido
CADENA
116. Segundo apellido
CADENA
117. Primer nombre
JUAN
120. Valor capital del socio
150,000
121. % Participación
15
""";

        System.out.println("=== TEST EXTRACTION ON USER OCR ===");
        System.out.println("NIT RUT: [" + extractNit(ocrRut) + "]");
        System.out.println("Razon Social RUT: [" + extractRazonSocial(ocrRut) + "]");
        System.out.println("CIIU RUT: [" + extractCiiu(ocrRut) + "]");
        System.out.println("Pais: [" + extractUbicacion(ocrRut, "Pa[ií]s") + "]");
        System.out.println("Depto: [" + extractUbicacion(ocrRut, "Departamento") + "]");
        System.out.println("Municipio: [" + extractUbicacion(ocrRut, "(?:Ciudad/Municipio|Municipio)") + "]");
        System.out.println("Direccion: [" + extractDireccion(ocrRut) + "]");
        System.out.println("Correo: [" + extractCorreo(ocrRut) + "]");
        System.out.println("Telefono: [" + extractTelefono(ocrRut) + "]");

        System.out.println("\nNIT Camara: [" + extractNit(ocrCamara) + "]");
        System.out.println("Razon Social Camara: [" + extractRazonSocial(ocrCamara) + "]");
        System.out.println("Direccion Camara: [" + extractDireccion(ocrCamara) + "]");
        System.out.println("Correo Camara: [" + extractCorreo(ocrCamara) + "]");
        System.out.println("Telefono Camara: [" + extractTelefono(ocrCamara) + "]");
        System.out.println("CIIU Camara: [" + extractCiiu(ocrCamara) + "]");

        System.out.println("\n=== REPRESENTANTES ===");
        List<String> reps = extractReps(ocrCamara, ocrRut);
        for (String r : reps) System.out.println("  * " + r);

        System.out.println("\n=== SOCIOS ===");
        List<String> socios = extractSocios(ocrCamara, ocrRut);
        for (String s : socios) System.out.println("  * " + s);
    }

    static String extractNit(String text) {
        if (text == null) return "";
        // 1. Línea siguiente a "5. Número de Identificación Tributaria (NIT)" o "NIT:"
        Matcher m1 = Pattern.compile("(?i)(?:5\\.\\s*N[uú]mero\\s+de\\s+Identificaci[oó]n\\s+Tributaria\\s*\\(NIT\\)|NIT\\s*[:.-]?)\\s*\\r?\\n+\\s*([0-9]{1,3}(?:\\.[0-9]{3}){2,3}|[0-9]{8,12})").matcher(text);
        if (m1.find()) return m1.group(1).replace(".", "").trim();

        // 2. Mismo renglón
        Matcher m2 = Pattern.compile("(?i)(?:NIT|N[uú]mero|Identificaci[oó]n\\s*Tributaria)[\\s\\S]{0,35}?\\b([0-9]{1,3}(?:\\.[0-9]{3}){2,3}|[0-9]{8,12})(?:-[0-9])?\\b").matcher(text);
        if (m2.find()) return m2.group(1).replace(".", "").trim();
        return "";
    }

    static String extractRazonSocial(String text) {
        if (text == null) return "";
        // 1. Línea posterior a "35. Razón social" (sin confundir con 31. Primer apellido)
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

    static String extractCiiu(String text) {
        if (text == null) return "";
        Matcher m1 = Pattern.compile("(?i)(?:46\\.\\s*C[oó]digo|Actividad\\s+Principal\\s*[:.-]?\\s*(?:C[oó]digo\\s+)?CIIU)\\s*\\r?\\n*\\s*([0-9]{4})\\b").matcher(text);
        if (m1.find()) return m1.group(1).trim();

        Matcher m2 = Pattern.compile("(?i)(?:CIIU|Actividad\\s+(?:Econ[oó]mica\\s+)?Principal)[\\s\\S]{0,30}?\\b([0-9]{4})\\b").matcher(text);
        if (m2.find()) return m2.group(1).trim();
        return "";
    }

    static String extractUbicacion(String text, String field) {
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

    static String extractDireccion(String text) {
        if (text == null) return "";
        Matcher m1 = Pattern.compile("(?i)(?:41\\.\\s*Direcci[oó]n\\s+principal)\\s*\\r?\\n+\\s*([A-Za-z0-9 #,.-]{5,100})").matcher(text);
        if (m1.find()) return m1.group(1).trim();

        Matcher m2 = Pattern.compile("(?i)(?:Direcci[oó]n\\s+(?:Domicilio\\s+)?Principal|Direcci[oó]n\\s+del\\s+domicilio\\s+principal)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([A-Za-z0-9 #,.-]{5,100})").matcher(text);
        if (m2.find()) return m2.group(1).trim();
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
        Matcher m1 = Pattern.compile("(?i)(?:44\\.\\s*Tel[eé]fono\\s*1)\\s*\\r?\\n+\\s*([0-9]{7,15})").matcher(text);
        if (m1.find()) return m1.group(1).trim();

        Matcher m2 = Pattern.compile("(?i)(?:Tel[eé]fono[s]?\\s*(?:1|Comercial(?:es)?|comercial\\s*1)?)\\s*[:.-]?\\s*(?:\\r?\\n)?\\s*([0-9()\\s/.-]{7,50})").matcher(text);
        if (m2.find()) {
            Matcher mNum = Pattern.compile("\\b(3[0-9]{9}|[0-9]{7,10})\\b").matcher(m2.group(1).replaceAll("[^0-9]", " "));
            if (mNum.find()) return mNum.group(1);
        }
        return "";
    }

    static List<String> extractReps(String textCamara, String textRut) {
        List<String> reps = new ArrayList<>();
        Set<String> docsSeen = new HashSet<>();

        // 1. Desde RUT (Hoja 3)
        if (textRut != null) {
            Matcher m = Pattern.compile("(?i)101\\.\\s*N[uú]mero\\s+de\\s+identificaci[oó]n\\s*\\r?\\n+\\s*([0-9.]{6,15})\\s*\\r?\\n+\\s*104\\.\\s*Primer\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*105\\.\\s*Segundo\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*106\\.\\s*Primer\\s+nombre\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)").matcher(textRut);
            while (m.find()) {
                String doc = m.group(1).replace(".", "").trim();
                String nombreCompleto = (m.group(4) + " " + m.group(2) + " " + m.group(3)).trim();
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    reps.add(nombreCompleto + " | CC: " + doc);
                }
            }
        }

        // 2. Desde Cámara
        if (textCamara != null) {
            // A) "REPRESENTADA LEGALMENTE POR: ALEJANDRO JARAMILLO RESTREPO C.C. 1.144.528.910"
            Matcher m1 = Pattern.compile("(?i)(?:REPRESENTADA\\s+LEGALMENTE\\s+POR|REPRESENTANTE\\s+LEGAL)\\s*[:.-]?\\s*\\r?\\n*\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ ]{5,45})\\s+(?:C\\.?C\\.?|C\\.?E\\.?|NIT|No\\.?)\\s*[:.-]?\\s*([0-9.]{6,15})").matcher(textCamara);
            while (m1.find()) {
                String name = m1.group(1).trim();
                String doc = m1.group(2).replace(".", "").trim();
                if (!docsSeen.contains(doc) && !name.toLowerCase().contains("sociedad")) {
                    docsSeen.add(doc);
                    reps.add(name + " | CC: " + doc);
                }
            }
        }
        return reps;
    }

    static List<String> extractSocios(String textCamara, String textRut) {
        List<String> socios = new ArrayList<>();
        Set<String> docsSeen = new HashSet<>();

        // 1. Desde RUT (Hoja 4)
        if (textRut != null) {
            Matcher m = Pattern.compile("(?i)112\\.\\s*N[uú]mero\\s+de\\s+identificaci[oó]n\\s*\\r?\\n+\\s*([0-9.]{6,15})\\s*\\r?\\n+\\s*115\\.\\s*Primer\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*116\\.\\s*Segundo\\s+apellido\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*117\\.\\s*Primer\\s+nombre\\s*\\r?\\n+\\s*([A-ZÁÉÍÓÚÑa-záéíóúñ]+)\\s*\\r?\\n+\\s*120\\.\\s*Valor\\s+capital\\s+del\\s+socio\\s*\\r?\\n+\\s*([0-9.,]+)\\s*\\r?\\n+\\s*121\\.\\s*%\\s*Participaci[oó]n\\s*\\r?\\n+\\s*([0-9.,]+)").matcher(textRut);
            while (m.find()) {
                String doc = m.group(1).replace(".", "").trim();
                String nombreCompleto = (m.group(4) + " " + m.group(2) + " " + m.group(3)).trim();
                String aporte = m.group(5);
                String part = m.group(6);
                if (!docsSeen.contains(doc)) {
                    docsSeen.add(doc);
                    socios.add(nombreCompleto + " | Doc: " + doc + " | Aporte: $" + aporte + " | Part: " + part + "%");
                }
            }
        }

        // 2. Desde Cámara (Page 3 - Tabla de Capital y Socios)
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
                    socios.add(rawName + " | Doc: " + doc + " | Aporte: $" + mSocio.group(3) + " | Part: " + part + "%");
                }
            }
        }
        return socios;
    }
}
