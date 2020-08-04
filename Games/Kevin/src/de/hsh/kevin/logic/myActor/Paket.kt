package de.hsh.kevin.logic.myActor

import common.actor.Actor

/**
 * Erstellt ein Paket
 * @author Kevin
 */
class Paket : Actor {
    override var speed = 3.0

    /**
     * Liefert den Typen des Pakets
     * @return Typ des Pakets
     */
    var paketTyp: enmPaketTyp?
        private set

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param paketTyp
     */
    constructor(pictureFileName: String?, paketTyp: enmPaketTyp?) : this(pictureFileName,
                                                                         startX,
                                                                         startY,
                                                                         paketTyp) {
    }

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param x
     * @param y
     * @param paketTyp
     */
    constructor(pictureFileName: String?, x: Double, y: Double, paketTyp: enmPaketTyp?) : super(
            pictureFileName!!,
            x,
            y) {
        this.paketTyp = paketTyp
    }

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param x
     * @param y
     * @param paketTyp
     */
    constructor(pictureFileName: List<String?>?,
                x: Double,
                y: Double,
                paketTyp: enmPaketTyp) : super(pictureFileName as List<String>, x, y, changePictureDelay) {
        this.paketTyp = paketTyp
    }

    /**
     * Bewegt das Paket
     */
    fun move() {
        setPos(x, y + speed)
    }

    override fun toString(): String {
        return super.toString()
    }

    companion object {
        private const val startX = 100.0
        private const val startY = 10.0
        private const val changePictureDelay = 15
    }
}
