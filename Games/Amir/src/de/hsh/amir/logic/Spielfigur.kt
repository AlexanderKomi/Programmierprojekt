package de.hsh.amir.logic

import common.actor.ControlableActor
import common.actor.Direction
import java.util.*

class Spielfigur internal constructor(x: Double, y: Double)
    : ControlableActor(
        spielFigurBildpfad,
        x,
        y,
        mapOf("Right" to Direction.Right,
              "Left" to Direction.Left)) {

    override fun calculateDirectedSpeed(direction: Direction, movement_speed: Double): DoubleArray {
        val xyTuple = DoubleArray(2)
        if (direction === Direction.Left) {
            xyTuple[0] = -movement_speed
            xyTuple[1] = 0.0
        } else if (direction === Direction.Right) {
            xyTuple[0] = movement_speed
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
