package de.hsh.alexander;

import common.actor.Actor;
import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.alexander.actor.ActorCreator;
import de.hsh.alexander.actor.PacMan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Observer, Initializable {

    public static final  String   fxml          = "PacManGame.fxml";
    private static       boolean  initialized   = false;
    
    private static ArrayList<PacMan> pacMen = new ArrayList<>(  );
    private static ArrayList<Actor> level = new ArrayList<>(  );

    @FXML
    private Canvas gameCanvas;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        if ( !initialized ) {
            this.gameCanvas.setFocusTraversable( true ); // DO NOT DELETE!!!! -> Otherwise does not fire events!
            this.gameCanvas.setOnKeyPressed( this::movePacMan ); // Only fires, when traversable
            this.gameCanvas.setOnKeyReleased( this::movePacMan ); // Only fires, when traversable
            try {
                pacMen.add( ActorCreator.initPacMan1() );
                pacMen.add( ActorCreator.initPacMan2() );
                level.add( ActorCreator.initTestWall() );
                level.forEach( levelElement -> pacMen.forEach( pacMan -> pacMan.addCollidingActor( levelElement ) ) );
            }
            catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
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
        level.forEach( levelElement -> {levelElement.draw( this.gameCanvas );} );
        pacMen.forEach( pacMan -> pacMan.draw( this.gameCanvas ) );
    }

    private void movePacMan( KeyEvent keyEvent ) {
        pacMen.forEach( pacMan -> pacMan.move(keyEvent) );
    }

    private void clearCanvas() {
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        this.gameCanvas.getGraphicsContext2D().clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );
    }


}
