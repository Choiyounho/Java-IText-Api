package pdf.domain;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pdf.utils.CommonsConstant.*;

public class ParagraphGenerator {

    private static final int LEADING_LENGTH = 32;
    private static final int SPACING_BEFORE = 50;
    private static final int SPACING_AFTER = 50;

    public Paragraph newInstance() {
        Paragraph spacing = new Paragraph(LEADING_LENGTH);
        spacing.setSpacingBefore(SPACING_BEFORE);
        spacing.setSpacingAfter(SPACING_AFTER);
        return spacing;
    }

    public void splitSpacing(String content, Paragraph paragraph) {
        IntStream.range(NUMBER_ZERO, NUMBER_TWENTY)
                .mapToObj(i -> new Chunk(content))
                .forEach(paragraph::add);
    }

    public Paragraph splitParagraph(String content) {
        return IntStream.range(NUMBER_ZERO, NUMBER_TEN)
                .mapToObj(i -> new Chunk(content))
                .collect(Collectors.toCollection(Paragraph::new));
    }

}
