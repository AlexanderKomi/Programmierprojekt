package de.hsh.amir.logic

import common.actor.Actor

class Gegner internal constructor(x: Double) : Actor(enemyPicture, x, START_Y) {

    fun move() {
        setPos(x, y + GEGNER_SPEED)
    }

    fun moveDiagonal() {
        setPos(x + GEGNER_SPEED / 4, y + GEGNER_SPEED)
    }

    companion object {
        private const val START_Y = 10.0
        private var GEGNER_SPEED = 3.0
        private const val enemyPicture = "/de/hsh/amir/resources/enemyFigur.png"

        /**
         * Setzt Gegnergeschwindigkeit GLOBAL!!! auf den übergebenen Wert.
         * @param speed
         */
        @JvmStatic
        fun setGegnerSpeed(speed: Double) {
            GEGNER_SPEED = speed
        }
    }
}
