package common.actor

import common.actor.CollisionCheck.isInBounds
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.util.*

/**
 * Drawable is an image with bounds checking.
 * Also support Sprites, scaling and rotation.
 *
 *
 * Use the constructors as you like :)
 * Its easier to support multiple projects, when many constructors are given as an interface.
 *
 *
 * Every Drawable can be drawn on a canvas.
 */
open class Drawable : Observable {
    val id: Int
    var height: Double = 0.0
    var width: Double = 0.0
    var x: Double
    var y: Double
    lateinit var currentImageName: String
    private var switchingBuffer = 0
    var switchImageDelay = 0.0
    protected var scaleWidth = 1.0
    var scaleHeight = 1.0
    private val imageView = ImageView()
    private val switchingImages = mutableListOf<Image>()
    var currentImage: Image
        get() = imageView.image
        set(currentImage) {
            imageView.image = currentImage
            height = this.currentImage.height
            width = this.currentImage.width
        }

    protected constructor(pictureFileName: String,
                          x: Double = 0.0,
                          y: Double = 0.0,
                          delay: Double = 0.0
    ) {
        currentImage = loadPicture(pictureFileName)
        this.x = x
        this.y = y
        this.switchImageDelay = delay
        id = id_counter
        id_counter++
    }

    protected constructor(pictureFilePaths: List<String>,
                          x: Double = 0.0,
                          y: Double = 0.0,
                          delay: Double = 0.0) {
        this.x = x
        this.y = y
        switchImageDelay = delay
        id = id_counter
        id_counter++
        pictureFilePaths.forEach { filePath -> addSwitchingImage(filePath) }
        if (switchingImages.size > 0) { currentImage = switchingImages[0] }
    }

    protected constructor(x: Double,
                          y: Double,
                          scale: Double,
                          picturePath: String) : this(picturePath, x, y) {
        scaleImage(scale)
    }

    private fun loadPicture(fileName: String): Image {
        currentImageName = fileName
        return TextureBuffer.loadImage(fileName)
    }

    private fun addSwitchingImage(picturePath: String) = loadPicture(picturePath).let { switchingImages.add(it) }

    /**
     * Switch switchingImages based on buffer implementation.
     */
    private fun switchImages() {
        if (switchingImages.isEmpty()) {
            return
        }
        if (switchingBuffer < switchImageDelay) {
            switchingBuffer++
            return
        }
        switchToNextImage()
    }

    private fun switchToNextImage() {
        switchingBuffer = 0
        val index = switchingImages.indexOf(currentImage)
        currentImage = if (index < switchingImages.size - 1) {
            switchingImages[index + 1]
        } else {
            switchingImages[0]
        }
    }

    protected fun scaleImageWidth(factor: Double) {
        if (factor <= 0) {
            x += width
        }
        width *= factor
        scaleWidth *= factor
        imageView.fitWidth = scaleWidth
        imageView.scaleX = scaleWidth
    }

    protected fun scaleImageHeight(factor: Double) {
        height *= factor
        scaleHeight *= factor
        imageView.fitHeight = scaleHeight
        imageView.scaleY = scaleHeight
    }

    protected fun scaleImage(factor: Double) {
        scaleImageHeight(factor)
        scaleImageWidth(factor)
    }

    // ---------------------------------- START DRAW ----------------------------------
    open fun draw(canvas: Canvas) = draw(canvas, 0.0, 0.0)

    fun draw(canvas: Canvas,
             offset_to_new_x: Double,
             offset_to_new_y: Double) {
        val inBoundsPos = calcPosAfterBounds(
                isInBounds(x, y,
                           width, height,
                           canvas.width, canvas.height,
                           offset_to_new_x, offset_to_new_y),
                offset_to_new_x,
                offset_to_new_y)
        val oldPos = pos
        pos = inBoundsPos
        pos = beforeDrawing(oldPos, inBoundsPos) // Maybe reset ? :)
        switchImages()
        //this.setCurrentImage(temp);
        canvas.graphicsContext2D.drawImage(currentImage, x, y, width, height)
    }

    /**
     * Override this method, to apply any new checks or manipulate the position before the new position is drawn.
     *
     * @param current_pos The current position of the Drawable
     * @param new_pos     The next position of the Drawable
     * @return Returns the new position of the Drawable.
     */
    protected open fun beforeDrawing(current_pos: DoubleArray,
                                     new_pos: DoubleArray): DoubleArray = new_pos

    // ---------------------------------- END DRAW ----------------------------------
    private fun calcPosAfterBounds(isInBounds: BooleanArray,
                                   new_x: Double,
                                   new_y: Double): DoubleArray {
        val temp = doubleArrayOf(x, y)
        if (isInBounds[0]) {
            temp[0] += new_x
        }
        if (isInBounds[1]) {
            temp[1] += new_y
        }
        return temp
    }

    override fun equals(other: Any?): Boolean =
            if (other is Drawable) {
                pos.contentEquals(other.pos) &&
                height == other.height &&
                width == other.width &&
                currentImageName == other.currentImageName
            } else {
                false
            }

    override fun toString(): String {
        return """${this.javaClass}(name:$currentImageName, x:$x, y:$y, width:$width, height:$height)"""
    }

    inline fun setPos(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    var pos: DoubleArray
        inline get() = doubleArrayOf(x, y)
        inline set(pos) {
            x = pos[0]
            y = pos[1]
        }

    fun setCurrentImage(filePath: String) {
        currentImage = loadPicture(filePath)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + height.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + currentImageName.hashCode()
        result = 31 * result + switchingBuffer
        result = 31 * result + switchImageDelay.hashCode()
        result = 31 * result + scaleWidth.hashCode()
        result = 31 * result + scaleHeight.hashCode()
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    companion object {
        private var id_counter = 0
    }
}
