import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static utils.CommonsConstant.ROOT_DIRECTORY;


public class ITextTestEx {

    private static final String FILENAME = "book.pdf";
    private static final String FONT = "malgun.ttf";
    private static final int FONT_TITLE_SIZE = 12;
    private static final int FONT_ROWS_SIZE = 10;
    private static final int TABLE_NUM_COLUMNS = 4;
    private static final int TABLE_WIDTH = 100;

    public static void main(String[] args) {

        String[] title = new String[]{"제목", "저자", "출판사", "이미지URL"};
        String[][] rows = new String[][]{
                {"물리법칙의 이해", "리처드 파인먼", "해나무", "url"},
                {"Java의 정석", "남궁성", "도우출판", "url"},
                {"리눅스프로그래밍", "창병모", "생능출판", "url"}
        };

        Document document = new com.itextpdf.text.Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(ROOT_DIRECTORY + FILENAME)));

            document.open();
            BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font fontTitle = new Font(baseFont, FONT_TITLE_SIZE);
            Font fontRows = new Font(baseFont, FONT_ROWS_SIZE);

            PdfPTable pdfPTable = new PdfPTable(TABLE_NUM_COLUMNS);
            pdfPTable.setWidthPercentage(TABLE_WIDTH);

            float[] colWidth = new float[]{20f, 15f, 15f, 30f};
            pdfPTable.setWidths(colWidth);

            for (String header : title) {
                PdfPCell pdfPCell = new PdfPCell();
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setGrayFill(0.9f);
                pdfPCell.setPhrase(new Phrase(header, fontTitle));
                pdfPTable.addCell(pdfPCell);
            }
            pdfPTable.completeRow();

            for (String[] row : rows) {
                for (String data : row) {
                    Phrase phrase = new Phrase(data, fontRows);
                    PdfPCell pdfPCell = new PdfPCell(phrase);
                    pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pdfPCell.setPaddingTop(20);
                    pdfPCell.setPaddingRight(30);
                    pdfPCell.setPaddingBottom(20);
                    pdfPCell.setPaddingLeft(30);

                    pdfPTable.addCell(pdfPCell);
                }
                pdfPTable.completeRow();
            }

            PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Cell 5"));
            pdfPCell4.setColspan(2);

            PdfPCell pdfPCell5 = new PdfPCell(new Phrase("Cell 6"));
            pdfPCell5.setColspan(2);

            pdfPTable.addCell(pdfPCell4);
            pdfPTable.addCell(pdfPCell5);

            document.addTitle("PDF Table Demo");
            document.add(pdfPTable);
            System.out.println("table 생성 완료");

        } catch (DocumentException e) {
            System.out.println("DocumentException e : " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            document.close();
        }
    }

}
