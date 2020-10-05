package pdf.application;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pdf.domain.DocumentGenerator;
import pdf.domain.FontGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static pdf.domain.DocumentGenerator.documentIsA4;
import static pdf.utils.CommonsConstant.*;

public class PdfExample {

    private static final String FILENAME = "book.pdf";
    private static final int TABLE_NUM_COLUMNS = 4;
    private static final int TABLE_WIDTH = 100;

    public static void main(String[] args) {

        String[][] rows = new String[][]{
                {"물리법칙의 이해", "리처드 파인먼", "해나무", "url"},
                {"Java의 정석", "남궁성", "도우출판", "url"},
                {"리눅스프로그래밍", "창병모", "생능출판", "url"}
        };

        try {
            getPdfInstance(documentIsA4, createFileOutputStream(FILENAME));

            DocumentGenerator.openDocumentA4();

            Font fontTitle = FontGenerator.createFontSize(FONT_TITLE_SIZE);
            Font fontRows = FontGenerator.createFontSize(FONT_ROWS_SIZE);

            PdfPTable pdfPTable = PdfExample.createPdfTable(TABLE_NUM_COLUMNS);
            pdfPTable.setWidthPercentage(TABLE_WIDTH);

            float[] colWidth = new float[]{20f, 15f, 15f, 30f};
            pdfPTable.setWidths(colWidth);

            for (String header : TABLE_TITLE) {
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

            documentIsA4.addTitle("PDF Table Demo");
            documentIsA4.add(pdfPTable);
            System.out.println("table 생성 완료");
        } catch (DocumentException e) {
            System.out.println("DocumentException e : " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e" + e.getMessage());
        } finally {
            DocumentGenerator.closeDocumentA4();
        }
    }

    public static void getPdfInstance(Document document, FileOutputStream fileOutputStream) throws DocumentException, FileNotFoundException {
        PdfWriter.getInstance(document, fileOutputStream);
    }

    public static FileOutputStream createFileOutputStream(String fileName) throws FileNotFoundException {
        return new FileOutputStream(ROOT_DIRECTORY + fileName);
    }

    public static PdfPTable createPdfTable(int columns) {
        return new PdfPTable(columns);
    }

}
