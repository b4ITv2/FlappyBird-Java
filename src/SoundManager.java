import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SoundManager {
    private Clip jumpClip;
    private Clip scoreClip;
    private Clip deathClip;

    public SoundManager() {
        jumpClip = loadSound(Constants.JUMP_SOUND_PATH);
        scoreClip = loadSound(Constants.SCORE_SOUND_PATH);
        deathClip = loadSound(Constants.DEATH_SOUND_PATH);
    }

    private Clip loadSound(String path) {
        try {
            Path soundPath = Path.of(path);
            if (!Files.exists(soundPath)) {
                System.out.println("Error: Sound not found!");
                return null;
            }

            File soundFile = soundPath.toFile();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            return clip;
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Sound format not supported!");
        } catch (IOException e) {
            System.out.println("Error: Sound File not readable!");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Audio board not available!");
        }

        return null;
    }

    private void play(Clip clip) {
        if (clip == null) {
            return;
        }

        if(clip.isRunning()) {
            clip.stop();
        }

        clip.setFramePosition(0);
        clip.start();
    }

    public void playJump() {
        play(jumpClip);
    }

    public void playScore() {
        play(scoreClip);
    }

    public void playDeath() {
        play(deathClip);
    }
}
