package pdf.domain;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;

import static pdf.utils.CommonsConstant.FONT;

public class FontGenerator {

    public static Font createFontSize(int fontTitleSize) throws IOException, DocumentException {
        return new Font(createFont(), fontTitleSize);
    }

    private static BaseFont createFont() throws DocumentException, IOException {
        return BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
    }

}
