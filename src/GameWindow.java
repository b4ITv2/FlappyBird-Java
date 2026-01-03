import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private GamePanel gamePanel;
    private HighScorePanel highScorePanel;

    public GameWindow() {
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        gamePanel = new GamePanel(this);
        highScorePanel = new HighScorePanel(this);

        mainContainer.add(gamePanel, Constants.GAME_PANEL_KEY);
        mainContainer.add(highScorePanel, Constants.HIGHSCORE_PANEL_KEY);

        this.add(mainContainer);

        cardLayout.show(mainContainer, Constants.GAME_PANEL_KEY);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(128, 72, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setVisible(true);
    }

    public void showHighScores() {
        highScorePanel.refreshHighScores();
        cardLayout.show(mainContainer, Constants.HIGHSCORE_PANEL_KEY);
        System.out.println("itt");
    }

    public void showGame() {
        gamePanel.resetGame();
        cardLayout.show(mainContainer, Constants.GAME_PANEL_KEY);
        gamePanel.requestFocusInWindow();
    }
}
