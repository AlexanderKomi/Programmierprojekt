package de.hsh.amir.logic

import common.sound.PlaySound.playSound
import de.hsh.kevin.logic.Score
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent

class AmirGame(private val canvas: Canvas, private val points: Score) {

    private var spielfigur: Spielfigur = Spielfigur(canvas.width / 2, canvas.height - 100)
    private var gegnerManager: GegnerManager = GegnerManager()
    private var timer = 0

    fun render() {
        val temp = gegnerManager.gegnerListe
        temp.removeAll(temp.filterNot { it.isInBounds(canvas.width, canvas.height + it.height) })
        gegnerManager.gegnerListe = temp

        timer++
        if (timer == 120) {
            gegnerManager.erstelleGegner(4)
            timer = 0
        }

        val toRemove = gegnerManager.gegnerListe
                .filter { gegner -> spielfigur.doesCollide(gegner) }
        toRemove.forEach { gegner ->
            points.increase()
            playSound()
            gegnerManager.gegnerListe.remove(gegner)
        }
        gegnerManager.move(canvas)
        canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
        spielfigur.draw(canvas)
        gegnerManager.draw(canvas)
    }

    fun onKeyPressed(event: KeyEvent) = spielfigur.move(event)

    companion object {
        /**
         * Spielt einen Sound ab.
         */
        private fun playSound() {
            playSound("/de/hsh/amir/resources/clickSound.mp3", true)
        }
    }

}
