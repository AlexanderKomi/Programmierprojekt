package common.sound

import javafx.scene.media.Media
import javafx.scene.media.MediaException
import java.io.File
import java.io.FileNotFoundException
import java.util.*

object AudioBuffer {
    private val fileToMedia = HashMap<String, Media?>()

    @Throws(MediaException::class)
    private fun addFile(filepath: String) {
        try {
            fileToMedia[filepath] = newMedia(filepath)
        } catch (npe: NullPointerException) {
            npe.printStackTrace()
        } catch (npe: FileNotFoundException) {
            npe.printStackTrace()
        }
    }

    @Throws(MediaException::class,
            FileNotFoundException::class)
    private fun newMedia(fileName: String): Media {
        val url = AudioBuffer::class.java.getResource(fileName)
                  ?: throw FileNotFoundException(
                          fileName)
        val soundFile = File(url.path)
        if (soundFile.exists()) {
            return Media(soundFile.toURI().toString())
        }
        throw FileNotFoundException(fileName)
    }

    private operator fun contains(fileName: String): Boolean {
        return fileToMedia.containsKey(fileName)
    }

    @Throws(MediaException::class)
    fun loadMedia(fileName: String): Media? {
        if (!contains(fileName)) {
            addFile(fileName)
        }
        return fileToMedia[fileName]
    }
}
