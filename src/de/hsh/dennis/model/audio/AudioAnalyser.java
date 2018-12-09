package de.hsh.dennis.model.audio;


import common.util.Logger;
import v4lk.lwbd.BeatDetector;
import v4lk.lwbd.util.Beat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AudioAnalyser {

    private BeatDetector detector;
    private File audioFile;

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
                    if(b.energy >= 0.6){
                        spawnTimes.add(b.timeMs / 1000d);
                    }
                }
                return spawnTimes;
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        return new ArrayList<>();
    }



}