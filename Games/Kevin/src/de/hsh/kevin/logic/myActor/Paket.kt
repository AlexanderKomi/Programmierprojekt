package de.hsh.kevin.logic.myActor

import common.actor.Actor

/**
 * Erstellt ein Paket
 * @author Kevin
 */
class Paket(pictureFileName: List<String>,
            x: Double,
            y: Double,
            paketTyp: enmPaketTyp) :
        Actor(pictureFileName, x, y, 15) {

    override var speed = 3.0

    /**
     * Liefert den Typen des Pakets
     * @return Typ des Pakets
     */
    var paketTyp: enmPaketTyp = paketTyp
        private set

    /**
     * Bewegt das Paket
     */
    fun move() = setPos(x, y + speed)

}
