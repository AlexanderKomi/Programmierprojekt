package de.hsh.alexander.src;

import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public final class PacManEndScreen extends Observable implements Initializable {

    public static final String  fxml        = "PacManEndScreen.fxml";
    private             boolean initialized = false;

    public static int player1Points = 0;
    public static int player2Points = 0;


    @FXML
    public Label player1PointsLabel;
    @FXML
    public Label player2PointsLabel;


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
        initialized = true;
        Logger.log( this.getClass() + " : initialized" );
        this.player1PointsLabel.setText( String.valueOf( player1Points ) );
        this.player2PointsLabel.setText( String.valueOf( player2Points ) );
    }

    void afterInitialization( final int player1Points, final int player2Points ) {
        if ( !initialized ) {
            this.player1PointsLabel = new Label( String.valueOf( player1Points ) );
            this.player2PointsLabel = new Label( String.valueOf( player2Points ) );
            Logger.log( new IllegalStateException(
                    "-------> ERROR : " + this.getClass() + ": Not initialized, but should be." ) );
            return;
        }
        else {
            this.player1PointsLabel.setText( String.valueOf( player1Points ) );
            this.player2PointsLabel.setText( String.valueOf( player2Points ) );
        }
    }
}
