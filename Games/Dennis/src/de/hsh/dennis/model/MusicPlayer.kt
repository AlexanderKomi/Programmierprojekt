package de.hsh.dennis.model

import common.util.PlaySound

object MusicPlayer {
    private var mediaFile: String? = null

    fun play() = PlaySound.playSound(mediaFile!!)
    fun pause() = PlaySound.pause()
    fun resume() = PlaySound.resume()
    fun stop() = PlaySound.stop()
    fun shutdown() {}

    fun loadFile(mp3Name: String) {
        this.mediaFile = "/de/de.hsh/dennis/resources/audioFiles/$mp3Name"
        PlaySound.loadMedia(this.mediaFile!!)
    }

}
