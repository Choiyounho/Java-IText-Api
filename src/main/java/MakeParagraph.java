import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.CommonsConstant.*;

public class MakeParagraph {

    private static final String FILENAME = "paragraphDemo.pdf";
    private static final int LEADING_LENGTH = 32;
    private static final int SPACING_BEFORE = 50;
    private static final int SPACING_AFTER = 50;

    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ROOT_DIRECTORY + FILENAME);
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            String content = "My favorite food! ";
            Paragraph spacing = newInstance();

            splitSpacing(content, spacing);
            document.add(spacing);

            Paragraph paragraph = splitParagraph(content);
            document.add(paragraph);

            System.out.println("paragraphDemo.pdf Created !");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e" + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("DocumentException e" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            document.close();
        }
    }

    private static Paragraph newInstance() {
        Paragraph spacing = new Paragraph(LEADING_LENGTH);
        spacing.setSpacingBefore(SPACING_BEFORE);
        spacing.setSpacingAfter(SPACING_AFTER);
        return spacing;
    }

    private static void splitSpacing(String content, Paragraph paragraph) {
        IntStream.range(NUMBER_ZERO, NUMBER_TWENTY)
                .mapToObj(i -> new Chunk(content))
                .forEach(paragraph::add);
    }

    private static Paragraph splitParagraph(String content) {
        return IntStream.range(NUMBER_ZERO, NUMBER_TEN)
                .mapToObj(i -> new Chunk(content))
                .collect(Collectors.toCollection(Paragraph::new));
    }

}
