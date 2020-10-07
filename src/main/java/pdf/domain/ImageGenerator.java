package pdf.domain;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import java.io.IOException;

public class ImageGenerator {

    public static Image getInstance(String imageName) throws IOException, DocumentException {
        return Image.getInstance(imageName);
    }

    public static Image resizeWidthAndHeight(String imageName, float width, float height) throws IOException, DocumentException {
        Image image = ImageGenerator.getInstance(imageName);
        image.scaleAbsolute(width, height);
        return image;
    }

    public static Image resizePercent(String imageName, float percent) throws IOException, DocumentException {
        Image image = ImageGenerator.getInstance(imageName);
        image.scalePercent(percent);
        return image;
    }

}
