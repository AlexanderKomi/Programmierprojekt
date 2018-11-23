package de.hsh.amir.controller;

import common.updates.UpdateCodes;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;

import java.util.Observable;

public class MainMenu_controller extends Observable{

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private Button b_1;

    @FXML
    private Button b_2;

    @FXML
    private Button b_3;

    @FXML
    private Button b_4;

    @FXML
    private Button b_5;

    @FXML
    void button_clicked(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        setChanged();
        notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
    }

}
