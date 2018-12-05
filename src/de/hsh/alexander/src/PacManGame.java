package de.hsh.alexander.src;

import common.actor.Level;
import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.src.level.Level1;
import de.hsh.alexander.src.level.PacManLevel;
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
            }
            catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }

            currentLevel.addObserver( this );
            this.gameCanvas.setOnKeyPressed( currentLevel::keyboardInput ); // Only fires, when traversable
            this.gameCanvas.setOnKeyReleased( currentLevel::keyboardInput ); // Only fires, when traversable
            initialized = true;
            Logger.log( this.getClass() + ": init executed" );
        }
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            if ( message.equals( PacManLevel.gameFinishedMessage ) ) {
                Logger.log( this.getClass() + ": " + PacManLevel.gameFinishedMessage );
                this.setChanged();
                this.notifyObservers( UpdateCodes.PacMan.showEndScreen );
            }
            else {
                Logger.log( this.getClass() + ": unknown update : " + o + ", " + arg );
            }
        }
        else {
            Logger.log( this.getClass() + ": unknown update : " + o + ", " + arg );
        }
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
