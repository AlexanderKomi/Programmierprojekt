/**
 * @author Julian Sender
 */
package common.actor

import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO

/**
 * loading image
 */
object ImageLoader {
    /**
     * Returns Gets a path and returns image
     * @param relativeFilePath the relative path to the image
     * @return returns imagepath for SwingFXUtils
     */
    @JvmStatic
    @Throws(IOException::class)
    fun loadImage(relativeFilePath: String): Image =
            getInputStream(relativeFilePath).use { u ->
                return SwingFXUtils.toFXImage(ImageIO.read(u)!!, null)
            }

    /**
     *
     * Gets Inputstream of resources as Stream for pictures depending on relative path to picture
     * @param relativePath relative path to the picture
     * @return returns an inputstream containing the image
     * @throws NullPointerException Location not found
     */
    @Throws(NullPointerException::class, FileNotFoundException::class)
    private fun getInputStream(relativePath: String): InputStream {
        val x = ImageLoader::class.java.getResource(relativePath)
                ?: throw FileNotFoundException("File does not exist under relative Path: $relativePath")
        if (!File(x.path).exists()) {
            throw FileNotFoundException("File does not exist: " + x.path)
        }
        return ImageLoader::class.java.getResourceAsStream(relativePath)
    }
}
