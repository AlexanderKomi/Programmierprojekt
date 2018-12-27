package common.util;

import common.util.loaders.AudioBuffer;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public final class PlaySound {

    private static Player p = new Player();

    public static void playSound( final String path ) {
        p.playSound( path, false );
    }

    public static void playAndResetSound( final String path ) {
        p.playSound( path, true );
    }

    public static void playSound( final String path, final boolean resetMediaPlayer ) {
        p.playSound( path, resetMediaPlayer );
    }

    static class Player implements Runnable {
        private String      musicFile    = null;
        private Media       currentMedia = null;
        private MediaPlayer prop_mediaPlayer;
        private boolean     resetTimer;

        public void playSound( final String path, final boolean resetMediaPlayer ) {
            resetTimer = resetMediaPlayer;
            if ( musicFile == null || !musicFile.equals( path ) ) { //Perfomance-Kniff
                try {
                    if ( prop_mediaPlayer != null ) {
                        prop_mediaPlayer.dispose();
                    }
                    musicFile = path;
                    currentMedia = AudioBuffer.loadMedia( path );
                    prop_mediaPlayer = createMediaPlayer();
                }
                catch ( MediaException me ) {
                    me.printStackTrace();
                }
            }
            if ( resetMediaPlayer ) {
                resetTimer( prop_mediaPlayer );
            }
            Platform.runLater( () -> prop_mediaPlayer.play() );
        }

        private void resetTimer( MediaPlayer mediaPlayer ) {
            if ( mediaPlayer.getCurrentTime().lessThan( mediaPlayer.getTotalDuration() ) ) {
                mediaPlayer.stop();
                mediaPlayer.seek( Duration.ZERO );
            }
        }

        private MediaPlayer createMediaPlayer() {
            MediaPlayer mediaPlayer = new MediaPlayer( currentMedia );
            mediaPlayer.setAutoPlay( false );
            mediaPlayer.setOnEndOfMedia( () -> {
                mediaPlayer.stop();
                mediaPlayer.seek( Duration.ZERO );
                //mediaPlayer.dispose();
            } );
            return mediaPlayer;
        }

        @Override
        public void run() {
            playSound( musicFile, resetTimer );
        }
    }



}
