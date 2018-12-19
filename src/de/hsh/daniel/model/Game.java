package de.hsh.daniel.model;

import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public class Game {

    private Board  board;

    public void render( Canvas gameCanvas, final int fps ) {
        board.draw( gameCanvas );
    }

    public void initialize(Canvas gameCanvas) {
        board = new Board();
        gameCanvas.setOnMouseClicked( e -> {
            board.onMouseClick( e.getX(), e.getY() );
        } );
        //TODO: Implement set cardback transparent/invisible on click
    }
}




