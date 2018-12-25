package common.util;

import common.util.loaders.AudioBuffer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PlaySound {

    private static String      musicFile = null;
    private static MediaPlayer mediaPlayer;

    public static void playSound(String path){
        if ( musicFile == null || !musicFile.equals( path ) ) { //Perfomance-Kniff
            musicFile = path;
            Media sound = AudioBuffer.loadMedia( musicFile );
            mediaPlayer = new MediaPlayer( sound );
            mediaPlayer.setAutoPlay( false );
            mediaPlayer.setOnEndOfMedia( () -> {
                mediaPlayer.stop();
                mediaPlayer.seek( Duration.ZERO );
            } );
        }
        if ( mediaPlayer.getCurrentTime().lessThan( mediaPlayer.getTotalDuration() ) ) {
            mediaPlayer.stop();
            mediaPlayer.seek( Duration.ZERO );
        }
        mediaPlayer.play();
    }
}
