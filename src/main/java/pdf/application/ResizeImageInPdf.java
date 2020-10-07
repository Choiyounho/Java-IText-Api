package pdf.application;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import pdf.domain.DocumentGenerator;
import pdf.domain.ImageGenerator;

import java.io.IOException;

import static pdf.application.PdfExample.createFileOutputStream;
import static pdf.application.PdfExample.getPdfInstance;
import static pdf.domain.DocumentGenerator.addDocument;
import static pdf.utils.CommonsConstant.*;
import static pdf.domain.DocumentGenerator.document;

public class ResizeImageInPdf {

    private static final String FILENAME = "ImageResize.pdf";
    private static final Float SIZE_200 = 200f;
    private static final Float SIZE_100 = 100f;

    public static void main(String[] args) {
        try {
            getPdfInstance(document, createFileOutputStream(FILENAME));
            DocumentGenerator.openDocument(document);

            Image originalOctocat = ImageGenerator.getInstance(IMAGE_OCTOCAT);
            addDocument(document, originalOctocat);

            Image resizeOctocat = ImageGenerator.resizeWidthAndHeight(IMAGE_OCTOCAT, SIZE_200, SIZE_200);
            addDocument(document, resizeOctocat);

            Image originalGithubLogo = ImageGenerator.resizePercent(IMAGE_GITHUB_LOGO, SIZE_100);
            addDocument(document, originalGithubLogo);

            Image resizeGithubLogo1 = ImageGenerator.resizeWidthAndHeight(IMAGE_GITHUB_LOGO, SIZE_200, SIZE_200);
            addDocument(document, resizeGithubLogo1);

            Image resizeGithubLogo2 = ImageGenerator.resizeWidthAndHeight(IMAGE_GITHUB_LOGO, SIZE_100, SIZE_200);
            addDocument(document, resizeGithubLogo2);

            System.out.println("Image Resize Success !");
        } catch (DocumentException e) {
            System.out.println("DocumentException e" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException e " + e.getMessage());
        } catch (Exception e ) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            DocumentGenerator.closeDocument(document);
        }
    }

}
