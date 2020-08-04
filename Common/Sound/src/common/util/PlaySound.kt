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
     *
     * @param path Player needs the path of mediafile as parameter
     */
    @JvmStatic
    fun playSound(path: String) {
        p.playSound(path, false)
    }

    /**
     * Plays and resets sound
     * @param path Player needs the path of mediafile as parameter
     */
    @JvmStatic
    fun playAndResetSound(path: String) {
        p.playSound(path, true)
    }

    /**
     * Plays a sound
     * @param path Player needs the path of mediafile as parameter
     * @param resetMediaPlayer true, if it's desired to reset mediaplayer
     */
    @JvmStatic
    fun playSound(path: String,
                  resetMediaPlayer: Boolean) {
        p.playSound(path, resetMediaPlayer)
    }

    /**
     * Check if next sound is different to previous, else save performance :-)
     */
    internal class Player : Runnable {
        private var musicFile: String? = null
        private var currentMedia: Media? = null
        private var prop_mediaPlayer: MediaPlayer? = null
        private var resetTimer = false

        @Throws(MediaException::class)
        fun playSound(path: String,
                      resetMediaPlayer: Boolean) {
            initProperties(path, resetMediaPlayer)
            if (prop_mediaPlayer != null) {
                if (resetTimer) {
                    resetTimer(prop_mediaPlayer!!)
                }
                Platform.runLater { prop_mediaPlayer!!.play() }
            }
            // else { throw new NullPointerException("Mediaplayer is null"); }
        }

        /**
         * Resets timer for MediaPlayer
         * @param mediaPlayer needs instance of mediaplayer where to reset timer
         */
        private fun resetTimer(mediaPlayer: MediaPlayer) {
            if (mediaPlayer.currentTime
                            .lessThan(mediaPlayer.totalDuration)) {
                mediaPlayer.stop()
                mediaPlayer.seek(Duration.ZERO)
            }
        }

        private fun initProperties(path: String,
                                   resetMediaPlayer: Boolean) {
            resetTimer = resetMediaPlayer
            if (musicFile != null && musicFile == path) { //Performance-Kniff
                return
            }
            if (prop_mediaPlayer != null) {
                prop_mediaPlayer!!.dispose()
            }
            musicFile = path
            if (musicFile == null) {
                throw NullPointerException("MusicFile String is null")
            }
            currentMedia = AudioBuffer.loadMedia(path)
            if (currentMedia == null) {
                throw NullPointerException("Current Media is null: $musicFile")
            }
            prop_mediaPlayer = createMediaPlayer()
        }

        /**
         * Creates isntance of Mediaplayer
         * @return returns the new instance of mediaplayer
         */
        private fun createMediaPlayer(): MediaPlayer {
            val mediaPlayer = MediaPlayer(
                    currentMedia)
            mediaPlayer.isAutoPlay = false
            mediaPlayer.onEndOfMedia = Runnable {
                mediaPlayer.stop()
                mediaPlayer.seek(Duration.ZERO)
            }
            return mediaPlayer
        }

        /**
         * Lets it all work together
         */
        override fun run() {
            musicFile?.let { playSound(it, resetTimer) }
        }
    }
}
