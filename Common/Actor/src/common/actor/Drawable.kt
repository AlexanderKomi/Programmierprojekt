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
    var x: Double
    var y: Double
    var height: Double = 0.0
    var width: Double = 0.0
    var currentImage: Image
        get() = imageView.image
        set(currentImage) {
            imageView.image = currentImage
            height = this.currentImage.height
            width = this.currentImage.width
        }

    var pos: DoubleArray
        inline get() = doubleArrayOf(x, y)
        inline set(pos) {
            x = pos[0]
            y = pos[1]
        }

    protected lateinit var currentImageName: String
    protected var scaleHeight = 1.0
    protected var scaleWidth = 1.0
    private var switchImageDelay = 0
    private var switchingBuffer = 0
    private val imageView = ImageView()
    private val switchingImages = mutableListOf<Image>()

    fun setPos(x: Double, y: Double) {
        this.x = x; this.y = y
    }

    infix fun setPos(xy: Pair<Double, Double>){
        this.x = xy.first; this.y = xy.second
    }

    infix fun setCurrentImage(filePath: String) {
        currentImage = TextureBuffer.loadImage(filePath)
        currentImageName = filePath
    }

    private constructor(x: Double, y: Double, delay: Int) {
        this.x = x
        this.y = y
        this.switchImageDelay = delay
        id = id_counter
        id_counter++
    }

    protected constructor(pictureFileName: String,
                          x: Double = 0.0,
                          y: Double = 0.0,
                          delay: Int = 0,
                          scale: Double = 1.0) :
        this(x, y, delay) {
        setCurrentImage(pictureFileName)
        scaleImage(scale)
    }

    protected constructor(
            pictureFilePaths: List<String>,
            x: Double = 0.0,
            y: Double = 0.0,
            delay: Int = 0,
            scale: Double = 1.0
    ) : this(x, y, delay) {
        switchingImages.addAll(pictureFilePaths.map { filePath -> TextureBuffer.loadImage(filePath) })
        if (switchingImages.size > 0) {
            currentImageName = pictureFilePaths[0]
            currentImage = switchingImages[0]
            scaleImage(scale)
        }
    }


    protected infix fun scaleImageWidth(factor: Double) {
        if (factor <= 0) {
            x += width
        }
        width *= factor
        scaleWidth *= factor
        imageView.fitWidth = scaleWidth
        imageView.scaleX = scaleWidth
    }

    protected infix fun scaleImageHeight(factor: Double) {
        height *= factor
        scaleHeight *= factor
        imageView.fitHeight = scaleHeight
        imageView.scaleY = scaleHeight
    }

    protected infix fun scaleImage(factor: Double) {
        scaleImageHeight(factor)
        scaleImageWidth(factor)
    }

    open infix fun draw(canvas: Canvas) = draw(canvas, 0.0, 0.0)

    protected open fun draw(canvas: Canvas,
                            offsetToNewX: Double,
                            offsetToNewY: Double) {
        /**
         * Switch switchingImages based on buffer implementation.
         */
        fun switchImages() {
            fun switchToNextImage() {
                switchingBuffer = 0
                val index = switchingImages.indexOf(currentImage)
                currentImage = if (index < switchingImages.size - 1) switchingImages[index + 1] else switchingImages[0]
            }
            if (switchingImages.isEmpty()) {
                return
            }
            if (switchingBuffer < switchImageDelay) {
                switchingBuffer++
                return
            }
            switchToNextImage()
        }

        fun calcPosAfterBounds(isInBounds: BooleanArray,
                               newX: Double,
                               newY: Double): DoubleArray = doubleArrayOf(x, y).also { temp ->
                                   if (isInBounds[0]) temp[0] += newX
                                   if (isInBounds[1]) temp[1] += newY
                               }

        if (offsetToNewX != 0.0 || offsetToNewY != 0.0) {
            val inBoundsPos = calcPosAfterBounds(
                    isInBounds(x, y,
                               width, height,
                               canvas.width, canvas.height,
                               offsetToNewX, offsetToNewY),
                    offsetToNewX,
                    offsetToNewY)
            val oldPos = pos
            pos = beforeDrawing(oldPos, inBoundsPos) // Maybe reset ? :)
        }
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
    protected open fun beforeDrawing(current_pos: DoubleArray, new_pos: DoubleArray): DoubleArray = new_pos


    override fun equals(other: Any?): Boolean =
            if (other is Drawable) {
                pos.contentEquals(other.pos) &&
                height == other.height &&
                width == other.width &&
                currentImageName == other.currentImageName
            } else false

    override fun toString(): String =
            """${this.javaClass}(name:$currentImageName, x:$x, y:$y, width:$width, height:$height)"""

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
