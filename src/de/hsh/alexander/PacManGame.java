package de.hsh.alexander;

import common.util.Logger;
import de.hsh.alexander.actor.Direction;
import de.hsh.alexander.actor.PacMan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Observer, Initializable {

    public static final String  fxml        = "PacManGame.fxml";
    @FXML
    public              Canvas  gameCanvas;
    private             boolean initialized = false;
    private             PacMan  pacMan1;
    private             PacMan  pacMan2;

    private void movePacMan( KeyEvent keyEvent ) {
        pacMan1.move( keyEvent );
        pacMan2.move( keyEvent );
        //render();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        init();
    }

    private void init() {
        if ( !initialized ) {
            this.gameCanvas.setFocusTraversable( true );
            this.gameCanvas.setOnKeyPressed( this::movePacMan );
            this.gameCanvas.setOnKeyReleased( this::movePacMan );
            initPacMan1();
            initPacMan2();
            initialized = true;
            render();
            Logger.log( this.getClass() + ": init executed" );
        }
    }

    private void initPacMan1() {
        HashMap<String, Direction> pacMan1KeyMap = new HashMap<>();
        pacMan1KeyMap.put( "Up", Direction.Up );
        pacMan1KeyMap.put( "Down", Direction.Down );
        pacMan1KeyMap.put( "Left", Direction.Left );
        pacMan1KeyMap.put( "Right", Direction.Right );
        pacMan1 = new PacMan( "Bug.png", pacMan1KeyMap );
    }

    private void initPacMan2() {
        HashMap<String, Direction> pacMan2KeyMap = new HashMap<>();
        pacMan2KeyMap.put( "W", Direction.Up );
        pacMan2KeyMap.put( "S", Direction.Down );
        pacMan2KeyMap.put( "A", Direction.Left );
        pacMan2KeyMap.put( "D", Direction.Right );
        pacMan2 = new PacMan( "Bug.png", pacMan2KeyMap );
    }

    private void clearCanvas() {
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        this.gameCanvas.getGraphicsContext2D().fillRect( 0, 0, 1200, 800 );
    }

    @Override
    public void update( Observable o, Object arg ) {
        Logger.log( this.getClass() + ": " + o + ", " + arg );
    }

    void render() {
        if ( !initialized ) {
            return;
        }
        clearCanvas();
        pacMan1.draw( this.gameCanvas.getGraphicsContext2D() );
        pacMan2.draw( this.gameCanvas.getGraphicsContext2D() );
    }
}
