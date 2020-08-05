package de.hsh.daniel.model

import de.hsh.daniel.model.board.Board
import de.hsh.daniel.model.board.GUIBoard
import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseEvent

/**
 * THis is the game started
 */
class Game(gameCanvas: Canvas) {
    private var board: GUIBoard = GUIBoard()
    fun render(gameCanvas: Canvas) = board.draw(gameCanvas)

    init {
        gameCanvas.onMouseClicked = EventHandler { e: MouseEvent ->
            board.onMouseClick(e.x, e.y)
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
        get() = board.board
}
