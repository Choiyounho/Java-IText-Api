package transferpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import transferpdf.domain.ExcelVo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import transferpdf.view.TransferView;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.CommonsConstant.*;

public class ExcelTransferPdf {

    private static final String INPUT_FILENAME = "input/isbn.xls";
    private static final String OUTPUT_FILENAME = "bookList.pdf";
    public static final int COLUMN_COUNT = 5;

    public static void main(String[] args) {

        List<ExcelVo> data = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(INPUT_FILENAME)) {
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            rows.next();
            String[] cellArr = new String[COLUMN_COUNT];
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                int i = 0;
                TransferView.readCell(cells, cellArr, i);
                ExcelVo print = ExcelVo.print(cellArr);
                data.add(print);
            }
            pdfMaker(data);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException e " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e " + e.getMessage());
        }
    }

    private static void pdfMaker(List<ExcelVo> data) {
        String[] headers = new String[]{"제목", "저자", "출판사", "이미지"};
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(PATHNAME + OUTPUT_FILENAME)));
            document.open();

            BaseFont bFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font fontHeader = new Font(bFont, FONT_TITLE_SIZE);
            Font fontRow = new Font(bFont, FONT_ROWS_SIZE);

            PdfPTable table = new PdfPTable(headers.length);
            for (String header : headers) {
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.9f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                table.addCell(cell);
            }
            table.completeRow();
            for (ExcelVo vo : data) {
                Phrase phrase = new Phrase(vo.getTitle(), fontRow);
                table.addCell(new PdfPCell(phrase));

                phrase = new Phrase(vo.getAuthor(), fontRow);
                table.addCell(new PdfPCell(phrase));

                phrase = new Phrase(vo.getCompany(), fontRow);
                table.addCell(new PdfPCell(phrase));

                Image image = Image.getInstance(vo.getImageUrl());
                table.addCell(image);

                table.completeRow();
            }

            document.addTitle("PDF Table Demo");
            document.add(table);
            System.out.println("bookList 생성완료");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException e " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException e " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException e " + e.getMessage());
        } catch (BadElementException e) {
            System.out.println("BadElementException e " + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("DocumentException e " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception e " + e.getMessage());
        } finally {
            document.close();
        }
    }

}


