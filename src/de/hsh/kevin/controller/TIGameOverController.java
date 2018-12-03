package de.hsh.kevin.controller;

import common.engine.components.menu.GameMenu;
import common.updates.UpdateCodes;
import de.hsh.kevin.logic.Score;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
public class TIGameOverController extends GameMenu {

    public static AnchorPane gameOverPane;

    public static final String fxml = "res/TIGameOver.fxml";
    private Score score;

    @FXML
    public Button btn_start;

    @FXML
    public Button btn_menu;

    @FXML
    public Button btn_sammlung;

    @FXML
    public Label lbl_score;

    public TIGameOverController(Score score) {
	this.score = score;
    }

    public void setScore() {
	Platform.runLater(() -> lbl_score.setText("Score: " + score.getScore()));
    }

    @FXML
    void menuPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.gameMenu);
    }

    @FXML
    void nochamalPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.playGame);
    }

    @FXML
    void sammlungPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.gameMenu);
	this.notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
    }

}
