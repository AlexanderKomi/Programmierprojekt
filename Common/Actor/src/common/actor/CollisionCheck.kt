package common.actor

import javafx.scene.canvas.Canvas

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
object CollisionCheck {
    /**
     * Just a Wrapper.
     */

    fun isInBounds(a: Drawable, canvas: Canvas): Boolean {
        val temp = isInBounds(
                a.x,
                a.y,
                a.width,
                a.height,
                canvas.width,
                canvas.height,
                0.0,
                0.0)
        return temp[0] && temp[1]
    }

    fun isInBounds(a: Drawable,
                   canvas_widht: Double,
                   canvas_height: Double): Boolean {
        val temp = isInBounds(
                a.x,
                a.y,
                a.width,
                a.height,
                canvas_widht,
                canvas_height,
                0.0,
                0.0)
        return temp[0] && temp[1]
    }

    /**
     * Calculates if a rectangle with a tuple of coordinates (x, y) and given width and height,
     * is positioned in a rectangle canvas_width and canvas_height.
     *
     *
     * The coordinates (new_x, new_y) are an offset to the (x,y) Tuple, not new coordinates!
     * This implementation is in particularly useful to calculate if a new position or the next position of the rectangle is
     * in the greater rectangle.
     *
     * @return an boolean Array with index 0 for x coordinate and
     * * index 1 for y coordinate. When true is written at the corresponding index, it means, that the corresponding
     * coordinate
     * * is in bounds.
     * @author Alexander Komischke
     * @author Kevin Jeske
     */
    @JvmStatic
    fun isInBounds(x: Double,
                   y: Double,
                   width: Double,
                   height: Double,
                   canvas_width: Double,
                   canvas_height: Double,
                   new_x: Double,
                   new_y: Double): BooleanArray = booleanArrayOf(
            false, false
    ).also { temp ->
        if (width > 0 &&
                x + new_x >= 0 &&
                x + new_x + width <= canvas_width) temp[0] = true
        else if (width < 0 && x + new_x in 0.0..canvas_width) temp[0] = true

        if (height > 0 &&
                y + new_y >= 0 &&
                y + new_y + height <= canvas_height) temp[1] = true
        else if (y + new_y in 0.0..canvas_height) temp[1] = true
    }

    fun doesCollide(a: Drawable, b: Drawable): Boolean =
            checkUpperLeftCorner(a, b) ||
                    checkUpperRightCorner(a, b) ||
                    checkLowerRightCorner(a, b) ||
                    checkLowerLeftCorner(a, b)

    fun doesCollide(a: Drawable, mouse_x: Double, mouse_y: Double): Boolean =
            doesCollide(a.x, a.y, a.width, a.height, mouse_x, mouse_y, 0.0, 0.0)

    private inline fun doesCollide(a_x: Double,
                                   a_y: Double,
                                   a_width: Double,
                                   a_height: Double,
                                   b_x: Double,
                                   b_y: Double,
                                   b_width: Double,
                                   b_height: Double): Boolean =
            checkUpperLeftCorner( a_x, a_y, a_width, a_height, b_x, b_y) ||
            checkUpperRightCorner(a_x, a_y, a_width, a_height, b_x, b_y, b_width) ||
            checkLowerRightCorner(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height) ||
            checkLowerLeftCorner( a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height)

    /**
     * Obere Linke Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private inline fun checkUpperLeftCorner(a: Drawable, b: Drawable): Boolean =
            checkUpperLeftCorner(a.x, a.y, a.width, a.height, b.x, b.y)

    private inline fun checkUpperLeftCorner(a_x: Double,
                                            a_y: Double,
                                            a_width: Double,
                                            a_height: Double,
                                            b_x: Double,
                                            b_y: Double): Boolean {
        return if (a_x <= b_x && a_x + a_width >= b_x) {
            a_y <= b_y && a_y + a_height >= b_y
        } else false
    }

    /**
     * Obere Rechte Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private fun checkUpperRightCorner(a: Drawable, b: Drawable): Boolean =
            checkUpperRightCorner(a.x, a.y, a.width, a.height, b.x, b.y, b.width)

    private inline fun checkUpperRightCorner(a_x: Double,
                                             a_y: Double,
                                             a_width: Double,
                                             a_height: Double,
                                             b_x: Double,
                                             b_y: Double,
                                             b_width: Double): Boolean =
            if (a_x <= b_x + b_width && a_x + a_width >= b_x + b_width) {
                a_y <= b_y && a_y + a_height >= b_y
            } else false

    /**
     * Untere Rechte Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private fun checkLowerRightCorner(a: Drawable, b: Drawable): Boolean =
            checkLowerRightCorner(a.x, a.y, a.width, a.height, b.x, b.y, b.width, b.height)

    private inline fun checkLowerRightCorner(a_x: Double,
                                             a_y: Double,
                                             a_width: Double,
                                             a_height: Double,
                                             b_x: Double,
                                             b_y: Double,
                                             b_width: Double,
                                             b_height: Double): Boolean =
            if (a_x <= b_x + b_width && a_x + a_width >= b_x + b_width) {
                a_y <= b_y + b_height && a_y + a_height >= b_y + b_height
            } else false

    /**
     * Untere Linke Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private fun checkLowerLeftCorner(a: Drawable, b: Drawable): Boolean =
            checkLowerLeftCorner(a.x, a.y, a.width, a.height, b.x, b.y, b.width, b.height)

    private inline fun checkLowerLeftCorner(a_x: Double,
                                            a_y: Double,
                                            a_width: Double,
                                            a_height: Double,
                                            b_x: Double,
                                            b_y: Double,
                                            b_width: Double,
                                            b_height: Double): Boolean =
            if (a_x <= b_x && a_x + a_width >= b_x)
                a_y <= b_y + b_height && a_y + a_height >= b_y + b_height
            else false
}
