package transferpdf.view;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Iterator;

public class TransferView {

    public static void readCell(Iterator<Cell> cells, String[] cellArr, int i) {
        while (cells.hasNext()) {
            Cell cell = cells.next();
            cellArr[i] = cell.toString();
            i++;
        }
    }

}
