package de.hsh.dennis.model

import common.logger.Logger.log
import javazoom.jl.decoder.JavaLayerException
import javazoom.jl.player.Player
import java.io.BufferedInputStream

@Suppress("DEPRECATION")
class AudioPlayer {
    object MusicPlayer {
        private var player: Player? = null
        var musikThreat: Thread? = null
        private var bis: BufferedInputStream? = null
        var isPlaying = false
        fun play() {
            if (musikThreat!!.isAlive) {
                musikThreat!!.stop()
            }
            if (bis == null || musikThreat == null) {
                log("no file to play detected. Please load a .mp3 first.")
                return
            }
            log("music starts playing")
            musikThreat!!.start()
            isPlaying = true
        }

        fun pause() {
            log("music paused")
            musikThreat!!.suspend()
            isPlaying = false
        }

        fun resume() {
            log("music playing again")
            isPlaying = true
            musikThreat!!.resume()
        }

        fun stop() {
            if (musikThreat != null) {
                musikThreat!!.stop()
                musikThreat = null
            }
            if (bis != null) {
                bis = null
            }
            isPlaying = false
        }

        fun shutdown() {
            stop()
            if (player != null) {
                player!!.close()
            }
            isPlaying = false
            log("MusikPlayer and dependencies finaly shut down !!!")
        }

        fun loadFile(mp3Name: String) {
            val path = "/de/de.hsh/dennis/resources/audioFiles/"
            val fis = AudioPlayer::class.java.getResourceAsStream(path + mp3Name)
            bis = BufferedInputStream(fis)
            try {
                player = Player(bis)
                musikThreat = Thread(Runnable {
                    try {
                        player!!.play()
                    } catch (e: JavaLayerException) {
                        e.printStackTrace()
                    }
                })
            } catch (e: JavaLayerException) {
                e.printStackTrace()
            }
        }

    }
}
