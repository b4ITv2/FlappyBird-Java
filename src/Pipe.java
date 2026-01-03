import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Pipe {
    public static final int PIPE_SPEED = 5;
    public static final int GAP_SIZE = 200;

    private int x;
    private int gapY;
    private int width, height;
    private BufferedImage lowerImage;
    private BufferedImage upperImage;
    private boolean passed;

    public Pipe(int startX) {
        x = startX;
        passed = false;
        try {
            lowerImage = ImageIO.read(new File(Constants.LOWER_PIPE_IMAGE_PATH));
            upperImage = ImageIO.read(new File(Constants.UPPER_PIPE_IMAGE_PATH));
            width = lowerImage.getWidth();
            height = lowerImage.getHeight();
        } catch (IOException | NullPointerException e) {
            System.err.println("Error: Image of the pipe is not found!");
        }

        generateGap();
    }

    private void generateGap() {
        Random random = new Random();
        int lower = (Constants.SCREEN_HEIGHT - GAP_SIZE) / 2;
        int upper = lower + GAP_SIZE;

        gapY = random.nextInt(min(lower,upper), max(lower, upper));
    }

    public void update() {
        x -= PIPE_SPEED;
    }

    public void draw(Graphics g) {
        int topEnd = gapY - GAP_SIZE/2;
        int topStart = topEnd - height;
        int bottomStart = gapY + GAP_SIZE/2;

        g.drawImage(upperImage, x, topStart, width, height, null);
        g.drawImage(lowerImage, x, bottomStart, width, height, null);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, 0, width, gapY - GAP_SIZE/2);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, gapY + GAP_SIZE/2, width, Constants.SCREEN_HEIGHT - (gapY + GAP_SIZE/2));
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean b) {
        passed = b;
    }
}
