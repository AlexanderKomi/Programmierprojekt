package common.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.rmi.runtime.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayer {

    public static class MusicPlayer {

        private Player player;
        Thread musikThreat;
        private File file;

        public void play() {
            if(musikThreat.isAlive()){musikThreat.stop();}
            if(file == null || musikThreat == null){
                Logger.log("no file to play detected. Please load a .mp3 first.");
                return;
            }
            Logger.log("music starts playing");
            musikThreat.start();
        }

        public void pause() {
            Logger.log("music paused");
            musikThreat.suspend();
        }

        public void resume() {
            Logger.log("music playing again");
            musikThreat.resume();
        }

        public void stop(){
            if(musikThreat != null){
                musikThreat.stop();
                musikThreat = null;
            }
            if(file != null){file = null;}
        }

        public void shutdown(){
            stop();
            player.close();
            Logger.log("MusikPlayer and dependencies finaly shut down !!!");
        }

        public void loadFile(String path) {

            file = new File(path);
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                try {
                    player = new Player(bis);
                    musikThreat = new Thread(() -> {
                        try {
                            player.play();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
