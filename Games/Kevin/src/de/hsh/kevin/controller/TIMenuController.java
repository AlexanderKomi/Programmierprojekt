package de.hsh.kevin.controller;

import common.updates.MenuCodes;
import common.updates.UpdateCodes;
import de.hsh.kevin.logic.Config;
import de.hsh.kevin.logic.enmDifficultyOptions;
import de.hsh.kevin.logic.enmSoundOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Observable;

/**
 * Erstellt das Menü
 * 
 * @author Kevin
 *
 */
public class TIMenuController extends Observable {

    public static final String fxml = "TIMenu.fxml";

    @FXML
    public Button btn_start;

    @FXML
    public Button btn_sound;

    @FXML
    public Button btn_schwierigkeit;

    @FXML
    public Button btn_sammlung;

    /**
     * Erstellt das Menü
     */
    public TIMenuController() {
        Config.setDifficulyPreset();
        Config.setSoundPreset();
    }

    /**
     * Menübutton Schwierigkeit
     * 
     * @param event,
     *            ändert Beschriftung und Schwierigkeit
     */
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

    /**
     * Verlässt das Programm
     * 
     * @param event,
     *            schießt das Programm
     */
    @FXML
    void exitPressed(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Kehrt zurück zur Sammlung
     * 
     * @param event,
     *            Öffnet das Hauptmenü der Sammlung
     */
    @FXML
    void sammlungPressed(ActionEvent event) {
        this.setChanged();
        this.notifyObservers(MenuCodes.exitToMainGUI);
    }

    /**
     * Menübutton Sound
     * @param event, ändert Beschriftung und Sound
     */
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

    /**
     * Button startet das Spiel
     * @param event, startet das Spiel
     */
    @FXML
    void startGamePressed(ActionEvent event) {
        this.setChanged();
        this.notifyObservers(UpdateCodes.TunnelInvader.playGame);
    }

}
