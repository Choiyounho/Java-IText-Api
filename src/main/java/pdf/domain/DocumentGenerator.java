package pdf.domain;

import com.itextpdf.text.*;

public class DocumentGenerator {

    public static final Document documentIsA4 = new Document(PageSize.A4);
    public static final Document document = new Document();

    public static void openDocument(){
        document.open();
    }

    public static void closeDocument(){
        document.close();
    }

    public static void openDocumentA4(){
        documentIsA4.open();
    }

    public static void closeDocumentA4(){
        documentIsA4.close();
    }


    public static void addDocument(Document document, Image image) throws DocumentException {
        document.add(image);
    }

}
