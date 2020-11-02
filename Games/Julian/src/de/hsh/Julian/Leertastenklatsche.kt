package de.hsh.Julian

import common.config.WindowConfig
import common.util.Logger
import common.util.PlaySound.playAndResetSound
import common.util.PlaySound.playSound
import de.hsh.dennis.model.Spawn.SpawnTimer
import javafx.application.Platform
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import java.util.*
import kotlin.math.sqrt

class Leertastenklatsche(observer: Observer) : Observable(), Observer {

    var horrorWasActivated = false
    private var score = 0
    private var leben = 3
    private var gamedone = false
    private var thedude: TheDude = TheDude(
            WindowConfig.window_width / 2.0 - 75.0,
            WindowConfig.window_height * 0.73)
    private val enemyList = ArrayList<Enemy>()
    private var spawntimer = 0.0

    init { this.addObserver(observer) }

    fun render(gc: Canvas) {
        if (gamedone) return

        spawntimer = if (score >= 70) 0.2 else 2.0 - score / 50

        if (timer.elabsedTime() > spawntimer) {
            timer.resetTimer()
            val e = Enemy(this)
            thedude.addCollidingActor(e)
            enemyList.add(e)
        }
        enemyList.forEach { enemy ->
            val enemyX = when(enemy.x > WindowConfig.window_width / 2) {
                true -> enemy.x - 1f - score / sqrt(score + 1f)
                false -> enemy.x + 1f + score / sqrt(score + 1f)
            }
            enemy.setPos(enemyX, enemy.y)
        }
        Platform.runLater {
            renderScore(gc)
            enemyList.forEach { enemy: Enemy -> enemy.draw(gc) }
            thedude.draw(gc)
        }
    }

    private fun renderScore(canvas: Canvas) {
        if (score >= 70) {
            if (!horrorWasActivated) {
                //PlaySound.playSound( "src\\de\\de.hsh\\Julian\\wav\\horror.wav" );
                playSound(pathToSound + "Kalinka.mp3")
                leben = 70
            }
            horrorWasActivated = true
        }
        with(canvas.graphicsContext2D) {
            val pointsText: String =
                    if (score < 70) "LEERTASTENKLATSCHE\nGegner abgewehrt: $score\nVerbleibende Leben: $leben"
                    else "HORRORMODUS SCORE: $score\nKLATSCH KLATSCH KLATSCH Leben: $leben"
            fillText(pointsText, 360.0, 36.0)
            strokeText(pointsText, 360.0, 36.0)
        }
    }

    fun parseInput(code: KeyCode) {
        when (code) {
            KeyCode.LEFT -> if (!thedude.isTurnedLeft) {
                thedude.swapImage()
                thedude.isTurnedLeft = true
            }
            KeyCode.RIGHT -> if (thedude.isTurnedLeft) {
                thedude.swapImage()
                thedude.isTurnedLeft = false
            }
            KeyCode.SPACE -> if (!horrorWasActivated) playSound(pathToSound + "cat.wav")
        }
    }

    override fun update(o: Observable, arg: Any) {
        fun adjustPositions(enemyXPosition: Double) {
            if (enemyXPosition <= thedude.pos[0])
                if (thedude.isTurnedLeft) {
                    score++
                    if (!horrorWasActivated) playAndResetSound(pathToSound + "collision.wav")
                } else {
                    leben--
                    if (!horrorWasActivated) playAndResetSound(pathToSound + "hit.wav")
                    gameOver()
                }
            else if (!thedude.isTurnedLeft)
                score++
                if (!horrorWasActivated) playAndResetSound(pathToSound + "collision.wav")
            else
                leben--
                if (!horrorWasActivated) playAndResetSound(pathToSound + "hit.wav")
                gameOver()
        }

        if (gamedone) {
            Logger.log(this.javaClass.toString() + ": Game should be done, but event is still fired. : " + o + ", " + arg)
            return
        }
        if (o !is Enemy) return

        enemyList.filter { enemy -> enemy.id == o.id }
                .forEach { enemy ->
                    o.deleteObservers()
                    enemy.deleteObservers()
                    thedude.removeCollisionActor(enemy)
                    enemyList.remove(enemy)
                    adjustPositions(enemy.pos[0])
                }
    }

    private fun gameOver() {
        if (leben <= 0 && !gamedone) {
            gamedone = true
            playSound(pathToSound + "noo.wav")
            enemyList.forEach { e ->
                e.deleteObservers()
            }
            enemyList.removeAll(enemyList)
            setChanged()
            notifyObservers("gameover")
        }
    }

    companion object {
        const val location = "/de/hsh/Julian/"
        const val pathToSound = location + "wav/"
        private val timer = SpawnTimer()
    }
}
