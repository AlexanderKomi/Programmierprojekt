package de.hsh.alexander.src;

import common.updates.UpdateCodes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Observable;

public class PacManEndScreen extends Observable {

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
}
