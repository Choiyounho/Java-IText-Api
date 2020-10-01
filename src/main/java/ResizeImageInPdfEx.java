import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import static utils.CommonsConstant.*;

public class ResizeImageInPdfEx {

    private static final String FILENAME = "ImageScaling.pdf";

    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(ROOT_DIRECTORY + FILENAME));
            document.open();

            addImage(document, IMAGENAME);

            scalingWidthAndHeight(document, IMAGENAME, 200f, 200f);

            scalingPercent(document, IMAGE_URL, 200f);

            scalingWidthAndHeight(document, IMAGE_URL, 200f, 200f);

            scalingWidthAndHeight(document, IMAGE_URL, 100f, 200f);

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

    private static void addImage(Document document, String imageName) throws IOException, DocumentException {
        Image image = Image.getInstance(imageName);
        document.add(image);
    }

    private static void scalingWidthAndHeight(Document document, String imageName, float width, float height) throws IOException, DocumentException {
        Image image = Image.getInstance(imageName);
        image.scaleAbsolute(width, height);
        document.add(image);
    }

    private static void scalingPercent(Document document, String imageName, float percent) throws IOException, DocumentException {
        Image image;
        image = Image.getInstance(imageName);
        image.scalePercent(percent);
        document.add(image);
    }

}
