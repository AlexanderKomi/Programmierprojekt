package de.hsh.daniel.model;

import de.hsh.daniel.model.board.BoardUtilities;
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
        if(board.getC2() != null && !board.getC2().isCardMatched()) {
            BoardUtilities.delay(2);
        }
        if(board.getC2() != null && !board.getC2().isCardMatched()) {
            board.turnBackCards();
            board.nullCards();
        }

    }

    /**
    Sets up Board on canvas and creates two Player objects
     */
    public final void initialize( Canvas gameCanvas ) {
        board = new GUIBoard();

        gameCanvas.setOnMouseClicked(e -> {
            board.onMouseClick(e.getX(), e.getY());

        });

      /*  if(board.getC2() != null && !board.getC2().isCardMatched()) {
            BoardUtilities.delay(2);
        }*/



    }
}




