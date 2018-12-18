package de.hsh.daniel.controller;

import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.daniel.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * This is the game fxml-controller started
 */
public class RamGame_controller extends Observable implements Initializable {

    public static final String fxml = "view/RAMGame.fxml";
    private Game game = new Game();
    private boolean initialized = false;

    @FXML
    private Canvas gameCanvas;

    public void render(int fps) {
        //Logger.log( "render" );
        game.render(gameCanvas, fps);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logger.log(this.getClass() + ": initialized");
        gameCanvas.setFocusTraversable(true);

        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Board board = BoardFactory.initBoard(BoardFactory.getBoardPairs());
        Image cardBack = new Image("de/hsh/daniel/resources/0.png");

        double gridW = (double) (board.getNumberOfPairs() / 2);
        double gridH = 4;
        int backCount = 0;
        double imgSize = (double) (WindowConfig.window_height / 4) - 20;


        //TODO: Implement set cardback transparent/invisible on click


        //Draws card images
        if (board.getNumberOfPairs() == 6) {
            Grid.drawGrid(gc, board, gridW, gridH, imgSize, board.getNumberOfPairs());
        } else if (board.getNumberOfPairs() == 8) {
            Grid.drawGrid(gc, board, gridW, gridH, imgSize, board.getNumberOfPairs());

        } else if (board.getNumberOfPairs() == 10) {
            Grid.drawGrid(gc, board, gridW, gridH, imgSize, board.getNumberOfPairs());
        }


        initialized = true;


/*

            //Draws card backside over card image


            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), backCount++) {
                     gc.drawImage(cardBack, xStart, yStart, imgSize, imgSize);
                }
                yStart += imgSize + 20;
                xStart = 10;
        }
*/


    }
}


