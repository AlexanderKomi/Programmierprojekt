package de.hsh.amir.controller;

import common.updates.UpdateCodes;
import de.hsh.amir.logic.AmirGame;
import de.hsh.kevin.logic.Score;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AmirGameController extends Observable implements Initializable {

    public static final String   fxml           = "view/AmirGame.fxml";
    public static final int      WINNING_POINTS = 10;
    private             AmirGame game;
    private final       Score    points;
    private             boolean  initialized    = false;
    @FXML
    private             Label    pointsLabel;
    @FXML
    private             Canvas   gameCanvas;

    public AmirGameController() {
        points = new Score();
    }

    public void render(int fps) {
        if (initialized && game != null) {
            game.render();
            updatePointsLabel();
            if (spielGewonnen()) {
                this.setChanged();
                this.notifyObservers(UpdateCodes.Amir.showEndScreen);
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(event -> game.onKeyPressed(event));
        gameCanvas.setOnKeyReleased(event -> game.onKeyPressed(event));
        game        = new AmirGame(gameCanvas, points);
        initialized = true;
    }

    private boolean spielGewonnen() {
        return points.getScore() >= WINNING_POINTS;
    }

    private void updatePointsLabel() {
        Platform.runLater(() -> pointsLabel.setText("Points: " + points.getScore()));
    }
}
