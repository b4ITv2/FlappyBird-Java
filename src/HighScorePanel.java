import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScorePanel extends JPanel {
    private final DefaultListModel<String> listModel;
    private final DataManager dataManager;

    public HighScorePanel(GameWindow gameWindow) {
        this.dataManager = new DataManager();
        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("TOP 10 HIGH SCORES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Constants.BIRD_YELLOW);
        this.add(titleLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        JScrollPane jScrollPane = getJScrollPane();
        this.add(jScrollPane, BorderLayout.CENTER);

        JButton restartButton = new JButton("PLAY");
        restartButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        restartButton.addActionListener(_ -> gameWindow.showGame());
        restartButton.setBackground(Constants.PIPE_GREEN);
        this.add(restartButton, BorderLayout.WEST);


        JButton menuButton = new JButton("MENU");
        menuButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        menuButton.addActionListener(_ -> gameWindow.showMenu());
        menuButton.setBackground(Constants.PIPE_GREEN);
        this.add(menuButton, BorderLayout.EAST);

        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        exitButton.addActionListener(_ -> System.exit(0));
        exitButton.setBackground(Constants.PIPE_GREEN);
        this.add(exitButton, BorderLayout.SOUTH);
    }

    private JScrollPane getJScrollPane() {
        JList<String> scoreListDisplay = new JList<>(listModel);
        scoreListDisplay.setBackground(Constants.BACKGROUND_BLUE);
        scoreListDisplay.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50));

        DefaultListCellRenderer defaultListCellRenderer = new DefaultListCellRenderer();
        defaultListCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        scoreListDisplay.setCellRenderer(defaultListCellRenderer);

        return new JScrollPane(scoreListDisplay);
    }

    public void refreshHighScores() {
        listModel.clear();
        List<ScoreEntry> scoreEntries = dataManager.loadHighScores();

        if (scoreEntries.isEmpty()) {
            listModel.addElement("No scores yet!");
        } else {
            int rank = 1;
            for (ScoreEntry scoreEntry : scoreEntries) {
                listModel.addElement(rank + ". " + scoreEntry.getName() + " - " + scoreEntry.getScore());
                ++rank;
            }
        }
    }
}
