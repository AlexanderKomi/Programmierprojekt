package de.hsh.kevin.controller;

import common.engine.components.menu.Menu;
import common.updates.MenuCodes;
import common.updates.UpdateCodes;
import de.hsh.kevin.logic.Score;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;

/**
 * Erstellt den GameOverScreen
 * @author Kevin
 *
 */
public class TIGameOverController extends Menu {

    public static AnchorPane gameOverPane;

    public static final String fxml = "TIGameOver.fxml";
    private Score score;

    @FXML
    public Button btn_start;

    @FXML
    public Button btn_menu;

    @FXML
    public Button btn_sammlung;

    @FXML
    public Label lbl_score;

    /**
     * Erstellt das GameOverMenü mit Angabe der Scores
     * @param score
     */
    public TIGameOverController(Score score) {
        this.score = score;
    }

    /**
     * Setzt das Label des Scores
     */
    public void setScore() {
        Platform.runLater(() -> lbl_score.setText("Score: " + score.getScore()));
    }

    /**
     * Durch Buttonklick kehrt ins Menü zurück
     * @param event
     */
    @FXML
    void menuPressed(ActionEvent event) {
        this.setChanged();
        this.notifyObservers(UpdateCodes.TunnelInvader.gameMenu);
    }

    /**
     * Durch Buttonklick startet das Spiel erneut
     * @param event
     */
    @FXML
    void nochamalPressed(ActionEvent event) {
        this.setChanged();
        this.notifyObservers(UpdateCodes.TunnelInvader.playGame);
    }

    /**
     * Durch Buttonklick kehrt ins Hauptmenü der Sammlung zurück
     * @param event
     */
    @FXML
    void sammlungPressed(ActionEvent event) {
        this.setChanged();
        this.notifyObservers(UpdateCodes.TunnelInvader.gameMenu);
        this.notifyObservers(MenuCodes.exitToMainGUI);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
