package de.hsh.alexander.level

import common.actor.Collectable
import common.actor.CollisionCheck.isInBounds
import common.actor.ControlableActor
import common.actor.Level
import common.actor.LevelElement
import common.config.WindowConfig
import common.util.Logger
import de.hsh.alexander.PacManGame
import de.hsh.alexander.actor.collectables.DataCoin
import de.hsh.alexander.actor.collectables.Invisible
import de.hsh.alexander.actor.level_elements.Condensator
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
import java.util.function.Consumer

abstract class PacManLevel internal constructor(gameCanvas: Canvas?) : Level(gameCanvas) {
    override fun reset(gameCanvas: Canvas?) {
        super.reset(gameCanvas)
        gameCanvas!!.onMouseClicked = EventHandler { clickEvent: MouseEvent ->
            onMouseClick(
                    clickEvent)
        }
        //Logger.log( this.getClass() + ": Resettet Level" );
        isGameFinished
    }

    private fun onMouseClick(clickEvent: MouseEvent) {
        val x = clickEvent.x
        val y = clickEvent.y
        //Logger.log( this.getClass() + ": Clicked at : (" + x + ", " + y + ")" );
        addCollectable(
                DataCoin(x, y)
                      )
    }

    val pacMan1Property: SimpleIntegerProperty
        get() = getPlayers().stream()
                .filter { player: ControlableActor? -> player is PacMan1 }
                .map { player: ControlableActor? -> player as PacMan1? }
                .map { obj: PacMan1? -> obj?.points!! }
                .findFirst()
                .get()

    val pacMan2Property: SimpleIntegerProperty
        get() = getPlayers().stream()
                .filter { player: ControlableActor? -> player is PacMan2 }
                .map { player: ControlableActor? -> player as PacMan2? }
                .map { obj: PacMan2? -> obj?.points }.findFirst().get()

    override fun keyboardInput(keyEvent: KeyEvent?) {
        getPlayers().forEach(Consumer { pacMan: ControlableActor ->
            pacMan.move(keyEvent)
        })
    }

    override fun update(o: Observable, arg: Any) {
        if (o is Collectable) {
            if (arg is String) {
                if (arg == Collectable.collected) {
                    coinCollected(o)
                }
            }
            if (o is Invisible) {
                Logger.log("PacManLevel : invisible collected : $o")
            }
        } else {
            Logger.log(this.javaClass.toString() + ": unknown observable=" + o + " , arg=" + arg)
        }
    }

    private fun coinCollected(c: Collectable) {
        if (c is DataCoin) {
            val a = c.collector
            if (a is PacMan) {
                a.addPoint()
            }
        }
        collected(c)
    }

    override val isGameFinished: Boolean
        get() {
            if (collectables.size == 0) {
                setChanged()
                this.notifyObservers(PacManGame.gameFinishedMessage)
                return true
            }
            return false
        }

    fun createDataCoins(gameCanvas: Canvas?) {
        var y = 0
        while (y < WindowConfig.window_height) {
            var x = 0
            while (x < WindowConfig.window_width) {
                val d = DataCoin(x.toDouble(), y.toDouble())
                if (isInBounds(d, gameCanvas!!)) {
                    addCollectable(d)
                }
                x += 50
            }
            y += 50
        }
    }

    fun addEasterEgg(x: Int, y: Int) = addCollectable(Invisible(x.toDouble(), y.toDouble()))

    fun addPlayers(pacMan1_x: Int, pacMan1_y: Int, pacMan2_x: Int, pacMan2_y: Int) {
        addPlayer(PacMan1(pacMan1_x.toDouble(), pacMan1_y.toDouble()))
        addPlayer(PacMan2(pacMan2_x.toDouble(), pacMan2_y.toDouble()))
    }

    fun fillPins(gameCanvas: Canvas) {
        addLevelElement(gameCanvas, Condensator(200.0, 600.0, 1))
        var i = 350
        while (i < 500) {
            addLevelElement(gameCanvas, Condensator(700.0, i.toDouble(), 0))
            i += 40
        }
        val end = 580
        val increment: Byte = 19
        addCondensators_y(gameCanvas, 285, end, increment.toInt(), 525)
        addCondensators_x(gameCanvas, 545, 805, increment.toInt(), 705)
        addLevelElement(gameCanvas, Condensator((525 + increment).toDouble(), (end + increment).toDouble()))
    }

    private fun addCondensators_y(gameCanvas: Canvas,
                                  start: Int,
                                  end: Int,
                                  increment: Int,
                                  x: Int) {
        var y = start
        while (y < end) {
            addLevelElement(gameCanvas, Condensator(x.toDouble(), y.toDouble()))
            addLevelElement(gameCanvas, Condensator((x + increment).toDouble(), y.toDouble()))
            y += increment
        }
    }

    private fun addCondensators_x(gameCanvas: Canvas,
                                  start: Int,
                                  end: Int,
                                  increment: Int,
                                  y: Int) {
        var x = start
        while (x < end) {
            addLevelElement(gameCanvas, Condensator(x.toDouble(), y.toDouble()))
            addLevelElement(gameCanvas, Condensator((x + increment).toDouble(), y.toDouble()))
            x += increment
        }
    }

    fun addSMDs(gameCanvas: Canvas?, smd_offset: Int) {
        var y = 0
        while (y < WindowConfig.window_height) {
            var x = 0
            while (x < WindowConfig.window_width) {
                val smd = SMD(x.toDouble(), y.toDouble())
                addLevelElement(gameCanvas!!, smd)
                x += smd_offset
            }
            y += smd_offset
        }
    }

    override fun addCollectable(c: Collectable): Boolean {
        if (!collidesWithPlayer(c)) {
            if (!collidesWithLevelElement(c)) {
                if (!collidesWithCollectable(c)) {
                    return super.addCollectable(c)
                }
            }
        }
        return false
    }

    override fun addLevelElement(levelElement: LevelElement): Boolean {
        if (!collidesWithPlayer(levelElement)) {
            if (!collidesWithLevelElement(levelElement)) {
                return super.addLevelElement(levelElement)
            }
        }
        return false
    }

    companion object {
        const val background_width = 950
        const val background_height = 950
    }
}
