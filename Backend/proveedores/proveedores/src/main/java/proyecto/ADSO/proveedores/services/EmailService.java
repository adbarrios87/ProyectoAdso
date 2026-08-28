package proyecto.ADSO.proveedores.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.*;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${resend.api.key:}")
    private String resendApiKey;

    @Value("${resend.from.email:Parere GRC <onboarding@resend.dev>}")
    private String resendFromEmail;

    @Value("${resend.test.redirect-to:adbarrios87@gmail.com}")
    private String resendTestRedirectTo;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    private void sendViaResend(List<String> to, String subject, String htmlContent, List<Map<String, String>> attachments) {
        try {
            List<String> finalRecipients = to;
            String finalSubject = subject;
            String finalHtml = htmlContent;

            if (resendTestRedirectTo != null && !resendTestRedirectTo.trim().isEmpty()) {
                String originalRecipients = String.join(", ", to);
                if (!to.contains(resendTestRedirectTo.trim())) {
                    finalRecipients = Collections.singletonList(resendTestRedirectTo.trim());
                    finalSubject = "[Para: " + originalRecipients + "] " + subject;
                    String infoBanner = "<div style=\"background-color: #fff8e1; border-left: 4px solid #D4A373; padding: 10px 14px; margin-bottom: 20px; font-size: 13px; color: #795548; border-radius: 4px;\"><strong>Modo Pruebas / Demo:</strong> Correo dirigido originalmente a: <code>" + originalRecipients + "</code></div>";
                    finalHtml = infoBanner + htmlContent;
                }
            }

            Map<String, Object> payload = new HashMap<>();
            payload.put("from", resendFromEmail);
            payload.put("to", finalRecipients);
            payload.put("subject", finalSubject);
            payload.put("html", finalHtml);

            if (attachments != null && !attachments.isEmpty()) {
                payload.put("attachments", attachments);
            }

            String jsonBody = objectMapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .header("Authorization", "Bearer " + resendApiKey.trim())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                System.err.println("Resend API Error (" + response.statusCode() + "): " + response.body());
                throw new RuntimeException("Error en Resend API (" + response.statusCode() + "): " + response.body());
            } else {
                System.out.println("Correo enviado exitosamente vía Resend: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar correo vía Resend: " + e.getMessage(), e);
        }
    }

    public void sendPasswordRecoveryEmail(String toEmail, String resetLink) {
        String htmlContent = "<div style=\"font-family: 'Poppins', Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #f0f0f0; border-radius: 12px; padding: 40px; box-shadow: 0 4px 12px rgba(0,0,0,0.05);\">"
                + "<div style=\"text-align: center; margin-bottom: 30px;\">"
                + "  <h1 style=\"color: #1E1E2F; margin: 0; font-size: 24px; font-weight: 600;\">Parere GRC</h1>"
                + "  <p style=\"color: #D4A373; font-style: italic; margin: 5px 0 0 0;\">Validación Ética de Proveedores</p>"
                + "</div>"
                + "<h2 style=\"color: #333333; font-size: 20px; font-weight: 500;\">¿Solicitaste recuperar tu contraseña?</h2>"
                + "<p style=\"color: #666666; font-size: 15px; line-height: 1.6;\">"
                + "  Hemos recibido una solicitud para restablecer la contraseña de tu cuenta en Parere GRC. "
                + "  Para continuar, haz clic en el siguiente botón. Este enlace caducará en 15 minutos."
                + "</p>"
                + "<div style=\"text-align: center; margin: 35px 0;\">"
                + "  <a href=\"" + resetLink + "\" style=\"background-color: #D4A373; color: #ffffff; text-decoration: none; padding: 14px 30px; font-size: 16px; font-weight: 500; border-radius: 8px; display: inline-block; box-shadow: 0 4px 10px rgba(212,163,115,0.25);\">Restablecer Contraseña</a>"
                + "</div>"
                + "<p style=\"color: #999999; font-size: 13px; line-height: 1.5;\">"
                + "  Si no solicitaste este cambio, puedes ignorar este correo de forma segura. Tu contraseña seguirá siendo la misma."
                + "</p>"
                + "<hr style=\"border: none; border-top: 1px solid #eeeeee; margin: 30px 0;\">"
                + "<p style=\"color: #cccccc; font-size: 11px; text-align: center; margin: 0;\">"
                + "  © 2025 Parere GRC - Juan Cadena - Adriana Barrios"
                + "</p>"
                + "</div>";

        if (resendApiKey != null && !resendApiKey.trim().isEmpty()) {
            sendViaResend(Collections.singletonList(toEmail), "Recuperación de Contraseña - Parere GRC", htmlContent, null);
        } else if (mailSender != null) {
            sendViaSmtp(toEmail, "Recuperación de Contraseña - Parere GRC", htmlContent, null);
        }
    }

    public void sendSignatureLinkEmail(String toEmail, String link) {
        String htmlContent = "<div style=\"font-family: 'Poppins', Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #f0f0f0; border-radius: 12px; padding: 40px; box-shadow: 0 4px 12px rgba(0,0,0,0.05);\">"
                + "<div style=\"text-align: center; margin-bottom: 30px;\">"
                + "  <h1 style=\"color: #1E1E2F; margin: 0; font-size: 24px; font-weight: 600;\">Parere GRC</h1>"
                + "  <p style=\"color: #D4A373; font-style: italic; margin: 5px 0 0 0;\">Validación Ética de Proveedores</p>"
                + "</div>"
                + "<h2 style=\"color: #333333; font-size: 20px; font-weight: 500;\">Firma Electrónica Requerida</h2>"
                + "<p style=\"color: #666666; font-size: 15px; line-height: 1.6;\">"
                + "  Estimado proveedor, su formulario de registro ha sido completado exitosamente. "
                + "  Para finalizar el proceso y aceptar la Declaración de Origen de Fondos, por favor firme electrónicamente haciendo clic en el siguiente botón."
                + "</p>"
                + "<div style=\"text-align: center; margin: 35px 0;\">"
                + "  <a href=\"" + link + "\" style=\"background-color: #3e3e5c; color: #ffffff; text-decoration: none; padding: 14px 30px; font-size: 16px; font-weight: 500; border-radius: 8px; display: inline-block; box-shadow: 0 4px 10px rgba(62,62,92,0.25);\">Firmar Documento</a>"
                + "</div>"
                + "<p style=\"color: #999999; font-size: 13px; line-height: 1.5;\">"
                + "  Este enlace tiene una validez de 24 horas."
                + "</p>"
                + "<hr style=\"border: none; border-top: 1px solid #eeeeee; margin: 30px 0;\">"
                + "<p style=\"color: #cccccc; font-size: 11px; text-align: center; margin: 0;\">"
                + "  © 2025 Parere GRC - Juan Cadena - Adriana Barrios"
                + "</p>"
                + "</div>";

        if (resendApiKey != null && !resendApiKey.trim().isEmpty()) {
            sendViaResend(Collections.singletonList(toEmail), "Firma Requerida: Registro de Proveedor - Parere GRC", htmlContent, null);
        } else if (mailSender != null) {
            sendViaSmtp(toEmail, "Firma Requerida: Registro de Proveedor - Parere GRC", htmlContent, null);
        }
    }

    public void sendSignedPdfEmail(String[] toEmails, File pdfFile) {
        String htmlContent = "<div style=\"font-family: 'Poppins', Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #f0f0f0; border-radius: 12px; padding: 40px; box-shadow: 0 4px 12px rgba(0,0,0,0.05);\">"
                + "<div style=\"text-align: center; margin-bottom: 30px;\">"
                + "  <h1 style=\"color: #1E1E2F; margin: 0; font-size: 24px; font-weight: 600;\">Parere GRC</h1>"
                + "  <p style=\"color: #D4A373; font-style: italic; margin: 5px 0 0 0;\">Validación Ética de Proveedores</p>"
                + "</div>"
                + "<h2 style=\"color: #333333; font-size: 20px; font-weight: 500;\">Documento Firmado Exitosamente</h2>"
                + "<p style=\"color: #666666; font-size: 15px; line-height: 1.6;\">"
                + "  La firma electrónica ha sido procesada exitosamente. Adjunto a este correo encontrará el documento PDF con el resumen de su registro y la confirmación de firma."
                + "</p>"
                + "<hr style=\"border: none; border-top: 1px solid #eeeeee; margin: 30px 0;\">"
                + "<p style=\"color: #cccccc; font-size: 11px; text-align: center; margin: 0;\">"
                + "  © 2025 Parere GRC - Juan Cadena - Adriana Barrios"
                + "</p>"
                + "</div>";

        if (resendApiKey != null && !resendApiKey.trim().isEmpty()) {
            List<Map<String, String>> attachments = new ArrayList<>();
            try {
                byte[] bytes = Files.readAllBytes(pdfFile.toPath());
                String base64Content = Base64.getEncoder().encodeToString(bytes);
                Map<String, String> att = new HashMap<>();
                att.put("filename", "Formulario_Firmado.pdf");
                att.put("content", base64Content);
                attachments.add(att);
            } catch (Exception e) {
                System.err.println("Error codificando PDF adjunto: " + e.getMessage());
            }
            sendViaResend(Arrays.asList(toEmails), "Documento Firmado: Registro de Proveedor - Parere GRC", htmlContent, attachments);
        } else if (mailSender != null) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom("pareregrc@gmail.com", "Parere GRC");
                helper.setTo(toEmails);
                helper.setSubject("Documento Firmado: Registro de Proveedor - Parere GRC");
                helper.setText(htmlContent, true);
                FileSystemResource fileResource = new FileSystemResource(pdfFile);
                helper.addAttachment("Formulario_Firmado.pdf", fileResource);
                mailSender.send(message);
            } catch (Exception e) {
                throw new RuntimeException("Error al enviar el correo con el PDF firmado vía SMTP: " + e.getMessage(), e);
            }
        }
    }

    public void sendSystemNotification(String toEmail, String subject, String notificationBody) {
        String htmlContent = "<div style=\"font-family: 'Poppins', Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #f0f0f0; border-radius: 12px; padding: 40px; box-shadow: 0 4px 12px rgba(0,0,0,0.05);\">"
                + "<div style=\"text-align: center; margin-bottom: 30px;\">"
                + "  <h1 style=\"color: #1E1E2F; margin: 0; font-size: 24px; font-weight: 600;\">Parere GRC</h1>"
                + "  <p style=\"color: #D4A373; font-style: italic; margin: 5px 0 0 0;\">Validación Ética de Proveedores</p>"
                + "</div>"
                + "<h2 style=\"color: #333333; font-size: 20px; font-weight: 500;\">Notificación del Sistema</h2>"
                + "<p style=\"color: #666666; font-size: 15px; line-height: 1.6;\">"
                + notificationBody
                + "</p>"
                + "<hr style=\"border: none; border-top: 1px solid #eeeeee; margin: 30px 0;\">"
                + "<p style=\"color: #cccccc; font-size: 11px; text-align: center; margin: 0;\">"
                + "  © 2025 Parere GRC - Juan Cadena - Adriana Barrios"
                + "</p>"
                + "</div>";

        if (resendApiKey != null && !resendApiKey.trim().isEmpty()) {
            sendViaResend(Collections.singletonList(toEmail), subject, htmlContent, null);
        } else if (mailSender != null) {
            sendViaSmtp(toEmail, subject, htmlContent, null);
        }
    }

    private void sendViaSmtp(String toEmail, String subject, String htmlContent, File pdfFile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, pdfFile != null, "UTF-8");
            helper.setFrom("pareregrc@gmail.com", "Parere GRC");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            if (pdfFile != null) {
                helper.addAttachment("Formulario_Firmado.pdf", new FileSystemResource(pdfFile));
            }
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar correo vía SMTP: " + e.getMessage(), e);
        }
    }
}
