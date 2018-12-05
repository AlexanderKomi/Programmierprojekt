package de.hsh.amir.controller;

import common.updates.UpdateCodes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;

public class AmirsMainMenuController extends Observable{

    public static final String fxml = "view/AmirsMenu.fxml";

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private Button startButton;

    @FXML
    private Button level1Button;

    @FXML
    private Button level2Button;

    @FXML
    private Button level3Button;

    @FXML
    private Button exitButton;

    @FXML
    void button_clicked(ActionEvent event) {
        String id = getId( event );
        this.setChanged();
        if ( id.equals( "startGameButton" ) ) {
            this.notifyObservers( UpdateCodes.Amir.startGame );
        }
        else if ( id.equals( "exitButton" ) ) {
            this.notifyObservers( UpdateCodes.Amir.mainMenu );
        }
        else {
            this.notifyObservers( id );
        }
    }

    private String getId( ActionEvent event ) {
        return ((Node) event.getSource()).getId();
    }
}
