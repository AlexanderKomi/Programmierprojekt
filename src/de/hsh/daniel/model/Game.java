package de.hsh.daniel.model;

import common.config.WindowConfig;
import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public class Game {

    private Board  board;
    private double imgSize = (double) (WindowConfig.window_height / 4) - 20;
    private double gridH   = 4;
    private double gridW;

    public void render(Canvas gameCanvas, int fps) {
        double              gridW       = (double) (Board.numberOfPairs / 2);
        Board.drawGrid( gameCanvas, board, gridW, gridH, imgSize, Board.numberOfPairs );
    }

    public void initialize(Canvas gameCanvas) {
        board = new Board();

        //TODO: Implement set cardback transparent/invisible on click
    }
}




