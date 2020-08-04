package de.hsh.dennis.model.audio

import common.util.Logger.log
import de.hsh.dennis.model.audio.AudioConfig.DelayBetweenSpawns
import v4lk.lwbd.BeatDetector
import java.io.IOException
import java.io.InputStream
import java.util.*

class AudioAnalyser {
    private var audioStream: InputStream? = null
    private val sensitivityFixed = 0.2
    var sensitivity = 0.2
    private var spawnDelay = DelayBetweenSpawns._default
    fun loadSound(mp3Name: String) {
        val path = "/de/de.hsh/dennis/resources/audioFiles/"
        audioStream = javaClass.getResourceAsStream(path + mp3Name)
    }

    fun clearAudioFile() {
        audioStream = null
    }

    val spawnTimes: List<Double>
        get() {
            if (audioStream == null) {
                log(this.javaClass.name + " : no File detected ...")
            } else {
                try {
                    val beats = BeatDetector.detectBeats(audioStream, BeatDetector.AudioType.MP3)
                    val spawnTimes: MutableList<Double> = ArrayList()
                    for (b in beats) {
                        if (b.energy >= sensitivity) {
                            if (spawnTimes.size >= 1) {
                                if (b.timeMs / 1000.0 - spawnTimes[spawnTimes.size - 1] >= spawnDelay) {
                                    spawnTimes.add(b.timeMs / 1000.0)
                                }
                            } else {
                                spawnTimes.add(b.timeMs / 1000.0)
                            }
                        }
                    }
                    log("detected " + spawnTimes.size + " usable beats.")
                    return spawnTimes
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return ArrayList()
        }

    fun resetSensitivity() {
        sensitivity = sensitivityFixed
    }

    fun setSpawnDelay(spawnDelay: Double) {
        this.spawnDelay = spawnDelay
    }

}
