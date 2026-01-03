import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird {
    private static final int JUMP_STRENGTH = -12;
    private int y;
    private int velocity;
    private int width, height;
    private BufferedImage image;

    public Bird() {
        velocity = 0;
        try {
            image = ImageIO.read(new File(Constants.BIRD_IMAGE_PATH));
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException | NullPointerException e) {
            System.err.println("Error: Image of the bird is not found!");
        }
        y = (Constants.SCREEN_HEIGHT - height) / 2;
    }

    public void update() {
        velocity += Constants.GRAVITY;
        y += velocity;
    }

    public void jump() {
        velocity = JUMP_STRENGTH;
    }

    public void draw(Graphics g) {
        g.drawImage(image, Constants.BIRD_X, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(Constants.BIRD_X, y, width, height);
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }
}