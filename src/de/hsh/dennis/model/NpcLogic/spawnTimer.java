package de.hsh.dennis.model.NpcLogic;

public class spawnTimer {

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
}
