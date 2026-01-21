import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private final GamePanel gamePanel;
    private final HighScorePanel highScorePanel;

    public GameWindow() {
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        gamePanel = new GamePanel(this);
        highScorePanel = new HighScorePanel(this);
        MenuPanel menuPanel = new MenuPanel(this);

        mainContainer.add(gamePanel, Constants.GAME_PANEL_KEY);
        mainContainer.add(highScorePanel, Constants.HIGHSCORE_PANEL_KEY);
        mainContainer.add(menuPanel, Constants.MENU_PANEL_KEY);

        this.add(mainContainer);

        cardLayout.show(mainContainer, Constants.MENU_PANEL_KEY);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(128, 72, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setVisible(true);
    }

    public void showHighScores() {
        highScorePanel.refreshHighScores();
        cardLayout.show(mainContainer, Constants.HIGHSCORE_PANEL_KEY);
    }

    public void showGame() {
        gamePanel.resetGame();
        cardLayout.show(mainContainer, Constants.GAME_PANEL_KEY);
        gamePanel.requestFocusInWindow();
    }

    public void showMenu() {
        cardLayout.show(mainContainer, Constants.MENU_PANEL_KEY);
    }
}
