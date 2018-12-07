package de.hsh.dennis.model.audio;

import common.util.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayer {

    private Player player;
    Thread musikThreat;

    public AudioPlayer() {
    }

    public void play() {
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

    public void loadFile(String path) {

        File file = new File(path);
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
