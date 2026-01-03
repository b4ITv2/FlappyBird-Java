import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    private BufferedImage image;

    public Background() {
        try {
            image = ImageIO.read(new File(Constants.BACKGROUND_PATH));
        } catch (IOException | NullPointerException e) {
            System.err.println("Error: Image of the background is not found!");
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
