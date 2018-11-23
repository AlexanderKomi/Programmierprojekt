package de.hsh.alexander;

import common.util.Logger;
import de.hsh.alexander.actor.Direction;
import de.hsh.alexander.actor.PacMan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Initializable {

    public static final String fxml = "PacManGame.fxml";

    @FXML
    public AnchorPane gamePane;

    @FXML
    private Canvas gameCanvas;
    private PacMan pacMan1;
    private PacMan pacMan2;

    public boolean initialized = false;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        init();
        bindKeys();
    }

    private void init() {
        if ( !initialized ) {
            initPacMan1();
            initPacMan2();
            initialized = true;
        }
    }

    private void bindKeys() {
        //gameCanvas.setOnKeyPressed( this::movePacMan );
        //gameCanvas.setOnKeyReleased( this::movePacMan );
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

    public void test() {
        Logger.log( "Test: Key Released" );
    }

    public void movePacMan( KeyEvent keyEvent ) {
        Logger.log( "Key pressed : " + keyEvent );
        pacMan1.move( keyEvent );
        pacMan2.move( keyEvent );
        String keyName = keyEvent.getCode().getName();
        switch ( keyName ) {
            case "Up":
                pacMan1.movePos( -5, 0 );
                break;
            case "Down":
                pacMan1.movePos( 5, 0 );
                break;
            case "Left":
                pacMan1.movePos( 0, -5 );
                break;
            case "Right":
                pacMan1.movePos( 0, 5 );
                break;
        }
    }

    void render() {
            if ( !initialized ) {
                return;
            }
        this.gameCanvas.getGraphicsContext2D().setFill( Color.WHITE );
        this.gameCanvas.getGraphicsContext2D().fillRect( 0, 0, 1200, 800 );
        pacMan1.draw( this.gameCanvas.getGraphicsContext2D() );
        pacMan2.draw( this.gameCanvas.getGraphicsContext2D() );
            pacMan1.movePos();
            pacMan2.movePos();

    }
}
