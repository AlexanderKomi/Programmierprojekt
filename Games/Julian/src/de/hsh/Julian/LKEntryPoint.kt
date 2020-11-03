/**
 * @author Julian Sender
 */
package de.hsh.Julian

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.updates.GameUpdater
import common.updates.UpdateLK
import common.util.Logger
import de.hsh.Julian.controller.LKStart
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import java.util.*

/**
 * Entrypoint for the Game
 */
class LKEntryPoint(o: Observer) : GameEntryPoint(o, WindowConfig.julian_title) {
    private val changer: LKFxmlChanger = LKFxmlChanger(this, LKStart.fxml, LKStart())
    private var canBeRendered = false
    private lateinit var canvas: Canvas
    private lateinit var lk: Leertastenklatsche

    /**
     * Rendering
     * @param fps setting the desired frames per second
     */
    override fun render(fps: Int) {
        if (!canBeRendered) {
            return
        }
        Platform.runLater {
            canvas.graphicsContext2D!!.clearRect(
                    0.0,
                    0.0,
                    WindowConfig.window_width.toDouble(),
                    WindowConfig.window_height.toDouble())
            lk.render(canvas)
        }
    }

    override fun gameUpdater(): GameUpdater = UpdateLK

    /**
     * Controls the scene-changes
     * @param o Observer
     * @param arg Ist Instanz von Canvas oder String
     */
    override fun update(o: Observable, arg: Any) = when (arg) {
        is Canvas -> initializeGame(arg)
        is String -> parseMessage(o, arg)
        else      -> changer.changeFxml(o, arg as String)
    }

    private fun initializeGame(arg: Canvas) {
        lk = Leertastenklatsche(this)

        canvas = arg
        canvas.onKeyPressed = EventHandler { e: KeyEvent -> lk.parseInput(e.code) }
        canvas.onKeyReleased = canvas.onKeyPressed

        with(canvas.graphicsContext2D!!) {
            font = Font.font("Helvetica", FontWeight.BOLD, 36.0)
            fill = Color.BLACK
            stroke = Color.BLACK
            lineWidth = 1.0
        }

        canBeRendered = true
    }

    private fun parseMessage(o: Observable, arg: String) =
            if (arg == "b_backtomenu") {
        canBeRendered = false
        changer.changeScene(LKStart.fxml, LKStart())
        exitToMainGUI()
    } else {
        Logger.log(arg)
        changer.changeFxml(o, arg)
    }
}
