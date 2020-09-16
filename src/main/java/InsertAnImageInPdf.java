import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import static utils.CommonsConstant.PATHNAME;

public class InsertAnImageInPdf {

    private static final String FILENAME = "ImageDemo.pdf";

    public static void main(String[] args) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(PATHNAME + FILENAME));
            document.open();

            String imageName = "results/github-octocat.png";
            Image image = Image.getInstance(imageName);
            document.add(image);

            String url = "https://mblogthumb-phinf.pstatic.net/MjAxOTEyMTVfMjc4/MDAxNTc2NDE0MTAwNjg1.cp_9N4gi8GOe7idQjx6pC1LUhK9EqpIs9uArKqZq6iUg.1vF6bTjG3vJW4mb_WagZ5gh0gfwjoo2bznBTEs-tyXkg.JPEG.nilsine11202/github.jpg?type=w800";
            image = Image.getInstance(url);
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

