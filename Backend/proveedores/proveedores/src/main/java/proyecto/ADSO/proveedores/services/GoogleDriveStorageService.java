package proyecto.ADSO.proveedores.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GoogleDriveStorageService {

    @Value("${google.drive.script.url:}")
    private String scriptUrl;

    public String uploadFile(String folderName, String fileName, String mimeType, byte[] fileBytes) throws Exception {
        if (scriptUrl == null || scriptUrl.trim().isEmpty()) {
            throw new IllegalStateException("URL de Google Apps Script no configurada en application.properties");
        }

        String b64 = Base64.getEncoder().encodeToString(fileBytes);
        String safeFolderName = folderName != null ? folderName.replace("\"", "\\\"") : "General";
        String safeFileName = fileName != null ? fileName.replace("\"", "\\\"") : "documento.pdf";
        String safeMime = (mimeType != null && !mimeType.isEmpty()) ? mimeType : "application/pdf";

        String jsonPayload = String.format(
                "{\"folderName\":\"%s\",\"fileName\":\"%s\",\"mimeType\":\"%s\",\"base64\":\"%s\"}",
                safeFolderName, safeFileName, safeMime, b64
        );

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(30))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(scriptUrl.trim()))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(60))
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        if (response.statusCode() != 200 || body == null) {
            throw new RuntimeException("Error en respuesta de Google Drive Script (HTTP " + response.statusCode() + "): " + body);
        }

        if (body.contains("Necesitas permiso") || body.contains("Abre el documento directamente") || body.contains("ServiceLogin")) {
            throw new IllegalStateException("El Webhook de Google Apps Script requiere permisos públicos. Asegúrate de configurar 'Quién tiene acceso' en 'Cualquier usuario' (Anyone).");
        }

        if (body.contains("\"status\":\"error\"")) {
            throw new RuntimeException("Error reportado por Google Drive: " + body);
        }

        // 1. Extraer 'url'
        Matcher mUrl = Pattern.compile("\"url\"\\s*:\\s*\"([^\"]+)\"").matcher(body);
        if (mUrl.find()) {
            return mUrl.group(1).replace("\\/", "/");
        }

        // 2. Extraer 'fileId'
        Matcher mId = Pattern.compile("\"fileId\"\\s*:\\s*\"([^\"]+)\"").matcher(body);
        if (mId.find()) {
            return "https://drive.google.com/file/d/" + mId.group(1) + "/view";
        }

        return body;
    }
}
