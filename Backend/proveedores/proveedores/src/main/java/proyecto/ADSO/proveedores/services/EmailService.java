package proyecto.ADSO.proveedores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordRecoveryEmail(String toEmail, String resetLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pareregrc@gmail.com", "Parere GRC");
            helper.setTo(toEmail);
            helper.setSubject("Recuperación de Contraseña - Parere GRC");

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

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Aviso SMTP (puerto bloqueado en Render): " + e.getMessage());
        }
    }
    public void sendSignatureLinkEmail(String toEmail, String link) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pareregrc@gmail.com", "Parere GRC");
            helper.setTo(toEmail);
            helper.setSubject("Firma Requerida: Registro de Proveedor - Parere GRC");

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

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Aviso SMTP (puerto bloqueado en Render): " + e.getMessage());
        }
    }

    public void sendSignedPdfEmail(String[] toEmails, File pdfFile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pareregrc@gmail.com", "Parere GRC");
            helper.setTo(toEmails);
            helper.setSubject("Documento Firmado: Registro de Proveedor - Parere GRC");

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

            helper.setText(htmlContent, true);
            
            FileSystemResource fileResource = new FileSystemResource(pdfFile);
            helper.addAttachment("Formulario_Firmado.pdf", fileResource);

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Aviso SMTP (puerto bloqueado en Render): " + e.getMessage());
        }
    }

    public void sendSystemNotification(String toEmail, String subject, String notificationBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pareregrc@gmail.com", "Parere GRC");
            helper.setTo(toEmail);
            helper.setSubject(subject);

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

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Aviso SMTP (puerto bloqueado en Render): " + e.getMessage());
        }
    }
}
