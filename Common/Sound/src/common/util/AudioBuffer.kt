package common.util

import javafx.scene.media.Media
import javafx.scene.media.MediaException
import java.io.File
import java.io.FileNotFoundException
import java.util.*

object AudioBuffer {
    private val fileToMedia = HashMap<String, Media>()

    @Throws(MediaException::class, FileNotFoundException::class)
    private fun newMedia(fileName: String): Media {
        val url = AudioBuffer::class.java.getResource(fileName)
                  ?: throw FileNotFoundException(fileName)
        val soundFile = File(url.path)
        if (soundFile.exists()) {
            return Media(soundFile.toURI().toString())
        }
        throw FileNotFoundException(fileName)
    }

    @Throws(MediaException::class, FileNotFoundException::class)
    fun loadMedia(fileName: String): Media? {
        if (!fileToMedia.containsKey(fileName)) {
            fileToMedia[fileName] = newMedia(fileName)
        }
        return fileToMedia[fileName]
    }
}
