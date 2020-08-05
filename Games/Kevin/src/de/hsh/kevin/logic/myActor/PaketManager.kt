package de.hsh.kevin.logic.myActor

import de.hsh.kevin.logic.Leben
import de.hsh.kevin.logic.Score
import de.hsh.kevin.logic.Sound
import de.hsh.kevin.logic.enmSounds
import de.hsh.kevin.logic.myActor.PaketFactory.getBadPaket
import de.hsh.kevin.logic.myActor.PaketFactory.getGoodPaket
import javafx.scene.canvas.Canvas
import java.util.*

/**
 * Verwaltet Pakete
 *
 * @author Kevin
 */
class PaketManager
/**
 * Erstellt einen PaketManager
 *
 * @param width  des Spielbereichs
 * @param height des Spielbereichs
 * @param score  Object des Spiels
 * @param leben  Object des Spiels
 */(private val width: Double, private val height: Double, private val score: Score, private val leben: Leben) {
    val pakete = mutableListOf<Paket>()

    /**
     * Erstellt Anzahl von Paketen mit Charnce Good:Bad Pakete
     *
     * @param chance zum erstellen eines GoodPakets
     * @param anzahl der zu erstellenden Pakete
     */
    fun createNewPaket(chance: Double, anzahl: Int) {
        for (i in 0 until anzahl) {
            val p: Paket = if (Math.random() < chance) {
                getGoodPaket()
            } else {
                getBadPaket()
            }
            setOnFreeLocation(p)
            pakete.add(p)
        }
    }

    /**
     * Bewegt alle Pakete
     */
    fun move() {
        val toRemove = mutableListOf<Paket>()
        pakete.forEach { paket: Paket ->
            if (paket.pos[1] > height) {
                if (paket.paketTyp === enmPaketTyp.good) {
                    score.increase()
                } else {
                    Sound.playSound(enmSounds.badPaketIgnored)
                    leben.decrease()
                }
                toRemove.add(paket)
            } else {
                paket.move()
            }
        }
        for (paket in toRemove) {
            pakete.remove(paket)
        }
    }

    /**
     * Zeichenet alle Pakete aufs Canvas
     */
    fun draw(canvas: Canvas?) {
        for (p in pakete) {
            p.draw(canvas!!)
        }
    }

    /**
     * Sucht freie Position für übergebenes Paket
     */
    private fun setOnFreeLocation(paket: Paket) {
        var randX: Double
        var free = false
        var isCollided: Boolean
        do {
            // sucht randX sodass Paket ganz im Bild ist
            do {
                randX = Math.random() * width
            } while (randX + paket.width > width)
            paket.setPos(randX, -(paket.height + 1))

            // check ob Platz belegt ist
            isCollided = false
            for (p in pakete) {
                if (p.doesCollide(paket)) {
                    isCollided = true
                    break
                }
            }
            if (!isCollided) {
                free = true
            }
        } while (!free)
    }

    /**
     * Löscht das übergebene Paket
     *
     * @param p zu löschende Paket
     */
    fun remove(p: Paket?) {
        pakete.remove(p)
    }

}
