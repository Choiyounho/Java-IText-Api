package transferpdf.view;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.ss.usermodel.Cell;
import transferpdf.domain.ExcelVo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TransferView {

    public static void createTable(String[] headers, Font fontHeader, PdfPTable table) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setGrayFill(0.9f);
            cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
            table.addCell(cell);
        }
    }

    public static void readCell(Iterator<Cell> cells, String[] cellArr, int i) {
        while (cells.hasNext()) {
            Cell cell = cells.next();
            cellArr[i] = cell.toString();
            i++;
        }
    }

    public static void createPhrase(List<ExcelVo> data, Font fontRow, PdfPTable table) throws BadElementException, IOException {
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
    }

}
