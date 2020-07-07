package de.hsh.dennis.model.audio;


import common.util.Logger;
import v4lk.lwbd.BeatDetector;
import v4lk.lwbd.util.Beat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static de.hsh.dennis.model.audio.AudioConfig.DelayBetweenSpawns._default;


public class AudioAnalyser {

    private BeatDetector detector;
    private InputStream audioStream;


    private double sensitivityFixed = 0.2d;
    private double sensitivity = 0.2d;


    private double spawnDelay = _default;

    public AudioAnalyser() {
        detector = new BeatDetector();
    }

    public void loadSound(String mp3Name){
        String path = "/de/de.hsh/dennis/resources/audioFiles/" ;



            audioStream = getClass().getResourceAsStream(path + mp3Name);

    }


    public void clearAudioFile(){
        this.audioStream = null;
    }

    public List<Double> getSpawnTimes(){
        if(audioStream == null){
            Logger.log(this.getClass().getName() + " : no File detected ...");
        }else{


            try {

                Beat[] beats = BeatDetector.detectBeats(audioStream, BeatDetector.AudioType.MP3);

                List<Double> spawnTimes = new ArrayList<>();


                for(Beat b : beats){
                    if(b.energy >= sensitivity){
                        if(spawnTimes.size() >= 1) {
                            if (spawnTimes.get(spawnTimes.size() - 1) != null && ((b.timeMs / 1000d) - spawnTimes.get(spawnTimes.size() - 1)) >= spawnDelay) {
                                spawnTimes.add(b.timeMs / 1000d);
                            }
                        }else{spawnTimes.add(b.timeMs / 1000d);}
                    }
                }
                Logger.log("detected " + spawnTimes.size() + " usable beats.");
                return spawnTimes;
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        return new ArrayList<>();
    }

    public void setSensitivity(double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public void resetSensitivity(){
        this.sensitivity = sensitivityFixed;
    }

    public void setSpawnDelay(double spawnDelay) {
        this.spawnDelay = spawnDelay;
    }

}