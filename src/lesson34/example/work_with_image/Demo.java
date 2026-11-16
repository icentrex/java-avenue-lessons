package lesson34.example.work_with_image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageUtils.loadImage("1.jpg");
            System.out.println(ImageUtils.getImageInfo(image));

            BufferedImage resized = ImageUtils.resize(image, 1000, 1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
