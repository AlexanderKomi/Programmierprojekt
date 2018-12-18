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

import static de.hsh.daniel.model.Board.numberOfPairs;

/**
 * This is the game fxml-controller started
 */
public class RamGame_controller extends Observable implements Initializable {

    public static final String fxml = "view/RAMGame.fxml";
    private Game game;
    private boolean initialized = false;

    @FXML
    private Canvas gameCanvas;

    public void render(int fps) {
        if(game != null){
            if(initialized) {
                gameCanvas.getGraphicsContext2D().clearRect(0,0,WindowConfig.window_width, WindowConfig.window_height);
                game.render(gameCanvas, fps);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Logger.log(this.getClass() + ": initialized");
        gameCanvas.setFocusTraversable(true);
        game = new Game();
        game.initialize(gameCanvas);
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


