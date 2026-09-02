import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;

public class ExtractText {
    public static void main(String[] args) throws Exception {
        File f = new File("Documentos/innovasoft/Camara y comercio innovadoft.pdf");
        PDDocument doc = Loader.loadPDF(f);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        System.out.println(text);
        doc.close();
    }
}
