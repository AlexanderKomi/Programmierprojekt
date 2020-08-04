package de.hsh.Julian

import common.config.WindowConfig
import common.util.Logger
import common.util.PlaySound.playAndResetSound
import common.util.PlaySound.playSound
import de.hsh.Julian.Enemy.Companion.createEnemy
import de.hsh.dennis.model.NpcLogic.SpawnTimer
import javafx.application.Platform
import javafx.scene.canvas.Canvas
import java.util.*
import java.util.function.Consumer
import kotlin.math.sqrt

class Leertastenklatsche : Observable(), Observer {
    private var score = 0
    private var leben = 3
    private var gamedone = false
    var horrorWasActivated = false
    private var thedude: TheDude = TheDude(
            (WindowConfig.window_width / 2.0 - 75.0),
            WindowConfig.window_height * 0.73)
    private val enemyList = ArrayList<Enemy>()
    private var spawntimer = 0.0

    fun render(gc: Canvas) {
        if (gamedone) {
            return
        }
        spawntimer = if (score >= 70) 0.2 else 2.0 - score / 50
        if (timer.elabsedTime() > spawntimer) {
            timer.resetTimer()
            val e = createEnemy()
            e.addObserver(this)
            thedude.addCollidingActor(e)
            enemyList.add(e)
        }
        enemyList.forEach { enemy ->
            when (enemy.x > WindowConfig.window_width / 2) {
                true  -> enemy.setPos(
                        enemy.x - 1.0f - score / sqrt(score + 1.0f),
                        enemy.y)
                false -> enemy.setPos(
                        enemy.x + 1f + score / sqrt(score + 1.0f),
                        enemy.y)
            }
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
                    if (score < 70) {
                        "LEERTASTENKLATSCHE\nGegner abgewehrt: $score\nVerbleibende Leben: $leben"
                    } else {
                        "HORRORMODUS SCORE: $score\nKLATSCH KLATSCH KLATSCH Leben: $leben"
                    }
            fillText(pointsText, 360.0, 36.0)
            strokeText(pointsText, 360.0, 36.0)
        }
    }

    fun parseInput(code: String) {
        when (code) {
            "LEFT" -> {
                if (!thedude.isTurnedleft) {
                    thedude.swapImage()
                    thedude.isTurnedleft = true
                }
            }
            "RIGHT" -> if (thedude.isTurnedleft) {
                thedude.swapImage()
                thedude.isTurnedleft = false
            }
            "SPACE" -> if (!horrorWasActivated) playSound(pathToSound + "cat.wav")
        }
    }

    override fun update(o: Observable, arg: Any) {
        if (gamedone) {
            Logger.log(this.javaClass.toString() + ": Game should be done, but event is still fired. : " + o + ", " + arg)
            return
        }
        if (o is Enemy) {
            val e: Enemy = o
            enemyList.filter { enemy -> enemy.id == e.id }
                .forEach { enemy ->
                    e.deleteObservers()
                    enemy.deleteObservers()
                    thedude.removeCollisionActor(enemy)
                    enemyList.remove(enemy)

                    //Logger.log( this.getClass() + ": Found enemy with same id" );
                    if (enemy.pos[0] <= thedude.pos[0]) {
                        if (thedude.isTurnedleft) {
                            score++
                            if (!horrorWasActivated) playAndResetSound(pathToSound + "collision.wav")
                        } else {
                            leben--
                            if (!horrorWasActivated) playAndResetSound(pathToSound + "hit.wav")
                            gameOver()
                        }
                    } else if (enemy.pos[0] > thedude.pos[0]) {
                        if (!thedude.isTurnedleft) {
                            score++
                            if (!horrorWasActivated) playAndResetSound(pathToSound + "collision.wav")
                        } else {
                            leben--
                            if (!horrorWasActivated) playAndResetSound(pathToSound + "hit.wav")
                            gameOver()
                        }
                    }
                    return
                }
        }
    }

    private fun gameOver() {
        if (leben <= 0 && !gamedone) {
            gamedone = true
            playSound(pathToSound + "noo.wav")
            for (e in enemyList) {
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
