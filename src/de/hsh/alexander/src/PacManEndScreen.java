package de.hsh.alexander.src;

import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class PacManEndScreen extends Observable implements Initializable {

    public static final String fxml = "PacManEndScreen.fxml";

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
        Logger.log( this.getClass() + " : initialized" );
    }
}
