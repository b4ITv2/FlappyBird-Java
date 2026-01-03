import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DataManager {
    public List<ScoreEntry> loadHighScores() {
        List<ScoreEntry> scoreEntries = new ArrayList<>();

        Path highScoresPath = Path.of(Constants.HIGHSCORES_PATH);
        if (!Files.exists(highScoresPath)) {
           return scoreEntries;
        }

        try (Stream<String> lines = Files.lines(highScoresPath)) {
            scoreEntries = lines
                    .map(line -> {
                        String[] parts = line.split(":");
                        if(parts.length == 2) {
                            try {
                                return new ScoreEntry(parts[0], Integer.parseInt(parts[1]));
                            } catch (NumberFormatException e) {
                                System.err.println("Error when reading the data file, score is not a number!");
                            }
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .sorted()
                    .limit(10)
                    .toList();

        } catch (IOException e) {
            System.err.println("Error when reading the data file!");
        }

        return scoreEntries;
    }

    public void saveScore(ScoreEntry scoreEntry) {
        List<ScoreEntry> scoreEntries = Stream
                .concat(loadHighScores().stream(), Stream.of(scoreEntry))
                .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
                .limit(10)
                .toList();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.HIGHSCORES_PATH))) {
            scoreEntries.stream()
                    .map(ScoreEntry::toString)
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line);
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            System.err.println("Error when writing the data file!");
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error when writing the data file!");
        }
    }
}
