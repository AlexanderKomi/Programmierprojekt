package common.actor

import javafx.scene.image.Image

internal object TextureBuffer {
    private val fileToImage = hashMapOf<String, Image>()

    infix fun loadImage(fileName: String): Image {
        if (!fileToImage.containsKey(fileName)) {
            fileToImage[fileName] = ImageLoader.loadImage(fileName)
        }
        return fileToImage[fileName]!!
    }
}
