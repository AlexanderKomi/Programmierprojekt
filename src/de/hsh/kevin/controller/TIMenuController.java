package de.hsh.kevin.controller;

import common.engine.components.menu.GameMenu;
import common.updates.UpdateCodes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Observable;
import java.util.Observer;

public class TIMenuController extends Observable {

    public static final String fxml = "../res/TIMenu.fxml";

    @FXML
    public Button btn_start;

    @FXML
    public Button btn_sound;

    @FXML
    public Button btn_schwierigkeit;

    @FXML
    public Button btn_sammlung;

    @FXML
    public Button btn_exit;


    @FXML
    void difficultyPressed(ActionEvent event) {

    }

    @FXML
    void exitPressed(ActionEvent event) {
        setChanged();
        notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
    }

    @FXML
    void sammlungPressed(ActionEvent event) {
	this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.mainMenu );
    }

    @FXML
    void soundPressed(ActionEvent event) {

    }

    @FXML
    void startGamePressed(ActionEvent event) {

    }
}
