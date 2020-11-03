package de.hsh.alexander.level

import common.actor.Collectable
import common.actor.CollisionCheck.isInBounds
import common.actor.ControllableActor
import common.actor.Level
import common.actor.LevelElement
import common.config.WindowConfig
import common.util.Logger
import de.hsh.alexander.PacManGame
import de.hsh.alexander.actor.ResourcePaths
import de.hsh.alexander.actor.collectables.DataCoin
import de.hsh.alexander.actor.collectables.Invisible
import de.hsh.alexander.actor.level_elements.Condensator
import de.hsh.alexander.actor.level_elements.Fan
import de.hsh.alexander.actor.level_elements.SMD
import de.hsh.alexander.actor.player.PacMan
import de.hsh.alexander.actor.player.PacMan1
import de.hsh.alexander.actor.player.PacMan2
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import java.util.*

class PacManLevel(gameCanvas: Canvas) : Level(gameCanvas) {

    override fun createLevel(gameCanvas: Canvas) {
        fun createDataCoins(gameCanvas: Canvas) {
            var y = 0.0
            while (y < WindowConfig.window_height) {
                var x = 0.0
                while (x < WindowConfig.window_width) {
                    val d = DataCoin(x, y)
                    if (isInBounds(d, gameCanvas)) add(d)
                    x += 50
                }
                y += 50
            }
        }

        fun addSMDs(gameCanvas: Canvas, smd_offset: Int) {
            var y = 0.0
            while (y < WindowConfig.window_height) {
                var x = 0.0
                while (x < WindowConfig.window_width) {
                    val smd = SMD(x, y)
                    addLevelElement(gameCanvas, smd)
                    x += smd_offset
                }
                y += smd_offset
            }
        }

        fun fillPins(gameCanvas: Canvas) {
            fun addCondensatorsY(gameCanvas: Canvas, start: Int, end: Int, increment: Int, x: Int) {
                var y = start
                while (y < end) {
                    addLevelElement(gameCanvas, Condensator(x.toDouble(), y.toDouble()))
                    addLevelElement(gameCanvas, Condensator((x + increment).toDouble(), y.toDouble()))
                    y += increment
                }
            }

            fun addCondensatorsX(gameCanvas: Canvas, start: Int, end: Int, increment: Int, y: Int) {
                var x = start
                while (x < end) {
                    addLevelElement(gameCanvas, Condensator(x.toDouble(), y.toDouble()))
                    addLevelElement(gameCanvas, Condensator((x + increment).toDouble(), y.toDouble()))
                    x += increment
                }
            }

            addLevelElement(gameCanvas, Condensator(200.0, 600.0, 1))
            var i = 350.0
            while (i < 500) {
                addLevelElement(gameCanvas, Condensator(700.0, i, 0))
                i += 40
            }
            val end = 580
            val increment = 19
            addCondensatorsY(gameCanvas, 285, end, increment, 525)
            addCondensatorsX(gameCanvas, 545, 805, increment, 705)
            addLevelElement(gameCanvas, Condensator(525.0 + increment, (end + increment).toDouble()))
        }

        setBackgroundImage(ResourcePaths.Actor.LevelElements.Backgrounds.microChip,
                background_width,
                background_height)
        addPlayer(PacMan1(250.0, 150.0))
        addPlayer(PacMan2(550.0, 350.0))

        addLevelElement(Fan(200.0, 50.0))
        addLevelElement(Fan(200.0, 375.0, 5.0))
        fillPins(gameCanvas)
        addSMDs(gameCanvas, 200)

        add(Invisible(300.0, 300.0))

        createDataCoins(gameCanvas)
    }

    override fun resetAndThan(gameCanvas: Canvas, thanDo: (Canvas) -> Unit) =
            super.resetAndThan(gameCanvas) {
                gameCanvas.onMouseClicked = EventHandler { clickEvent: MouseEvent ->
                    add(DataCoin(clickEvent.x, clickEvent.y))
                }
                isGameFinished
            }

    val pacMan1Property: SimpleIntegerProperty
        get() = players
                .filterIsInstance<PacMan1>()
                .map { player: PacMan1 -> player.points  }
                .first()

    val pacMan2Property: SimpleIntegerProperty
        get() = players
                .filterIsInstance<PacMan2>()
                .map { player: PacMan2 -> player.points }
                .first()

    override val isGameFinished: Boolean
        get() = if (collectables.size == 0) {
            setChanged()
            this.notifyObservers(PacManGame.gameFinishedMessage)
            true
        } else false

    override infix fun keyboardInput(keyEvent: KeyEvent) =
            players.forEach { pacMan: ControllableActor ->
                pacMan.move(keyEvent)
            }

    override fun update(o: Observable, arg: Any) {
        fun coinCollected(c: Collectable) {
            if (c is DataCoin) {
                val a = c.collector
                if (a is PacMan) a.addPoint()
            }
            collected(c)
        }

        if (o is Collectable) {
            if (arg is String && arg == Collectable.collected) coinCollected(o)
            if (o is Invisible) Logger.log("PacManLevel : invisible collected : $o")
        } else {
            Logger.log(this.javaClass.toString() + ": unknown observable=" + o + " , arg=" + arg)
        }
    }

    override fun add(c: Collectable): Boolean =
            if (!collidesWithPlayer(c) && !collidesWithLevelElement(c) && !collidesWithCollectable(c)) super.add(c)
            else false

    override fun addLevelElement(levelElement: LevelElement): Boolean =
            if (!collidesWithPlayer(levelElement) && !collidesWithLevelElement(levelElement)) super.addLevelElement(levelElement)
            else false

    companion object {
        const val background_width = 950
        const val background_height = 950
    }
}
