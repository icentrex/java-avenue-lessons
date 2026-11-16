package lesson34.example.work_with_image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static BufferedImage loadImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        if (image == null) {
            throw new IOException("Не удалось прчоитать картинку");
        }
        return image;
    }

    public static String getImageInfo(BufferedImage image) {
        String str = image.getWidth() + " " + image.getHeight() + " " + image.getType();
        return str;
    }

    public static BufferedImage resize(BufferedImage source, int newWidth, int newHeight) {
        BufferedImage resized = new BufferedImage(newWidth, newHeight, source.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resized;
    }
}
