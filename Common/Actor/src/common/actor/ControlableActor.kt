package common.actor

import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent

abstract class ControlableActor : Actor {
    protected constructor(pictureFileName: String,
                          x: Double,
                          y: Double,
                          keymap: Map<String, Direction>) : super(pictureFileName, x, y) {
        this.movement.keymap = (keymap)
    }

    protected constructor(pictureFileName: Array<String>,
                          x: Double,
                          y: Double,
                          keymap: Map<String, Direction>,
                          delay: Int) : super(x, y, delay, pictureFileName) {
        this.movement.keymap = (keymap)
    }

    /**
     * Checks Key Released and Pressed Events.
     */
    fun move(keyEvent: KeyEvent) {
        val keyName = keyEvent.code.getName()
        when (keyEvent.eventType.name) {
            "KEY_PRESSED" -> {
                movement.onKeyPressed(keyName)
            }
            "KEY_RELEASED" -> {
                movement.onKeyReleased(keyName)
            }
        }
    }

    override fun draw(canvas: Canvas) {
        val directedSpeed = movement.holdDown.keys
                .filter { direction: Direction -> movement.holdDown[direction]!! }
                .map { direction: Direction -> calculateDirectedSpeed(direction, movement.velocity) }
                .firstOrNull()
        if (directedSpeed != null) {
            super.draw(canvas, directedSpeed[0], directedSpeed[1])
        } else {
            super.draw(canvas, 0.0, 0.0)
        }
    }

    protected open fun calculateDirectedSpeed(direction: Direction, movement_speed: Double): DoubleArray {
        val xyTuple = DoubleArray(2)
        if (direction === Direction.Down) {
            xyTuple[1] += movement_speed
        }
        if (direction === Direction.Up) {
            xyTuple[1] += -movement_speed
        }
        if (direction === Direction.Left) {
            xyTuple[0] += -movement_speed
        }
        if (direction === Direction.Right) {
            xyTuple[0] += movement_speed
        }
        return xyTuple
    }
}
