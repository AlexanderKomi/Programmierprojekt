package de.hsh.dennis.model;

import common.util.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;

public class AudioPlayer {

    public static class MusicPlayer {

        private static Player player;
        static         Thread musikThreat;
        private static BufferedInputStream bis;
        private static boolean isPlaying = false;

        public static void play() {
            if ( musikThreat.isAlive() ) {musikThreat.stop();}
            if ( bis == null || musikThreat == null ) {
                Logger.log( "no file to play detected. Please load a .mp3 first." );
                return;
            }
            Logger.log( "music starts playing" );
            musikThreat.start();
            setIsPlaying(true);
        }

        public static void pause() {
            Logger.log( "music paused" );
            musikThreat.suspend();
            setIsPlaying(false);
        }

        public static void resume() {
            Logger.log( "music playing again" );
            setIsPlaying(true);
            musikThreat.resume();
        }

        public static void stop() {
            if ( musikThreat != null ) {
                musikThreat.stop();
                musikThreat = null;
            }
            if ( bis != null ) {bis = null;}
            setIsPlaying(false);
        }

        public static void shutdown() {
            stop();
            if ( player != null ) {

                player.close();
            }
            setIsPlaying(false);
            Logger.log( "MusikPlayer and dependencies finaly shut down !!!" );
        }

        public static void loadFile( String mp3Name ) {
            String path = "/de/hsh/dennis/resources/audioFiles/";

            InputStream fis =  AudioPlayer.class.getResourceAsStream(path + mp3Name);
            bis = new BufferedInputStream( fis );

            try {
                player = new Player( bis );
                musikThreat = new Thread( () -> {
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


        public static boolean getIsPlaying() {
            return isPlaying;
        }

        public static void setIsPlaying(boolean playing) {
            isPlaying = playing;
        }
    }
}
