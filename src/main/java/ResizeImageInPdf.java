import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import static utils.CommonsConstant.*;

public class ResizeImageInPdf {

    private static final String FILENAME = "ImageResize.pdf";
    private static final Float SIZE_200 = 200f;
    private static final Float SIZE_100 = 100f;

    public static void main(String[] args) {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(ROOT_DIRECTORY + FILENAME));
            document.open();

            Image originalOctocat = getInstance(IMAGE_OCTOCAT);
            addDocument(document, originalOctocat);

            Image resizeOctocat = resizeWidthAndHeight(IMAGE_OCTOCAT, SIZE_200, SIZE_200);
            addDocument(document, resizeOctocat);

            Image originalGithubLogo = resizePercent(IMAGE_GITHUB_LOGO, SIZE_100);
            addDocument(document, originalGithubLogo);

            Image resizeGithubLogo1 = resizeWidthAndHeight(IMAGE_GITHUB_LOGO, SIZE_200, SIZE_200);
            addDocument(document, resizeGithubLogo1);

            Image resizeGithubLogo2 = resizeWidthAndHeight(IMAGE_GITHUB_LOGO, SIZE_100, SIZE_200);
            addDocument(document, resizeGithubLogo2);

            System.out.println("크기 조절 성공!!");
        } catch (DocumentException e) {
            System.out.println("DocumentException e" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException e " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e ) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            document.close();
        }
    }

    private static Image getInstance(String imageName) throws IOException, DocumentException {
        return Image.getInstance(imageName);
    }

    private static Image resizeWidthAndHeight(String imageName, float width, float height) throws IOException, DocumentException {
        Image image = Image.getInstance(imageName);
        image.scaleAbsolute(width, height);
        return image;
    }

    private static Image resizePercent(String imageName, float percent) throws IOException, DocumentException {
        Image image = Image.getInstance(imageName);
        image.scalePercent(percent);
        return image;
    }

    private static void addDocument(Document document, Image image) throws DocumentException {
        document.add(image);
    }

}
