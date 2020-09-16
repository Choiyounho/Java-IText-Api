import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import static utils.CommonsConstant.*;

public class ResizeImageInPdf {

    private static final String FILENAME = "ImageScaling.pdf";

    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(PATHNAME + FILENAME));
            document.open();

            Image image = Image.getInstance(IMAGENAME);
            document.add(image);

            image = Image.getInstance(IMAGENAME);
            image.scaleAbsolute(200f, 200f);
            document.add(image);

            image = Image.getInstance(IMAGE_URL);
            image.scalePercent(200f);
            document.add(image);

            image = Image.getInstance(IMAGE_URL);
            image.scaleAbsolute(100f, 200f);
            document.add(image);

            System.out.println("크기 조절 성공!!");


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e ) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            document.close();
        }
    }
}
