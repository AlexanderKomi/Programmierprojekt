package de.hsh.daniel.controller;

import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.daniel.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

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
        gameCanvas.setOnMouseClicked( e -> {
            Logger.log( this.getClass() + ": Clicked at (" + e.getX() + ", " + e.getY() + ")" );
        } );
        game = new Game();
        game.initialize(gameCanvas);
        initialized = true;
    }
}


