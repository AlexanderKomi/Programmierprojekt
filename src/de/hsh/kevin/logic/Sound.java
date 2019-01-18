package de.hsh.kevin.logic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Läd alle Sounds des Spiels und erlaubt es diese abzuspielen
 * @author Kevin
 *
 */
public class Sound {

    private static HashMap<enmSounds, Media> sounds;
    private static MediaPlayer mediaPlayer;
    private static final String path = Config.resLocation + "sounds/";
    private static final double soundVolume = 0.5;

    static {
        if (sounds == null) {
            sounds = new HashMap<>();
            try {
                sounds.put(enmSounds.collision, new Media(Sound.class.getResource(path + "collision.mp3").toURI().toString()));
                sounds.put(enmSounds.badPaketIgnored, new Media(Sound.class.getResource(path + "bad_paket.mp3").toURI().toString()));
                sounds.put(enmSounds.hit, new Media(Sound.class.getResource(path + "hit.mp3").toURI().toString()));
                System.out.println( Sound.class.getResource( path + "hit.mp3" ).toURI().toString() );
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }            
        }
    }

    /**
     * Spiel den Sound ab
     * @param sound Option des abzuspielenden Sounds
     */
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
