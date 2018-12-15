package de.hsh.kevin.controller;

import common.updates.UpdateCodes;
import de.hsh.kevin.logic.Config;
import de.hsh.kevin.logic.enmDifficultyOptions;
import de.hsh.kevin.logic.enmSoundOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Observable;

public class TIMenuController extends Observable{

    public static final String fxml = "TIMenu.fxml";

    @FXML
    public Button btn_start;

    @FXML
    public Button btn_sound;

    @FXML
    public Button btn_schwierigkeit;

    @FXML
    public Button btn_sammlung;

    
    public TIMenuController() {
       Config.setDifficulyPreset();
       Config.setSoundPreset();
    }
    
    
    @FXML
    void difficultyPressed(ActionEvent event) {
	Config.switchDifficulty();
	String option = "";

	if (Config.getDifficultyOption() == enmDifficultyOptions.easy) {
	    option = "einfach";
	} else if (Config.getDifficultyOption() == enmDifficultyOptions.normal) {
	    option = "normal";
	} else {
	    option = "schwer";
	}
	btn_schwierigkeit.setText("Schwierigkeit: " + option);
    }

    @FXML
    void exitPressed(ActionEvent event) {
	System.exit(0);
    }

    @FXML
    void sammlungPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
    }

    @FXML
    void soundPressed(ActionEvent event) {
	Config.switchSound();
	String option = "";
	if (Config.getSoundOption() == enmSoundOptions.on) {
	    option = "on";
	} else {
	    option = "off";
	}
	btn_sound.setText("Sound: " + option);
    }

    @FXML
    void startGamePressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.playGame);
    }

}
