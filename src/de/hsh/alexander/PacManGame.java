package de.hsh.alexander;

import common.actor.Direction;
import common.util.Logger;
import common.util.Path;
import de.hsh.alexander.actor.PacMan;
import de.hsh.alexander.actor.TestWall;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class PacManGame extends Observable implements Observer, Initializable {

    private static final String   actorLocation = Path.getExecutionLocation() + "de/hsh/alexander/actor/";
    public static final  String   fxml          = "PacManGame.fxml";
    @FXML
    public               Canvas   gameCanvas;
    private static       boolean  initialized   = false;
    private              PacMan   pacMan1;
    private              PacMan   pacMan2;
    private              TestWall wall;

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
            try {
                pacMan1 = initPacMan1();
                pacMan2 = initPacMan2();
                wall = initTestWall();
            }
            catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
            initialized = true;
            Logger.log( this.getClass() + ": init executed" );
        }
    }

    private TestWall initTestWall() throws FileNotFoundException {
        return new TestWall(actorLocation + "p1_front.png", 300, 400);
    }

    private PacMan initPacMan1() throws FileNotFoundException {
        HashMap<String, Direction> pacMan1KeyMap = new HashMap<>();
        pacMan1KeyMap.put( "Up", Direction.Up );
        pacMan1KeyMap.put( "Down", Direction.Down );
        pacMan1KeyMap.put( "Left", Direction.Left );
        pacMan1KeyMap.put( "Right", Direction.Right );

        ArrayList<String> images = new ArrayList<>();
        images.add( actorLocation + "sprite_pacman1_1.png" );
        images.add( actorLocation + "sprite_pacman1_2.png" );
        images.add( actorLocation + "sprite_pacman1_3.png" );
        images.add( actorLocation + "sprite_pacman1_4.png" );

        return new PacMan( images, pacMan1KeyMap );

    }

    private PacMan initPacMan2() throws FileNotFoundException {
        HashMap<String, Direction> pacMan2KeyMap = new HashMap<>();
        pacMan2KeyMap.put( "W", Direction.Up );
        pacMan2KeyMap.put( "S", Direction.Down );
        pacMan2KeyMap.put( "A", Direction.Left );
        pacMan2KeyMap.put( "D", Direction.Right );

        return new PacMan( actorLocation + "snailWalk2.png", 500, 500, pacMan2KeyMap );
    }

    private void clearCanvas() {
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        //this.gameCanvas.getGraphicsContext2D().fillRect(  );
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
        this.gameCanvas.getGraphicsContext2D().clearRect( 0, 0, 1200, 800 );
        this.wall.draw( this.gameCanvas );
        pacMan1.drawAndApplyCollision( this.gameCanvas, this.wall ); // TODO : Fix collision bug
        pacMan1.drawAndApplyCollision( this.gameCanvas, pacMan2 );
        pacMan2.drawAndApplyCollision( this.gameCanvas, pacMan1 );
    }
}
