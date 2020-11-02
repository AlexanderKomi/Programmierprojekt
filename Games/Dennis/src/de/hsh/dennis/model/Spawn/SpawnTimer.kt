package de.hsh.dennis.model.Spawn

class SpawnTimer {
    //Uhr
    private var start = -1.0
    fun start() {
        if (start == -1.0) {
            start = currentSec
        }
    }

    val currentTimeStamp: Double
        get() {
            start()
            return currentSec - start
        }

    private val currentSec: Double
        get() = System.currentTimeMillis() / 1000.0

    //Rundenzähler
    private var resetTime = -1.0
    fun elabsedTime(): Double {
        if (resetTime == -1.0) {
            resetTimer()
        }
        return System.currentTimeMillis() / 1000.0 - resetTime
    }

    fun resetTimer() {
        resetTime = System.currentTimeMillis() / 1000.0
    }
}
