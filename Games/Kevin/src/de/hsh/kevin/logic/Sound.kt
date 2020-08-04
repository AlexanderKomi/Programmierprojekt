package de.hsh.kevin.logic

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.net.URISyntaxException
import java.util.*

/**
 * Läd alle Sounds des Spiels und erlaubt es diese abzuspielen
 *
 * @author Kevin
 */
object Sound {
    private val sounds = HashMap<enmSounds, Media>()
    private var mediaPlayer: MediaPlayer? = null
    private const val path = Config.resLocation + "sounds/"
    private const val soundVolume = 0.5

    /**
     * Spiel den Sound ab
     *
     * @param sound Option des abzuspielenden Sounds
     */
    fun playSound(sound: enmSounds?) {
        if (Config.soundOption === enmSoundOptions.off) {
            return
        }
        val play = Thread(Runnable {
            mediaPlayer = MediaPlayer(sounds[sound])
            mediaPlayer!!.volume = soundVolume
            mediaPlayer!!.play()
        })
        play.start()
    }

    init {
        try {
            sounds[enmSounds.collision] = Media(Sound::class.java.getResource(path + "collision.mp3")
                                                        .toURI()
                                                        .toString())
            sounds[enmSounds.badPaketIgnored] = Media(Sound::class.java.getResource(path + "bad_paket.mp3")
                                                              .toURI()
                                                              .toString())
            sounds[enmSounds.hit] = Media(Sound::class.java.getResource(path + "hit.mp3")
                                                  .toURI()
                                                  .toString())
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }
}
