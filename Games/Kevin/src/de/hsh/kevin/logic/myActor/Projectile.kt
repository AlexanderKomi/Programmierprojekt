package de.hsh.kevin.logic.myActor

import common.actor.Actor
import de.hsh.kevin.logic.Config

/**
 * Erstellt Projektil an Position (x,y)
 * @param pictureFileName Pfad zum Bild
 * @param x - Wert des Projektils
 * @param y - Wert des Projektils
 */
class Projectile(x: Double = 0.0, y: Double = 0.0, pictureFileName: String = Config.resLocation + "kugel.png")
    : Actor(pictureFileName, x, y) {

    /**
     * Bewegt das Projektil um gegebenes Speed
     */
    fun move() {
        val speed = 4.0
        setPos(x, y - speed)
    }
}
