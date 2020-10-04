package pdf.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import pdf.domain.DocumentGenerator;
import pdf.domain.ExcelVo;
import pdf.view.TransferView;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static pdf.application.PdfExample.createFileOutputStream;
import static pdf.application.PdfExample.getPdfInstance;
import static pdf.domain.DocumentGenerator.documentIsA4;
import static pdf.utils.CommonsConstant.*;

public class TransferPdfController {

    private static final String INPUT_FILENAME = "input/isbn.xls";
    private static final String OUTPUT_FILENAME = "bookList.pdf";
    private static final int COLUMN_COUNT = 5;

    public void transferExcel() {
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
        String[] headers = TABLE_TITLE;
        try {
            getPdfInstance(documentIsA4, createFileOutputStream(OUTPUT_FILENAME));
            DocumentGenerator.openDocumentA4();

            BaseFont bFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font fontHeader = new Font(bFont, FONT_TITLE_SIZE);
            Font fontRow = new Font(bFont, FONT_ROWS_SIZE);

            PdfPTable table = new PdfPTable(headers.length);
            TransferView.createTable(headers, fontHeader, table);

            table.completeRow();

            TransferView.createPhrase(data, fontRow, table);

            documentIsA4.addTitle("PDF Table Demo");
            documentIsA4.add(table);
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
            DocumentGenerator.closeDocumentA4();
        }
    }

}
