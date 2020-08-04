package de.hsh.amir.logic

import de.hsh.amir.controller.AmirsMainMenuController
import javafx.scene.canvas.Canvas
import java.util.*

class GegnerManager internal constructor() : Observer {
    var gegnerListe = ArrayList<Gegner>()

    /**
     * Zeichnet jede Gegnerfigur auf die Canvas.
     *
     * @param canvas
     */
    fun draw(canvas: Canvas) = gegnerListe.forEach { g ->
        g.draw(canvas)
    }

    /**
     * Erstellt einen Gegner und setzt ihn an eine zufällig x-Position.
     */
    private fun erstelleGegner() {
        val random = Random()
        var gegner: Gegner? = null
        var zufallsZahl: Int
        var collides = true
        while (collides) {
            collides = false
            //set Gegner at a random x-position
            zufallsZahl = RAND_ABSCHNITT + random.nextInt(SPIELFELD_BREITE - 2 * RAND_ABSCHNITT)
            gegner = Gegner(zufallsZahl.toDouble())
            gegner.addObserver(this)
            gegnerListe
                    .filter { g -> gegner.doesCollide(g) }
                    .forEach { _ ->
                        collides = true
                        gegner.deleteObservers()
                    }
        }

        //Packe maximal 20 Gegner in der Liste rein!
        if (gegnerListe.size <= 20 && gegner != null) {
            gegnerListe.add(gegner)
        } else {
            gegner?.deleteObservers()
        }
    }

    /**
     * Erstellt minimal einen und maximal fünf Gegner UNABHÄNGIG!!! vom
     * übergebenen Parameter
     * CHANGE THIS ONLY IF YOU WANT TO CHANGE THE NUMBER OF GEGNER-OBJECTS!!!
     * @param anzahlGegner unabhöngig von diesem Parameter werden "max" Gegner erstellt.
     */
    fun erstelleGegner(anzahlGegner: Int) = (0 until anzahlGegner.minMax(0, 5)).forEach { _ -> erstelleGegner() }

    private fun <T : Comparable<T>> T.minMax(min: T, max: T): T = when {
        this < min -> min
        this < max -> this
        else       -> max
    }

    /**
     * Bewegt Gegner jedes mal an einer neuen Position.
     * Bei Level 2 bewegen sich die Figuren schneller.
     * Bei Level 3 bewegen sich die Figuren schneller und teilweise diagonal.
     *
     * @param canvas
     */
    fun move(canvas: Canvas) {
        val zufallsZahl = RAND_ABSCHNITT + Random().nextInt(SPIELFELD_BREITE - 2 * RAND_ABSCHNITT).toDouble()
        when (AmirsMainMenuController.LEVEL_NUMBER) {
            1 -> moveEnemy(canvas, zufallsZahl)
            2 -> moveEnemy(canvas, zufallsZahl)
            3 -> gegnerListe.forEachIndexed { index, gegner ->
                if (index % 2 == 0) {
                    gegner.move()
                } else {
                    gegner.moveDiagonal()
                }
                if (gegner.y >= canvas.height) {
                    gegner.y = -30.0
                    gegner.x = zufallsZahl
                }
            }
        }
    }

    private fun moveEnemy(canvas: Canvas, zufallsZahl: Double) {
        gegnerListe.forEach { gegner ->
            gegner.move()
            if (gegner.y >= canvas.height) {
                gegner.y = -30.0
                gegner.x = zufallsZahl
            }
        }
    }

    override fun update(o: Observable, arg: Any) {}

    companion object {
        private const val SPIELFELD_BREITE = 1200
        private const val RAND_ABSCHNITT = 200
    }
}
