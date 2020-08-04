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
    var height = 0.0
    var width = 0.0
    lateinit var name: String
    private var switchingBuffer = 0
    private var switchingDelay = 0.0
    protected var scaleX = 1.0
    private var scaleY = 1.0
    private val imageView = ImageView()
    private val switchingImages = ArrayList<Image>()
    var x: Double
    var y: Double
    var currentImage: Image
        get() = imageView.image
        set(currentImage) {
            imageView.image = currentImage
            height = this.currentImage.height
            width = this.currentImage.width
        }

    protected constructor(pictureFileName: String,
                          x: Double = 0.0,
                          y: Double = 0.0) {
        currentImage = loadPicture(pictureFileName)
        this.x = x
        this.y = y
        id = id_counter
        id_counter++
    }

    protected constructor(pictureFilePaths: List<String>,
                          x: Double,
                          y: Double,
                          delay: Int) : this(x, y, delay) {
        for (filePath in pictureFilePaths) {
            loadPicture(filePath).let { switchingImages.add(it) }
        }
        if (switchingImages.size > 0) {
            currentImage = switchingImages[0]
        }
    }

    protected constructor(mustHave: String,
                          asList: List<String>,
                          x: Double,
                          y: Double,
                          delay: Int) : this(asList, x, y, delay) {
        loadPicture(mustHave).let { switchingImages.add(it) }
    }

    private constructor(x: Double, y: Double, delay: Int) {
        this.x = x
        this.y = y
        switchingDelay = delay.toDouble()
        id = id_counter
        id_counter++
    }

    protected constructor(x: Double,
                          y: Double,
                          delay: Int,
                          pictureFileName: Array<out String>) : this(
            listOf<String>(*pictureFileName),
            x,
            y,
            delay)

    protected constructor(x: Double,
                          y: Double,
                          scale: Double,
                          picturePath: String) : this(picturePath,
                                                      x,
                                                      y) {
        scaleImage(scale)
    }

    private fun loadPicture(fileName: String): Image {
        name = fileName
        return TextureBuffer.loadImage(fileName)
    }

    /**
     * Switch switchingImages based on buffer implementation.
     */
    private fun switchImages() {
        if (switchingImages.isEmpty()) {
            return
        }
        if (switchingBuffer < switchingDelay) {
            switchingBuffer++
            return
        }
        switchToNextImage()
    }

    private fun switchToNextImage() {
        switchingBuffer = 0
        val index = switchingImages.indexOf(currentImage)
        if (index < switchingImages.size - 1) {
            currentImage = switchingImages[index + 1]
        } else {
            currentImage = switchingImages[0]
        }
    }

    protected fun scaleImageWidth(factor: Double) {
        if (factor <= 0) {
            x += width
        }
        width *= factor
        scaleX *= factor
        imageView.fitWidth = scaleX
        imageView.scaleX = scaleX
    }

    protected fun scaleImageHeight(factor: Double) {
        height *= factor
        scaleY *= factor
        imageView.fitHeight = scaleY
        imageView.scaleY = scaleY
    }

    protected fun scaleImage(factor: Double) {
        scaleImageHeight(factor)
        scaleImageWidth(factor)
    }

    // ---------------------------------- START DRAW ----------------------------------
    open fun draw(canvas: Canvas) {
        draw(canvas, 0.0, 0.0)
    }

    open fun draw(canvas: Canvas,
                  offset_to_new_x: Double,
                  offset_to_new_y: Double) {
        draw(canvas,
             canvas.width,
             canvas.height,
             offset_to_new_x,
             offset_to_new_y)
    }

    fun draw(
            canvas: Canvas,
            canvas_width: Double,
            canvas_height: Double,
            offset_to_new_x: Double,
            offset_to_new_y: Double) {
        val isInBounds = isInBounds(
                x,
                y,
                width,
                height,
                canvas_width,
                canvas_height,
                offset_to_new_x,
                offset_to_new_y)
        val in_bounds_pos = calcPosAfterBounds(isInBounds,
                                               offset_to_new_x,
                                               offset_to_new_y)
        val old_pos = pos
        pos = in_bounds_pos
        pos = beforeDrawing(old_pos, in_bounds_pos) // Maybe reset ? :)
        switchImages()
        //this.setCurrentImage(temp);
        canvas.graphicsContext2D
                .drawImage(currentImage,
                           x,
                           y,
                           width,
                           height)
    }

    /**
     * Override this method, to apply any new checks or manipulate the position before the new position is drawn.
     *
     * @param current_pos The current position of the Drawable
     * @param new_pos     The next position of the Drawable
     * @return Returns the new position of the Drawable.
     */
    protected open fun beforeDrawing(current_pos: DoubleArray,
                                     new_pos: DoubleArray): DoubleArray {
        return new_pos
    }

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

    override fun equals(other: Any?): Boolean {
        if (other is Drawable) {
            val d = other
            return Arrays.equals(pos,
                                 d.pos) && height == d.height && width == d.width && name == d.name && currentImage == d.currentImage
        }
        return false
    }

    override fun toString(): String {
        return this.javaClass.toString() + "(" + "name:" +
               name + ", " +
               "x:" + x + ", " +
               "y:" + y + ", " +
               "width:" + width + ", " +
               "height:" + height + ")"
    }

    fun setPos(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    var pos: DoubleArray
        get() = doubleArrayOf(x, y)
        set(pos) {
            x = pos[0]
            y = pos[1]
        }

    fun setSize(size: Double) {
        width = size
        height = size
    }

    protected fun setSwitchingDelay(switchingDelay: Double) {
        this.switchingDelay = switchingDelay
    }

    // --- Getter & Setter ------------------------------------------------------------------------


    fun setCurrentImage(filePath: String) {
        currentImage = loadPicture(filePath)
    }

    companion object {
        private var id_counter = 0
    }
}
