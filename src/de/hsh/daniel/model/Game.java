package de.hsh.daniel.model;

import de.hsh.daniel.model.board.Board;
import de.hsh.daniel.model.board.GUIBoard;
import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public final class Game {

    private GUIBoard board;

    public final void render( Canvas gameCanvas, final int fps ) {
        board.draw(gameCanvas);
    }

    /**
    Sets up Board on canvas and creates two Player objects
     */
    public final void initialize( Canvas gameCanvas ) {
        board = new GUIBoard();

        gameCanvas.setOnMouseClicked(e -> {
            board.onMouseClick(e.getX(), e.getY());
        });
    }

    public Board getGUIBoard() { return board.getBoard(); }


   // public Board getBoard() { return this.board; }
}




