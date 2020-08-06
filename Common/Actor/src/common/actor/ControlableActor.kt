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

    protected constructor(pictureFileNames: Array<String>,
                          x: Double,
                          y: Double,
                          keymap: Map<String, Direction>,
                          delay: Int) : super(pictureFileNames.toList(), x = x, y = y, delay = delay) {
        this.movement.keymap = (keymap)
    }

    private var facingDirection: Direction = Direction.Left

    /**
     * Checks Key Released and Pressed Events.
     */
    fun move(keyEvent: KeyEvent) {
        val keyName = keyEvent.code.getName()
        when (keyEvent.eventType.name) {
            "KEY_PRESSED" -> { movement.onKeyPressed(keyName) }
            "KEY_RELEASED" -> { movement.onKeyReleased(keyName) }
            else -> {}
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
            super.draw(canvas)
        }
    }

    protected open fun calculateDirectedSpeed(direction: Direction, movementSpeed: Double): DoubleArray {
        val xyTuple = DoubleArray(2)
        when (direction) {
            Direction.Right -> xyTuple[0] = movementSpeed
            Direction.Left -> xyTuple[0] = -movementSpeed
            Direction.Down -> xyTuple[1] = movementSpeed
            Direction.Up -> xyTuple[1] = -movementSpeed
            Direction.Non -> {}
        }
        return xyTuple
    }

    protected fun changeFacingDirection(directionChange: DoubleArray) {
        mirrorImageOnVerticalAxis(horizontalDirectionChange = directionChange[0])
        mirrorImageOnHorizontalAxis(verticalDirectionChange = directionChange[1])
    }

    private fun mirrorImageOnVerticalAxis(horizontalDirectionChange: Double, invertImage: Boolean = true) {
        if (horizontalDirectionChange > 0 && this.facingDirection != Direction.Right) {
            this.facingDirection = Direction.Right
            if (invertImage && this.scaleWidth > 0) {
                this.scaleImageWidth(-1.0)
            }
        } else if (horizontalDirectionChange < 0 && this.facingDirection != Direction.Left) {
            this.facingDirection = Direction.Left
            if (invertImage && this.scaleWidth < 0) {
                this.scaleImageWidth(-1.0)
            }
        }
    }

    private fun mirrorImageOnHorizontalAxis(verticalDirectionChange: Double, invertImage: Boolean = false) {
        if (verticalDirectionChange > 0 && this.facingDirection != Direction.Down) {
            this.facingDirection = Direction.Down
            if (invertImage && this.scaleHeight < 0) {
                this.scaleImageHeight(-1.0)
            }
        } else if (verticalDirectionChange < 0 && this.facingDirection != Direction.Up) {
            this.facingDirection = Direction.Up
            if (invertImage && this.scaleHeight < 0) {
                this.scaleImageHeight(-1.0)
            }
        }
    }
}
