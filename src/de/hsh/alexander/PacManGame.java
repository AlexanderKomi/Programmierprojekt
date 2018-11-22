package de.hsh.alexander;

import de.hsh.alexander.actor.Direction;
import de.hsh.alexander.actor.PacMan;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Initializable {

    public static  String     fxml = "PacManGame.fxml";

    @FXML
    public static  AnchorPane gamePane;

    @FXML
    public         Canvas gameCanvas;
    private static Canvas tempcanvas;
    private        PacMan pacMan1;
    private        PacMan pacMan2;

    private        boolean         initialized = false;
    private        GraphicsContext gc;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        fillCanvas();
    }

    void init() {
        if ( !initialized ) {
            gc = tempcanvas.getGraphicsContext2D();
            initPacMan1();
            initPacMan2();
            initialized = true;
        }
    }

    private void fillCanvas() {
        tempcanvas = this.gameCanvas;
        gc = tempcanvas.getGraphicsContext2D();
        gc.fillText( "Test text", 100.0, 100.0 );
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

    void movePacMan1( KeyEvent keyEvent ) {
        pacMan1.move( keyEvent );
        pacMan2.move( keyEvent );

        /*
        String keyName = keyEvent.getCode().getName();
        if ( keyName.equals( "Up" ) ) {
            pacMan1.movePos( -5, 0 );
        }
        else if ( keyName.equals( "Down" ) ) {
            pacMan1.movePos( 5, 0 );
        }
        else if ( keyName.equals( "Left" ) ) {
            pacMan1.movePos( 0, -5 );
        }
        else if ( keyName.equals( "Right" ) ) {
            pacMan1.movePos( 0, 5 );
        }
        */
    }

    void render() {
        Platform.runLater( () -> {
            if ( !initialized ) {
                return;
            }
            gc.setFill( Color.WHITE );
            gc.fillRect( 0, 0, 1200, 800 );
            pacMan1.draw( gc );
            pacMan2.draw( gc );
            pacMan1.movePos();
            pacMan2.movePos();
        } );

    }
}
