import java.awt.*;

public class Constants {
    private Constants() {}

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final int GRAVITY = 1;
    public static final int BIRD_X = 100;

    public static final String BIRD_IMAGE_PATH = "img/bird.png";
    public static final String UPPER_PIPE_IMAGE_PATH = "img/upper-pipe.png";
    public static final String LOWER_PIPE_IMAGE_PATH = "img/lower-pipe.png";
    public static final String BACKGROUND_PATH = "img/background.png";
    public static final String HIGHSCORES_PATH = "data/highscores.txt";
    public static final String DEATH_SOUND_PATH = "sounds/death.wav";
    public static final String JUMP_SOUND_PATH = "sounds/jump.wav";
    public static final String SCORE_SOUND_PATH = "sounds/score.wav";


    public static final String GAME_PANEL_KEY = "GAME";
    public static final String HIGHSCORE_PANEL_KEY = "HIGHSCORE";
    public static final String MENU_PANEL_KEY = "MENU";

    public static final Color PIPE_GREEN = Color.decode("#7db343");
    public static final Color BIRD_YELLOW = Color.decode("#fec400");
    public static final Color BACKGROUND_BLUE = Color.decode("#0099cc");
}
