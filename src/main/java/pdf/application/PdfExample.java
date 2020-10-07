package pdf.application;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pdf.domain.DocumentGenerator;
import pdf.domain.FontGenerator;
import pdf.view.TransferView;

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

            DocumentGenerator.openDocument(documentIsA4);

            Font fontTitle = FontGenerator.createFontSize(FONT_TITLE_SIZE);
            Font fontRows = FontGenerator.createFontSize(FONT_ROWS_SIZE);

            PdfPTable pdfPTable = PdfExample.createPdfTable(TABLE_NUM_COLUMNS);
            pdfPTable.setWidthPercentage(TABLE_WIDTH);

            float[] colWidth = new float[]{20f, 15f, 15f, 30f};
            pdfPTable.setWidths(colWidth);

            TransferView.createCellTitle(TABLE_TITLE, fontTitle, pdfPTable);
            pdfPTable.completeRow();

            createRowsCell(rows, fontRows, pdfPTable);

            PdfPCell pdfPCell5 = newInstanceForPdfCell("Cell 5");
            PdfPCell pdfPCell6 = newInstanceForPdfCell("Cell 6");

            pdfPTable.addCell(pdfPCell5);
            pdfPTable.addCell(pdfPCell6);

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
            DocumentGenerator.closeDocument(documentIsA4);
        }
    }

    private static PdfPCell newInstanceForPdfCell(String cell) {
        PdfPCell pdfPCell = new PdfPCell(new Phrase(cell));
        pdfPCell.setColspan(2);
        return pdfPCell;
    }

    private static void createRowsCell(String[][] rows, Font fontRows, PdfPTable pdfPTable) {
        for (String[] row : rows) {
            phrasePdfCell(fontRows, pdfPTable, row);
            pdfPTable.completeRow();
        }
    }

    private static void phrasePdfCell(Font fontRows, PdfPTable pdfPTable, String[] row) {
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
    }

    public static void getPdfInstance(Document document, FileOutputStream fileOutputStream) throws DocumentException {
        PdfWriter.getInstance(document, fileOutputStream);
    }

    public static FileOutputStream createFileOutputStream(String fileName) throws FileNotFoundException {
        return new FileOutputStream(ROOT_DIRECTORY + fileName);
    }

    public static PdfPTable createPdfTable(int columns) {
        return new PdfPTable(columns);
    }

}
