package pdf.application;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import pdf.domain.DocumentGenerator;
import pdf.domain.ParagraphGenerator;

import java.io.FileNotFoundException;

import static pdf.application.PdfExample.createFileOutputStream;
import static pdf.application.PdfExample.getPdfInstance;
import static pdf.domain.DocumentGenerator.document;

public class MakeParagraph {

    private static final String FILENAME = "paragraphDemo.pdf";

    public static void main(String[] args) {
        try {
            getPdfInstance(document, createFileOutputStream(FILENAME));
            DocumentGenerator.openDocument(document);

            String content = "My favorite food! ";
            ParagraphGenerator paragraphGenerator = new ParagraphGenerator();
            Paragraph spacing = paragraphGenerator.newInstance();

            paragraphGenerator.splitSpacing(content, spacing);
            document.add(spacing);

            Paragraph paragraph = paragraphGenerator.splitParagraph(content);
            document.add(paragraph);

            System.out.println("paragraphDemo.pdf Created !");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e " + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("DocumentException e " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e " + e.getMessage());
        } finally {
            DocumentGenerator.closeDocument(document);
        }
    }

}
