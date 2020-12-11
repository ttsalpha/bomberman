package sontran.utility;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import static sontran.BombermanGame.player;
import static sontran.levels.NextLevel.wait;

public class SoundManager extends JFrame {
    public static Clip title_screen;
    public static Clip bomb_explosion;
    public static Clip just_died;

    public static boolean isSoundDied;
    public static boolean isSoundTitle;

    private static boolean isSoundComplete;

    public SoundManager(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);

            assert url != null;
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            if (sound.equals("title")) {
                title_screen = AudioSystem.getClip();
                title_screen.open(audioIn);
                title_screen.start();
                title_screen.loop(10);
            }
            if (sound.equals("explosion")) {
                bomb_explosion = AudioSystem.getClip();
                bomb_explosion.open(audioIn);
                bomb_explosion.start();
            }
            if (sound.equals("just_died")) {
                just_died = AudioSystem.getClip();
                just_died.open(audioIn);
                just_died.start();
            }
            if (sound.equals("default")) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (!isSoundTitle) {
            new SoundManager("sound/title_screen.wav", "title");
            isSoundTitle = true;
        }
        if (!player.isLife()) {
            title_screen.close();
            bomb_explosion.close();
            if (!isSoundDied) {
                new SoundManager("sound/just_died.wav", "just_died");
                isSoundDied = true;
            }
        }
        if (wait) {
            title_screen.close();
            bomb_explosion.close();
            if (!isSoundComplete) {
                new SoundManager("sound/level_complete.wav", "default");
                isSoundComplete = true;
            }
        }
    }
}
