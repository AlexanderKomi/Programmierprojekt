package de.hsh.alexander;

import common.actor.Level;
import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.level.Level1;
import de.hsh.alexander.level.PacManLevel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Observer, Initializable {

    public static final String fxml = "PacManGame.fxml";
    boolean initialized = false;

    private Level currentLevel;

    public PacManGame() {
        reset();
    }

    @FXML
    private Canvas gameCanvas;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        if ( initialized ) {
            return;
        }
        initialized = true;
        reset();
        Logger.log( this.getClass() + ": init executed" );

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

    void render( int fps ) {
        if ( !initialized ) {
            return;
        }
        clearCanvas();
        this.currentLevel.render( this.gameCanvas, fps );
    }

    void reset() {
        if ( !initialized ) {
            this.gameCanvas = new Canvas();
        }

        this.currentLevel = new Level1();
        this.currentLevel.reset();
        this.gameCanvas.setFocusTraversable( true ); // DO NOT DELETE!!!! -> Otherwise does not fire events!
        this.gameCanvas.setOnKeyPressed( e -> {
            this.currentLevel.keyboardInput( e );
            Logger.log( "Key pressed" );
        } ); // Only fires, when traversable
        this.gameCanvas.setOnKeyReleased( this.currentLevel::keyboardInput ); // Only fires, when traversable


        this.currentLevel.addObserver( this );
        Logger.log( this.getClass() + ": Resetted game" );
    }

    private void clearCanvas() {
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        this.gameCanvas.getGraphicsContext2D().clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );
    }


}
