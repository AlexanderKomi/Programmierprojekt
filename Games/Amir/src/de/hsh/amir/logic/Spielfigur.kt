package de.hsh.amir.logic

import common.actor.ControllableActor
import common.actor.Direction
import javafx.scene.input.KeyCode

class Spielfigur internal constructor(x: Double, y: Double)
    : ControllableActor(
        spielFigurBildpfad,
        x,
        y,
        mapOf(KeyCode.RIGHT to Direction.Right,
              KeyCode.LEFT to Direction.Left)) {

    override fun calculateDirectedSpeed(direction: Direction, movementSpeed: Double): DoubleArray {
        val xyTuple = DoubleArray(2)
        if (direction === Direction.Left) {
            xyTuple[0] = -movementSpeed
            xyTuple[1] = 0.0
        } else if (direction === Direction.Right) {
            xyTuple[0] = movementSpeed
            xyTuple[1] = 0.0
        }
        return xyTuple
    }

    companion object {
        private const val defaultSpeed = 10
        private const val spielFigurBildpfad = "/de/hsh/amir/resources/playerFigur.png"
    }

    init {
        speed = defaultSpeed.toDouble()
    }
}
