package pdf.domain;

import com.itextpdf.text.*;

public class DocumentGenerator {

    public static final Document documentIsA4 = new Document(PageSize.A4);
    public static final Document document = new Document();

    public static void openDocument(Document document){
        document.open();
    }

    public static void closeDocument(Document document){
        document.close();
    }

    public static void addDocument(Document document, Image image) throws DocumentException {
        document.add(image);
    }

}
