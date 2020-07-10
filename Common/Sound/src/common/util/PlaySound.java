/**
 * @author Julian Sender
 */
package common.util;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class to play sounds
 */
public final class PlaySound {

    private static Player p = new Player();

    /**
     *
     * @param path Player needs the path of mediafile as parameter
     */
    public static void playSound( final String path ) {
        p.playSound( path, false );
    }

    /**
     * Plays and resets sound
     * @param path Player needs the path of mediafile as parameter
     */
    public static void playAndResetSound( final String path ) {
        p.playSound( path, true );
    }

    /**
     * Plays a sound
     * @param path Player needs the path of mediafile as parameter
     * @param resetMediaPlayer true, if it's desired to reset mediaplayer
     */
    public static void playSound( final String path, final boolean resetMediaPlayer ) {
        p.playSound( path, resetMediaPlayer );
    }

    /**
     * Check if next sound is different to previous, else save performance :-)
     */
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
            if (prop_mediaPlayer != null) {
                Platform.runLater( () -> prop_mediaPlayer.play() );
            }
        }

        /**
         * Resets timer for MediaPlayer
         * @param mediaPlayer needs instance of mediaplayer where to reset timer
         */
        private void resetTimer( MediaPlayer mediaPlayer ) {
            if ( mediaPlayer.getCurrentTime().lessThan( mediaPlayer.getTotalDuration() ) ) {
                mediaPlayer.stop();
                mediaPlayer.seek( Duration.ZERO );
            }
        }

        /**
         * Creates isntance of Mediaplayer
         * @return returns the new instance of mediaplayer
         */
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

        /**
         * Lets it all work together
         */
        @Override
        public void run() {
            playSound( musicFile, resetTimer );
        }
    }



}
