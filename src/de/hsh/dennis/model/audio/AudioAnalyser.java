package de.hsh.dennis.model.audio;


import common.util.Logger;
import v4lk.lwbd.BeatDetector;
import v4lk.lwbd.util.Beat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.hsh.dennis.model.audio.AudioConfig.DelayBetweenSpawns._default;


public class AudioAnalyser {

    private BeatDetector detector;
    private File audioFile;


    private double sensitivityFixed = 0.2d;
    private double sensitivity = 0.2d;


    private double spawnDelay = _default;

    public AudioAnalyser() {
        detector = new BeatDetector();
    }

    public void loadSound(String path){
        audioFile = new File(this.getClass().getResource(path).getPath());
    }

    public void loadSound(File audioFile){
        this.audioFile = audioFile;
    }

    public void clearAudioFile(){
        this.audioFile = null;
    }

    public List<Double> getSpawnTimes(){
        if(audioFile == null){
            Logger.log(this.getClass().getName() + " : no File detected ...");
        }else{


            try {

                Beat[] beats = BeatDetector.detectBeats(audioFile, BeatDetector.AudioType.MP3);

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
                Logger.log("detected " + spawnTimes.size() + " usable beats in " + audioFile.getName());
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