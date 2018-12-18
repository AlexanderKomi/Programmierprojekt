package common.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlaySound {

    private static String      musicFile = null;
    private static MediaPlayer mediaPlayer;
    private static Media       sound;

    public static void playSound(String path){
        if(musicFile==null||!musicFile.equals(path)) { //Perfomance-Kniff
            musicFile = path;
            sound = new Media( new File( musicFile ).toURI().toString() );
            mediaPlayer = new MediaPlayer(sound);
        }
        mediaPlayer.play();
    }

    public static void playSound2( String relativePath ) {
        if ( musicFile == null || !musicFile.equals( relativePath ) ) { //Perfomance-Kniff
            musicFile = relativePath;
            Media sound = new Media( PlaySound.class.getResource( relativePath ).toExternalForm() );
            mediaPlayer = new MediaPlayer( sound );
        }
        mediaPlayer.play();
    }
}
