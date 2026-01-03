import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private final GameEngine gameEngine;
    private final GameWindow gameWindow;

    public GamePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.gameEngine = new GameEngine(this);

        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int backgroundX = gameEngine.getBackgroundX();

        Background background = gameEngine.getBackground();
        g.drawImage(background.getImage(),backgroundX, 0, getWidth(), getHeight(), null);
        g.drawImage(background.getImage(),backgroundX + getWidth(), 0, getWidth(), getHeight(), null);

        for (Pipe pipe : gameEngine.getPipes()) {
            pipe.draw(g);
        }

        Bird bird = gameEngine.getBird();
        bird.draw(g);
    }

    public void gameOver(int score) {
        String name = JOptionPane.showInputDialog(this,
                "Game Over! Score: " + score + "\nType in your name",
                JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            DataManager dataManager = new DataManager();
            dataManager.saveScore(new ScoreEntry(name, score));
        }

        gameWindow.showHighScores();
    }

    public void resetGame() {
        gameEngine.restart();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameEngine.jump();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
