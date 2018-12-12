package de.hsh.amir.controller;

import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.amir.logic.Gegner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;

public class AmirsMainMenuController extends Observable {

    public static final String fxml = "view/AmirsMenu.fxml";
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
        String id = getId(event);
        this.setChanged();
        if (id.equals("startGameButton")) {
            this.notifyObservers(UpdateCodes.Amir.startGame);
        } else if (id.equals("exitButton")) {
            this.notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
        } else if (id.equals("level1Button")) {
            Logger.log(this.getClass() + " : level1 Button");
            Gegner.setGegnerSpeed(30);
            level1Button.setDisable(true);
            level2Button.setDisable(false);
            level3Button.setDisable(false);
        } else if (id.equals("level2Button")) {
            Logger.log(this.getClass() + " : level2 Button");
            Gegner.setGegnerSpeed(30);
            level1Button.setDisable(false);
            level2Button.setDisable(true);
            level3Button.setDisable(false);
        } else if (id.equals("level3Button")) {
            Logger.log(this.getClass() + " : level3 Button");
            Gegner.setGegnerSpeed(30);
            level1Button.setDisable(false);
            level2Button.setDisable(false);
            level3Button.setDisable(true);
        }else {
            this.notifyObservers(id);
        }
    }

    private String getId(ActionEvent event) {
        return ((Node) event.getSource()).getId();
    }
}
