import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class GameEngine implements ActionListener {
    private Bird bird;
    private final ArrayList<Pipe> pipes;
    private final Background background;
    private final Timer timer;
    private final GamePanel gamePanel;
    private int backgroundX;
    private int pipeSpawnCounter;
    private int score;
    private final SoundManager soundManager;

    public GameEngine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.bird = new Bird();
        this.pipes = new ArrayList<>();
        this.background = new Background();
        this.backgroundX = 0;
        this.pipeSpawnCounter = 0;
        this.soundManager = new SoundManager();
        this.soundManager.playBackground();

        this.timer = new Timer(20, this);
    }

    public void restart() {
        score = 0;
        pipes.clear();
        backgroundX = 0;
        bird = new Bird();
        timer.restart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.update();
        checkCollisions();
        moveBackground();
        movePipes();
        gamePanel.repaint();
    }

    private void moveBackground() {
        if (backgroundX - 1 <= -Constants.SCREEN_WIDTH) {
            backgroundX = 0;
        }
        backgroundX -= 1;
    }

    private void movePipes() {
        ++pipeSpawnCounter;
        if (pipeSpawnCounter >= 100) {
            pipes.add(new Pipe(Constants.SCREEN_WIDTH));
            pipeSpawnCounter = 0;
        }

        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            pipe.update();
            if (pipe.isOffScreen()) {
                iterator.remove();
            }
        }
    }

    private void checkCollisions() {
        if (bird.getY() < 0 || bird.getY() > (Constants.SCREEN_HEIGHT - bird.getHeight())) {
            soundManager.playDeath();
            gameOver();
        }

        Rectangle birdBounds = bird.getBounds();
        for (Pipe pipe : pipes) {
            Rectangle topBounds = pipe.getTopBounds();
            Rectangle bottomBounds = pipe.getBottomBounds();
            if (topBounds.intersects(birdBounds) || bottomBounds.intersects(birdBounds)) {
                soundManager.playHit();
                soundManager.playDeath();
                gameOver();
            }

            if(!pipe.isPassed() && (topBounds.x + topBounds.width) < (birdBounds.x + birdBounds.width)) {
                ++score;
                pipe.setPassed(true);
                soundManager.playScore();
            }
        }
    }

    private void gameOver() {
        timer.stop();
        System.out.println("GAME OVER!");

        gamePanel.gameOver(score);
    }

    public void jump() {
        bird.jump();
        soundManager.playJump();
    }

    public Bird getBird() {
        return bird;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Background getBackground() {
        return background;
    }

    public int getBackgroundX() {
        return backgroundX;
    }
}
