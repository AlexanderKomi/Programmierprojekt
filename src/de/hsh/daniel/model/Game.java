package de.hsh.daniel.model;

import common.util.Logger;
import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public class Game {

    private Board  board;

    public void render(Canvas gameCanvas, int fps) {
        board.draw( gameCanvas );
    }

    public void initialize(Canvas gameCanvas) {
        gameCanvas.setOnMouseClicked( e -> {
            Logger.log( this.getClass() + ": Clicked at (" + e.getX() + ", " + e.getY() + ")" );
        } );
        board = new Board();
        board.createGrid();
        //TODO: Implement set cardback transparent/invisible on click
    }
}




