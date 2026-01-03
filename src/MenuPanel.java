import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private GameWindow gameWindow;
    private Background background;

    public MenuPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.background = new Background();
        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        this.add(titleLabel, gridBagConstraints);

        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(_ -> gameWindow.showGame());
        this.add(startButton, gridBagConstraints);

        JButton scoreButton = new JButton("HIGH SCORES");
        scoreButton.addActionListener(_ -> gameWindow.showHighScores());
        this.add(scoreButton, gridBagConstraints);

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(_ -> System.exit(0));
        this.add(exitButton, gridBagConstraints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
        } else {
            System.err.println("Error: Background image not found in menu!");
        }
    }
}
