import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import static utils.CommonsConstant.*;

public class InsertAnImageInPdf {

    private static final String FILENAME = "ImageDemo.pdf";

    public static void main(String[] args) {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(ROOT_DIRECTORY + FILENAME));
            document.open();

            Image image = Image.getInstance(IMAGE_OCTOCAT);
            document.add(image);

            image = Image.getInstance(IMAGE_GITHUB_LOGO);
            document.add(image);

            System.out.println("ImageDemo.pdf 파일 생성 완료");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e" + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("DocumentException e" + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException e" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException e" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            document.close();
        }
    }

}

