package de.hsh.alexander.game;

import de.hsh.alexander.game.actor.PacMan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Initializable {

    public static  String     fxml = "PacManGame.fxml";
    private static Observer   temp;
    @FXML
    public static  AnchorPane gamePane;

    @FXML
    public         Canvas          gameCanvas;
    private static Canvas          tempcanvas;
    private        PacMan          pacMan1;
    private        boolean         initialized = false;
    private        GraphicsContext gc;
    public PacManGame() {}

    public PacManGame( Observer o ) {
        temp = o;
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.addObserver( temp );
        fillCanvas();
    }

    private void fillCanvas() {
        tempcanvas = this.gameCanvas;
        gc = tempcanvas.getGraphicsContext2D();
        gc.fillText( "Test text", 100.0, 100.0 );
    }

    void movePacMan1( KeyEvent keyEvent ) {
        init();
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
    }

    private void init() {
        if ( !initialized ) {
            gc = tempcanvas.getGraphicsContext2D();
            pacMan1 = new PacMan( "Bug.png" );
            initialized = true;
        }
    }

    void render() {
        if ( !initialized ) {
            return;
        }
        gc.setFill( Color.WHITE );
        gc.fillRect( 0, 0, 1200, 800 );
        pacMan1.draw( gc );
    }
}
