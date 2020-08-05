package common.actor

import javafx.scene.image.Image
import java.io.IOException
import java.util.*

internal object TextureBuffer {
    private val fileToImage = HashMap<String, Image>()
    fun loadImage(fileName: String): Image {
        if (!fileToImage.containsKey(fileName)) {
            try {
                fileToImage[fileName] = ImageLoader.loadImage(fileName)
            } catch (npe: NullPointerException) {
                npe.printStackTrace()
            } catch (npe: IOException) {
                npe.printStackTrace()
            }
        }
        return fileToImage[fileName]!!
    }
}
