package de.hsh.amir.logic

import common.actor.ControlableActor
import common.actor.Direction
import java.util.*

class Spielfigur internal constructor() : ControlableActor(spielFigurBildpfad,
                                                           startX,
                                                           startY,
                                                           playerKeyMap) {
    override fun calculateDirectedSpeed(direction: Direction,
                                        movementSpeed: Double): DoubleArray {
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
        private const val startX = 250.0
        private const val startY = 750.0
        private const val defaultSpeed = 10
        private const val spielFigurBildpfad = "/de/hsh/amir/resources/playerFigur.png"
        private val playerKeyMap = createPlayerKeymap()
        private fun createPlayerKeymap(): HashMap<String, Direction> {
            val playerKeyMap = HashMap<String, Direction>()
            playerKeyMap["Right"] = Direction.Right
            playerKeyMap["Left"] = Direction.Left
            return playerKeyMap
        }
    }

    init {
        speed = defaultSpeed.toDouble()
    }
}
