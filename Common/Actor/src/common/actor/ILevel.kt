package common.actor

import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent

interface ILevel {
    /**
     * Create all your level elements
     * @param gameCanvas
     */
    fun createLevel(gameCanvas: Canvas?)

    /**
     * What happens, when a key is pressed?
     */
    fun keyboardInput(keyEvent: KeyEvent?)

    /**
     * Draw what you would like to :)
     */
    fun render(canvas: Canvas?, fps: Int)
}
