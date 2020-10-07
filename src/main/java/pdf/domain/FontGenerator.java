package pdf.domain;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;

import static pdf.utils.CommonsConstant.FONT;

public class FontGenerator {

    public static Font createFontSize(int fontTitleSize) throws IOException, DocumentException {
        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return new Font(baseFont, fontTitleSize);
    }

}
