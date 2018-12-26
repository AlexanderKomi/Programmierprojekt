package de.hsh.daniel.model;

import de.hsh.daniel.model.board.GUIBoard;
import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public final class Game {

    private GUIBoard board;
    private Player   p1;
    private Player   p2;

    public final void render( Canvas gameCanvas, final int fps ) {
        board.draw(gameCanvas);
    }

    /**
    Sets up Board on canvas and creates two Player objects
     */
    public final void initialize( Canvas gameCanvas ) {
        board = new GUIBoard();
        p1 = new Player();
        p2 = new Player();

        gameCanvas.setOnMouseClicked(e -> {
            board.onMouseClick(e.getX(), e.getY());
        });


    }
}




