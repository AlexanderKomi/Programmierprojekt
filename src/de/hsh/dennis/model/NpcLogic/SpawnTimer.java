package de.hsh.dennis.model.NpcLogic;

public class SpawnTimer {

    //Uhr
    private double start = -1;

    public void start() {
        if (start == -1) {
            start = getCurrentSec();
        }
    }

    public double getCurrentTimeStamp() {
        start();
        return getCurrentSec() - start;
    }

    public double getCurrentSec() {
        return System.currentTimeMillis() / 1000d;
    }

    //Rundenzähler
    private double resetTime = -1;

    public double elabsedTime(){
        if(resetTime == -1){resetTimer();}
        return (System.currentTimeMillis() / 1000d) - resetTime;
    }

    public void resetTimer(){
        resetTime = System.currentTimeMillis() / 1000d;
    }
}
