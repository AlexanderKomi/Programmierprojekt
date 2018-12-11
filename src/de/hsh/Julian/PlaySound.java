package de.hsh.Julian;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class PlaySound {

    public static void playSound(){
        String musicFile = "src\\de\\hsh\\Julian\\collision.wav";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
