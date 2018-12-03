package de.hsh.alexander.src;

import common.actor.Level;
import common.config.WindowConfig;
import common.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Observer, Initializable {

    public static final  String   fxml          = "PacManGame.fxml";
    private static       boolean  initialized   = false;

    private Level currentLevel;

    @FXML
    private Canvas gameCanvas;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        if ( !initialized ) {
            this.gameCanvas.setFocusTraversable( true ); // DO NOT DELETE!!!! -> Otherwise does not fire events!
            try {
                currentLevel = new Level1();
                currentLevel.createLevel();
            }
            catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
            this.gameCanvas.setOnKeyPressed( currentLevel::keyboardInput ); // Only fires, when traversable
            this.gameCanvas.setOnKeyReleased( currentLevel::keyboardInput ); // Only fires, when traversable
            initialized = true;
            Logger.log( this.getClass() + ": init executed" );
        }
    }

    @Override
    public void update( Observable o, Object arg ) {
        Logger.log( this.getClass() + ": " + o + ", " + arg );
    }

    void render(int fps) {
        if ( !initialized ) {
            return;
        }
        clearCanvas();
        currentLevel.render( this.gameCanvas, fps );
    }

    private void clearCanvas() {
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        this.gameCanvas.getGraphicsContext2D().clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );
    }


}
