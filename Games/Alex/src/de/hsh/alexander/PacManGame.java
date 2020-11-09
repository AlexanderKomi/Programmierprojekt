package de.hsh.alexander;

import common.config.WindowConfig;
import common.updates.Updatable;
import common.updates.UpdateCodes;
import common.updates.Updater;
import common.util.Logger;
import de.hsh.alexander.level.PacManLevel;
import de.hsh.alexander.actor.player.PacMan1;
import de.hsh.alexander.actor.player.PacMan2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public final class PacManGame implements Updatable, Updater, Initializable {

    public static final String gameFinishedMessage = "PacMan : Game finished";
    public static final String fxml                = "PacManGame.fxml";
    boolean initialized = false;

    private PacManLevel currentLevel;
    private PacMan1 pacMan1;
    private PacMan2 pacMan2;

    @FXML
    private Canvas gameCanvas;
    @FXML
    private Canvas player1Canvas;
    @FXML
    private Canvas player2Canvas;
    @FXML
    private Label player1Points;
    @FXML
    private Label player2Points;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        if ( initialized ) {
            return;
        }
        pacMan1 = new PacMan1( 0, 0 );
        pacMan2 = new PacMan2( 0, 0 );

        initialized = true;
        reset();
        bindLabelsToPoints();
        Logger.INSTANCE.log( this.getClass() + ": init executed" );
    }

    @Override
    public void update(Updatable o, String arg ) {
        if (arg != null) {
            if ( arg.equals( gameFinishedMessage ) ) {
                Logger.INSTANCE.log( this.getClass() + ": " + gameFinishedMessage );
                this.notifyUpdater( UpdateCodes.PacMan.showEndScreen );
            } else Logger.INSTANCE.log(this.getClass() + ": unknown update : " + o + ", " + arg);
        } else Logger.INSTANCE.log(this.getClass() + ": unknown update : " + o + ", " + arg);
    }

    void render( final int fps ) {
        if ( !initialized ) {
            return;
        }
        Platform.runLater( () -> {
            //PacManMenu.test( player1Canvas, player2Canvas, pacMan1, pacMan2 );
            PacManMenu.clearAndDraw( pacMan1, player1Canvas );
            PacManMenu.clearAndDraw( pacMan2, player2Canvas );


            if ( this.currentLevel != null ) {
                clearCanvas();
                this.currentLevel.render( this.gameCanvas, fps );
            }
        } );
    }

    private void reset() {
        if ( !initialized ) {
            return;
        }
        this.gameCanvas.setFocusTraversable( true ); // DO NOT DELETE!!!! -> Otherwise does not fire events!
        this.currentLevel = new PacManLevel(gameCanvas);
        this.gameCanvas.setOnKeyPressed( this.currentLevel::keyboardInput );
        this.gameCanvas.setOnKeyReleased( this.currentLevel::keyboardInput ); // Only fires, when traversable
        this.currentLevel.addObserver( this );
        Logger.INSTANCE.log( this.getClass() + ": Resetted game" );
    }

    private void bindLabelsToPoints() {
        this.currentLevel.getPacMan1Property()
                .addListener(
                        ( obj, oldValue, newValue ) ->
                                Platform.runLater(() -> this.player1Points.setText(String.valueOf(newValue ) )));
        this.currentLevel.getPacMan2Property()
                .addListener(
                        ( obj, oldValue, newValue ) ->
                                Platform.runLater(() -> this.player2Points.setText(String.valueOf(newValue ) )));
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

    synchronized void delete() {
        this.deleteObservers();
        this.currentLevel = null;
    }

    @Override
    public List<Updater> getUpdaterList() {
        return null;
    }
}
