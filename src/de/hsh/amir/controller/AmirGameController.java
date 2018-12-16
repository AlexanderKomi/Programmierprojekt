package de.hsh.amir.controller;

import common.updates.UpdateCodes;
import de.hsh.amir.logic.AmirGame;
import de.hsh.kevin.logic.Score;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AmirGameController extends Observable implements Initializable {

    public static final String fxml = "view/AmirGame.fxml";
    private AmirGame game;
    private Score points;
    private boolean initialized = false;
    @FXML
    private Label pointsLabel;
    @FXML
    private Canvas gameCanvas;

    public AmirGameController(){
        points = new Score();
    }

    public void render(int fps) {
        if (initialized) {
            if (game != null) {
                game.render(fps);
                updateLabel();
                if(spielGewonnen()){
                    this.setChanged();
                    this.notifyObservers(UpdateCodes.Amir.mainMenu);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(event -> {
            game.onKeyPressed(event);
        });
        gameCanvas.setOnKeyReleased(event -> {
            game.onKeyPressed(event);
        });
        game = new AmirGame(gameCanvas, points);
        game.reset();
        initialized = true;
    }

    private boolean spielGewonnen() {
        int punkte = points.getScore();
        if (punkte >= 10) {
            return true;
        }
        return false;
    }

    private void updateLabel() {
        Platform.runLater(() -> pointsLabel.setText("Points: " + points.getScore()));
    }
}
