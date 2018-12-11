package de.hsh.kevin.logic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.HashMap;

public class Sound {

    private static HashMap<enmSounds, Media> sounds;
    private static MediaPlayer mediaPlayer;
    private static final String path = "src\\de\\hsh\\kevin\\res\\sounds\\";
    private static final double soundVolume = 0.5;

    static {
        if (Config.getSoundOption() == enmSoundOptions.on && sounds == null) {
            sounds = new HashMap<>();
            sounds.put(enmSounds.collision, new Media(new File(path + "collision.mp3").toURI().toString()));
            sounds.put(enmSounds.badPaketIgnored, new Media(new File(path + "bad_paket.mp3").toURI().toString()));
            sounds.put(enmSounds.hit, new Media(new File(path + "hit.mp3").toURI().toString()));
        }
    }

    public static void playSound(enmSounds sound) {
        if (Config.getSoundOption() == enmSoundOptions.off) {
            return;
        }
        Thread play = new Thread(new Runnable() {
            public void run() {
                mediaPlayer = new MediaPlayer(sounds.get(sound));
                mediaPlayer.setVolume(soundVolume);
                mediaPlayer.play();
            }
        });
        play.start();

    }
}
