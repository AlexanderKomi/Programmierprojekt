package de.hsh.Julian;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class PlaySound {

    public static String musicFile=null;
    public static MediaPlayer mediaPlayer;

    public static void playSound(String path){

        if(musicFile==null||!musicFile.equals(path)) { //Perfomance-Kniff
            musicFile = path;
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        }
        mediaPlayer.play();
    }
}
