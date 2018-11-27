package de.hsh.alexander;

import common.actor.Direction;
import common.util.Logger;
import de.hsh.alexander.actor.PacMan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class PacManGame extends Observable implements Observer, Initializable {

    public static final String  fxml        = "PacManGame.fxml";
    @FXML
    public              Canvas  gameCanvas;
    private static      boolean initialized = false;
    private             PacMan  pacMan1;
    private             PacMan  pacMan2;

    private void movePacMan( KeyEvent keyEvent ) {
        pacMan1.move( keyEvent );
        pacMan2.move( keyEvent );
        if ( pacMan1.doesCollide( pacMan2 ) ) {
            //Logger.log( "Pacman1 collides with pacman2" );
        }
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        if ( !initialized ) {
            this.gameCanvas.setFocusTraversable( true ); // DO NOT DELETE!!!! -> Otherwise does not fire events!
            this.gameCanvas.setOnKeyPressed( this::movePacMan ); // Only fires, when traversable
            this.gameCanvas.setOnKeyReleased( this::movePacMan ); // Only fires, when traversable
            initPacMan1();
            initPacMan2();
            initialized = true;
            Logger.log( this.getClass() + ": init executed" );
        }
    }

    private void initPacMan1() {
        HashMap<String, Direction> pacMan1KeyMap = new HashMap<>();
        pacMan1KeyMap.put( "Up", Direction.Up );
        pacMan1KeyMap.put( "Down", Direction.Down );
        pacMan1KeyMap.put( "Left", Direction.Left );
        pacMan1KeyMap.put( "Right", Direction.Right );
        try {
            ArrayList<String> images = new ArrayList<>();
            images.add( "p1_stand.png" );
            images.add( "p1_front.png" );

            pacMan1 = new PacMan( images, pacMan1KeyMap );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private void initPacMan2() {
        HashMap<String, Direction> pacMan2KeyMap = new HashMap<>();
        pacMan2KeyMap.put( "W", Direction.Up );
        pacMan2KeyMap.put( "S", Direction.Down );
        pacMan2KeyMap.put( "A", Direction.Left );
        pacMan2KeyMap.put( "D", Direction.Right );
        try {
            pacMan2 = new PacMan( "snailWalk2.png", pacMan2KeyMap );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
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
        pacMan1.draw( this.gameCanvas );
        pacMan2.draw( this.gameCanvas );
    }
}
