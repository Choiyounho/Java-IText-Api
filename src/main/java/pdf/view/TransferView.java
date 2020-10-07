package pdf.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.ss.usermodel.Cell;
import pdf.domain.ExcelVo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TransferView {

    public static void createCellTitle(String[] titles, Font fontTitle, PdfPTable pdfPTable) {
        for (String title : titles) {
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setGrayFill(0.9f);
            pdfPCell.setPhrase(new Phrase(title.toUpperCase(), fontTitle));
            pdfPTable.addCell(pdfPCell);
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
