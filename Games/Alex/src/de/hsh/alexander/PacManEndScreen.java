package de.hsh.alexander;

import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.actor.player.PacMan1;
import de.hsh.alexander.actor.player.PacMan2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public final class PacManEndScreen extends Observable implements Initializable {

    public static final String  fxml        = "PacManEndScreen.fxml";
    private             boolean initialized = false;

    static int player1Points = 0;
    static int player2Points = 0;


    @FXML
    private Canvas player1Canvas;
    @FXML
    private Canvas player2Canvas;

    private PacMan1 pacMan1;
    private PacMan2 pacMan2;


    @FXML
    private Label player1PointsLabel;
    @FXML
    private Label player2PointsLabel;


    @FXML
    public void mainMenuButtonPressed() {
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.mainMenu );
    }

    @FXML
    public void repeatButtonPressed() {
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.repeatGame );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        pacMan1 = new PacMan1( 0, 0 );
        pacMan2 = new PacMan2( 0, 0 );
        initialized = true;
        Logger.log( this.getClass() + " : initialized" );
        this.player1PointsLabel.setText( String.valueOf( player1Points ) );
        this.player2PointsLabel.setText( String.valueOf( player2Points ) );
    }

    void render() {
        if ( !initialized ) {
            return;
        }
        player1Canvas.getGraphicsContext2D().clearRect( 0, 0, player1Canvas.getWidth(), player1Canvas.getHeight() );
        player2Canvas.getGraphicsContext2D().clearRect( 0, 0, player2Canvas.getWidth(), player2Canvas.getHeight() );

        pacMan1.draw( player1Canvas );
        pacMan2.draw( player2Canvas );
    }

}
