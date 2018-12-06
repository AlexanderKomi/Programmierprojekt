package de.hsh.alexander;


import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Observable;

public class PacManMenu extends Observable {

    public static final String fxml = "PacManMenu.fxml";

    @FXML
    public VBox   basicPane;
    @FXML
    public Button backButton;
    @FXML
    public Button okButton;


    public void backButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Back Button pressed" );
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.mainMenu );
    }

    public void okButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Ok Button pressed" );
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.startGame );
    }
}
