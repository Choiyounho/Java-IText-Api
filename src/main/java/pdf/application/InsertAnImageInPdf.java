package pdf.application;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import pdf.domain.DocumentGenerator;
import pdf.domain.ImageGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import static pdf.application.PdfExample.createFileOutputStream;
import static pdf.application.PdfExample.getPdfInstance;
import static pdf.utils.CommonsConstant.*;
import static pdf.domain.DocumentGenerator.document;

public class InsertAnImageInPdf {

    private static final String FILENAME = "ImageDemo.pdf";

    public static void main(String[] args) {
        try {
            getPdfInstance(document, createFileOutputStream(FILENAME));
            DocumentGenerator.openDocument();

            Image octocat = ImageGenerator.getInstance(IMAGE_OCTOCAT);
            DocumentGenerator.addDocument(document, octocat);

            Image githubLogo = ImageGenerator.getInstance(IMAGE_GITHUB_LOGO);
            DocumentGenerator.addDocument(document, githubLogo);

            System.out.println("ImageDemo.pdf Created !");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e " + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("DocumentException e " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException e " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException e " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e " + e.getMessage());
        } finally {
            DocumentGenerator.closeDocument();
        }
    }

}

