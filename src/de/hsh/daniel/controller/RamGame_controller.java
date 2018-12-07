package de.hsh.daniel.controller;

import common.util.Logger;
import de.hsh.daniel.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/** This is the game fxml-controller started */
public class RamGame_controller extends Observable implements Initializable {

    public static final String fxml = "view/RAMGame.fxml";
    private             Game   game = new Game();
    @FXML
    private             Canvas gameCanvas;

    public void render( int fps ) {
        //Logger.log( "render" );
        game.render( gameCanvas, fps );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        Logger.log( this.getClass() + ": initialized" );
        gameCanvas.setFocusTraversable( true );
    }
}

