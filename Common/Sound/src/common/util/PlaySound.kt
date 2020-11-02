/**
 * @author Julian Sender
 */
package common.util

import javafx.application.Platform
import javafx.scene.media.Media
import javafx.scene.media.MediaException
import javafx.scene.media.MediaPlayer
import javafx.util.Duration

/**
 * Class to play sounds
 */
object PlaySound {
    private val p = Player()

    /**
     * Buffer Media before it needs to be played.
     */
    fun loadMedia(mediaPath: String): Media = AudioBuffer.loadMedia(mediaPath)!!

    /**
     * Plays a sound
     * @param path Player needs the path of mediafile as parameter
     * @param resetMediaPlayer true, if it's desired to reset mediaplayer
     */
    fun playSound(path: String, resetMediaPlayer: Boolean = false) = p.playSound(path, resetMediaPlayer)

    /**
     * Plays and resets sound
     * @param path Player needs the path of mediafile as parameter
     */
    fun playAndResetSound(path: String) = p.playSound(path, true)

    fun stop() = p.stop()

    fun pause() = this.p.pause()

    fun resume() = this.p.resume()

    /**
     * Check if next sound is different to previous, else save performance :-)
     */
    internal class Player : Runnable {

        private var musicFile: String? = null
        private var currentMedia: Media? = null
        private var propMediaplayer: MediaPlayer? = null
        private var resetTimer = false

        fun stop() = this.propMediaplayer?.stop()
        fun pause() = this.propMediaplayer?.pause()
        fun resume() = this.propMediaplayer?.play()

        @Throws(MediaException::class)
        fun playSound(path: String, resetMediaPlayer: Boolean) {

            fun createMediaPlayer(): MediaPlayer {
                val mediaPlayer = MediaPlayer(currentMedia)
                mediaPlayer.isAutoPlay = false
                mediaPlayer.onEndOfMedia = Runnable {
                    mediaPlayer.stop()
                    mediaPlayer.seek(Duration.ZERO)
                }
                return mediaPlayer
            }

            resetTimer = resetMediaPlayer
            if (musicFile != null && musicFile == path) { //Performance-Kniff
                return
            }
            if (propMediaplayer != null) propMediaplayer!!.dispose()
            musicFile = path
            currentMedia = AudioBuffer.loadMedia(path)
            propMediaplayer = createMediaPlayer()

            if (propMediaplayer != null) {
                if (resetTimer) {
                    resetTimer(propMediaplayer!!)
                }
                Platform.runLater { propMediaplayer!!.play() }
            }
        }

        /**
         * Resets timer for MediaPlayer
         * @param mediaPlayer needs instance of mediaplayer where to reset timer
         */
        private fun resetTimer(mediaPlayer: MediaPlayer) {
            if (mediaPlayer.currentTime.lessThan(mediaPlayer.totalDuration)) {
                mediaPlayer.stop()
                mediaPlayer.seek(Duration.ZERO)
            }
        }

        /**
         * Lets it all work together
         */
        override fun run() {
            musicFile?.let { playSound(it, resetTimer) }
        }
    }
}
