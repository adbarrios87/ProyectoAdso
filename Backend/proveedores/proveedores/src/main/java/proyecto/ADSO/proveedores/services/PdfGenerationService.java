package proyecto.ADSO.proveedores.services;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import proyecto.ADSO.proveedores.entites.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfGenerationService {

    public File generateSignedPdf(ProveedorEntity proveedor, 
                                  FirmaTokenEntity firma, 
                                  FormaDePagoEntity formaPago, 
                                  List<ContactoEntity> contactos,
                                  List<RepresentanteLegalEntity> representantes,
                                  List<SociosProveedorEntity> socios,
                                  String outputDirPath) throws Exception {

        String providerName = proveedor.getRazonSocial() != null && !proveedor.getRazonSocial().isEmpty() 
            ? proveedor.getRazonSocial() : proveedor.getNombres() + " " + proveedor.getApellidos();
        
        File dir = new File(outputDirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File pdfFile = new File(dir, "Formulario_Firmado_" + proveedor.getNumeroIdentificacion() + ".pdf");
        Document document = new Document(PageSize.A4, 40, 40, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, new Color(62, 62, 92));
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE);
        Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);

        // --- ENCABEZADO ---
        Paragraph title = new Paragraph("Parere GRC - Formulario de Registro Firmado", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        
        Paragraph subTitle = new Paragraph("Validación Ética de Proveedores", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, new Color(212, 163, 115)));
        subTitle.setAlignment(Element.ALIGN_CENTER);
        subTitle.setSpacingAfter(20);
        document.add(subTitle);

        // --- SECCIÓN 1: DATOS DEL PROVEEDOR ---
        document.add(new Paragraph("1. Información General", sectionFont));
        document.add(new Paragraph(" "));
        
        PdfPTable tableInfo = new PdfPTable(2);
        tableInfo.setWidthPercentage(100);
        tableInfo.setSpacingAfter(15);
        addCell(tableInfo, "Razón Social / Nombre", textFont); addCell(tableInfo, providerName, textFont);
        addCell(tableInfo, "Identificación", textFont); addCell(tableInfo, proveedor.getNumeroIdentificacion() + (proveedor.getDigitoVerificacion() != null ? "-" + proveedor.getDigitoVerificacion() : ""), textFont);
        addCell(tableInfo, "Correo Principal", textFont); addCell(tableInfo, proveedor.getCorreoPrincipal() != null ? proveedor.getCorreoPrincipal() : "N/A", textFont);
        document.add(tableInfo);

        // --- SECCIÓN 2: INFORMACIÓN BANCARIA Y FINANCIERA ---
        document.add(new Paragraph("2. Información Bancaria y Financiera", sectionFont));
        document.add(new Paragraph(" "));

        PdfPTable tableBancaria = new PdfPTable(2);
        tableBancaria.setWidthPercentage(100);
        tableBancaria.setSpacingAfter(15);
        addCell(tableBancaria, "Banco Referencia", textFont); addCell(tableBancaria, proveedor.getBancoReferencia() != null ? proveedor.getBancoReferencia() : "N/A", textFont);
        addCell(tableBancaria, "Tipo de Cuenta", textFont); addCell(tableBancaria, proveedor.getTipoCuenta() != null ? proveedor.getTipoCuenta() : "N/A", textFont);
        addCell(tableBancaria, "Número de Cuenta", textFont); addCell(tableBancaria, proveedor.getNumCuenta() != null ? proveedor.getNumCuenta() : "N/A", textFont);
        document.add(tableBancaria);

        if (proveedor.getActivos() != null || proveedor.getTotalIngresos() != null) {
            PdfPTable tableFinanciera = new PdfPTable(2);
            tableFinanciera.setWidthPercentage(100);
            tableFinanciera.setSpacingAfter(15);
            addCell(tableFinanciera, "Total Activos", textFont); addCell(tableFinanciera, proveedor.getActivos() != null ? proveedor.getActivos().toString() : "0.00", textFont);
            addCell(tableFinanciera, "Total Pasivos", textFont); addCell(tableFinanciera, proveedor.getPasivos() != null ? proveedor.getPasivos().toString() : "0.00", textFont);
            addCell(tableFinanciera, "Patrimonio", textFont); addCell(tableFinanciera, proveedor.getPatrimonio() != null ? proveedor.getPatrimonio().toString() : "0.00", textFont);
            addCell(tableFinanciera, "Total Ingresos", textFont); addCell(tableFinanciera, proveedor.getTotalIngresos() != null ? proveedor.getTotalIngresos().toString() : "0.00", textFont);
            addCell(tableFinanciera, "Total Gastos", textFont); addCell(tableFinanciera, proveedor.getTotalGastos() != null ? proveedor.getTotalGastos().toString() : "0.00", textFont);
            document.add(tableFinanciera);
        }

        // --- SECCIÓN 3: REPRESENTANTES Y SOCIOS ---
        if (representantes != null && !representantes.isEmpty()) {
            document.add(new Paragraph("3. Representantes Legales", sectionFont));
            document.add(new Paragraph(" "));
            PdfPTable tReps = new PdfPTable(3);
            tReps.setWidthPercentage(100);
            tReps.setSpacingAfter(15);
            addHeader(tReps, "Nombre", tableHeaderFont); addHeader(tReps, "Identificación", tableHeaderFont); addHeader(tReps, "Correo", tableHeaderFont);
            for (RepresentanteLegalEntity rep : representantes) {
                addCell(tReps, rep.getNombres() + " " + rep.getApellidos(), textFont);
                addCell(tReps, rep.getNumeroIdentificacion(), textFont);
                addCell(tReps, rep.getCorreo(), textFont);
            }
            document.add(tReps);
        }

        if (socios != null && !socios.isEmpty()) {
            document.add(new Paragraph("4. Composición Societaria", sectionFont));
            document.add(new Paragraph(" "));
            PdfPTable tSocios = new PdfPTable(3);
            tSocios.setWidthPercentage(100);
            tSocios.setSpacingAfter(15);
            addHeader(tSocios, "Nombre Completo", tableHeaderFont); addHeader(tSocios, "Identificación", tableHeaderFont); addHeader(tSocios, "Participación %", tableHeaderFont);
            for (SociosProveedorEntity socio : socios) {
                addCell(tSocios, socio.getNombres() + " " + socio.getApellidos(), textFont);
                addCell(tSocios, socio.getNumeroIdentificacion(), textFont);
                addCell(tSocios, socio.getParticipacion() != null ? socio.getParticipacion().toString() + "%" : "N/A", textFont);
            }
            document.add(tSocios);
        }

        // --- SECCIÓN 4: DECLARACIÓN LAFT Y FIRMA ---
        document.newPage();
        document.add(new Paragraph("5. Declaración de Origen de Fondos (LAFT)", sectionFont));
        document.add(new Paragraph(" "));
        
        Paragraph juramento = new Paragraph(
            "Bajo la gravedad de juramento manifiesto que los datos aquí consignados obedecen a la realidad, " +
            "por lo que declaro haber leído, entendido y aceptado el documento de declaración de origen de fondos " +
            "y cumplimiento LAFT de Parere S.A.S.\n\n" +
            "Autorizo a Parere S.A.S. para que, en caso de que lo juzgue necesario, consulte mi información crediticia en Centrales de Riesgo.",
            textFont
        );
        juramento.setAlignment(Element.ALIGN_JUSTIFIED);
        juramento.setSpacingAfter(30);
        document.add(juramento);

        document.add(new Paragraph("CERTIFICADO DE FIRMA ELECTRÓNICA", sectionFont));
        document.add(new Paragraph(" "));

        PdfPTable tFirma = new PdfPTable(2);
        tFirma.setWidthPercentage(100);
        tFirma.setSpacingAfter(15);
        addCell(tFirma, "Estado de Firma", textFont); addCell(tFirma, "FIRMADO Y ACEPTADO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, new Color(74, 222, 128)));
        addCell(tFirma, "Fecha y Hora", textFont); addCell(tFirma, firma.getFechaFirmado() != null ? firma.getFechaFirmado().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "N/A", textFont);
        addCell(tFirma, "Dirección IP", textFont); addCell(tFirma, firma.getIpFirma() != null ? firma.getIpFirma() : "No registrada", textFont);
        addCell(tFirma, "Token de Verificación", textFont); addCell(tFirma, firma.getToken(), FontFactory.getFont(FontFactory.COURIER, 9, Color.DARK_GRAY));
        document.add(tFirma);

        document.close();
        return pdfFile;
    }

    private void addHeader(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new Color(62, 62, 92)); // var(--primary-color)
        cell.setPadding(8);
        table.addCell(cell);
    }

    private void addCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(6);
        cell.setBorderColor(new Color(230, 230, 230));
        table.addCell(cell);
    }
}
