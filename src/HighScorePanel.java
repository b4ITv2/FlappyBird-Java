import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScorePanel extends JPanel {
    private final GameWindow gameWindow;
    private final DefaultListModel<String> listModel;
    private final DataManager dataManager;

    public HighScorePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
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

        JButton restaurantButton = new JButton("PLAY");
        restaurantButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        restaurantButton.addActionListener(_ -> gameWindow.showGame());
        restaurantButton.setBackground(Constants.PIPE_GREEN);
        this.add(restaurantButton, BorderLayout.SOUTH);
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
