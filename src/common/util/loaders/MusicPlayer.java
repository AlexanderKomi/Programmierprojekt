package common.util.loaders;

import common.util.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;

import static common.util.loaders.ImageLoader.getBufferedInputStream;

public final class MusicPlayer {

    private static Player player;
    private static Thread musicThread;
    private static File   file;

    public static void play() {
        if ( musicThread.isAlive() ) {musicThread.stop();}
        if ( file == null || musicThread == null ) {
            Logger.log( "no file to play detected. Please load a .mp3 first." );
            return;
        }
        Logger.log( "music starts playing" );
        musicThread.start();
    }

    public static void pause() {
        Logger.log( "music paused" );
        musicThread.suspend();
    }

    public static void resume() {
        Logger.log( "music playing again" );
        musicThread.resume();
    }

    public static void stop() {
        if ( musicThread != null ) {
            musicThread.stop();
            musicThread = null;
        }
        if ( file != null ) {file = null;}
    }

    public static void shutdown() {
        stop();
        if ( player != null ) {
            player.close();
        }
        Logger.log( MusicPlayer.class + " : dependencies finaly shut down !!!" );
    }

    public static void playFile( String path ) {
        try {
            player = new Player( getBufferedInputStream( path ) );
            musicThread = new Thread( () -> {
                try {
                    player.play();
                }
                catch ( JavaLayerException e ) {
                    e.printStackTrace();
                }
            } );
        }
        catch ( JavaLayerException e ) {
            e.printStackTrace();
        }
    }

}
