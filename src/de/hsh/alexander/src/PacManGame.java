package de.hsh.alexander.src;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.src.level.PacManLevel;
import de.hsh.alexander.src.level.level1.Level1;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Observer, Initializable {

    public static final String fxml = "PacManGame.fxml";
    boolean initialized = false;

    private PacManLevel currentLevel;

    public PacManGame() {
        //reset();
    }

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label player1Points;
    @FXML
    private Label player2Points;


    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        if ( initialized ) {
            return;
        }
        initialized = true;
        reset();
        bindLabelsToPoints();
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

    void render( final int fps ) {
        if ( !initialized ) {
            return;
        }
        Platform.runLater( () -> {
            clearCanvas();
            if ( this.currentLevel != null ) {
                this.currentLevel.render( this.gameCanvas, fps );
            }
        } );
    }

    private void reset() {
        if ( !initialized ) {
            return;
        }
        this.gameCanvas.setFocusTraversable( true ); // DO NOT DELETE!!!! -> Otherwise does not fire events!
        this.currentLevel = new Level1( gameCanvas );
        this.gameCanvas.setOnKeyPressed( this.currentLevel::keyboardInput );
        this.gameCanvas.setOnKeyReleased( this.currentLevel::keyboardInput ); // Only fires, when traversable
        this.currentLevel.addObserver( this );
        Logger.log( this.getClass() + ": Resetted game" );
    }

    private void bindLabelsToPoints() {
        this.currentLevel.getPacMan1Property().addListener( ( obj, oldValue, newValue ) -> {
            Platform.runLater( () -> {
                this.player1Points.setText( String.valueOf( newValue ) );
            } );
        } );

        this.currentLevel.getPacMan2Property().addListener( ( obj, oldValue, newValue ) -> {
            Platform.runLater( () -> {
                this.player2Points.setText( String.valueOf( newValue ) );
            } );
        } );

    }

    private void clearCanvas() {
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        this.gameCanvas.getGraphicsContext2D().clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );
    }

    public PacManLevel getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public synchronized void deleteObservers() {
        this.currentLevel.deleteObservers();
        super.deleteObservers();
    }

    public synchronized void delete() {
        this.deleteObservers();
        this.currentLevel = null;
    }
}
