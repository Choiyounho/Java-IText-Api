import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static utils.CommonsConstant.PATHNAME;

public class MakeParagraph {

    private static final String FILENAME = "paragraphDemo.pdf";

    public static void main(String[] args) {
        Document document = new Document();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATHNAME + FILENAME);
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            String content = "My favorite food! ";
            Paragraph paragraph = new Paragraph(32); // 줄간격 32
            paragraph.setSpacingBefore(50); // 문단 과 문단 사이 길이 50
            paragraph.setSpacingAfter(50);

            for (int i = 0; i < 20; i++) {
                Chunk chunk = new Chunk(content);
                paragraph.add(chunk);
            }
            document.add(paragraph);

            Paragraph paragraph1 = new Paragraph();

            for (int i = 0; i < 10; i++) {
                paragraph1.add(new Chunk(content));
            }
            document.add(paragraph1);
            document.close();

            System.out.println("paragraphDemo.pdf 생성완료");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e" + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("DocumentException e" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e" + e.getMessage());
        }

    }
}
