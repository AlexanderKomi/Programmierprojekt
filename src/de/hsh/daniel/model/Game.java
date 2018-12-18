package de.hsh.daniel.model;

import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.daniel.controller.RamGame_controller;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


/**
 * THis is the game started
 */
public class Game {

    private Board               board       ;
    private double              imgSize     = (double) (WindowConfig.window_height / 4) - 20;
    private double              gridH       = 4;

    public void render(Canvas gameCanvas, int fps) {

        double              gridW       = (double) (Board.numberOfPairs / 2);
        Grid.drawGrid(gameCanvas, board, gridW, gridH, imgSize, Board.numberOfPairs);
    }

    public void initialize(Canvas gameCanvas) {
        board = new Board();
        //TODO: Implement set cardback transparent/invisible on click
    }
}




