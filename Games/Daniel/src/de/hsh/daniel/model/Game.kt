package de.hsh.daniel.model

import de.hsh.daniel.model.board.Board
import de.hsh.daniel.model.board.GUIBoard
import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseEvent

/**
 * THis is the game started
 */
class Game {
    private var board: GUIBoard? = null
    fun render(gameCanvas: Canvas, fps: Int) {
        board!!.draw(gameCanvas)
    }

    /**
     * Sets up Board on canvas and creates two Player objects
     */
    fun initialize(gameCanvas: Canvas) {
        board = GUIBoard()
        gameCanvas.onMouseClicked = EventHandler { e: MouseEvent ->
            board!!.onMouseClick(e.x,
                                 e.y)
        }
    }

    /**
     * *****************************************************************
     * *                                                               *
     * *                       GETTERS & SETTERS                       *
     * *                                                               *
     * *****************************************************************
     *
     */
    val gUIBoard: Board
        get() = board!!.board
}
