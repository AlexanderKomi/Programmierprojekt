package de.hsh.kevin.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import common.updates.UpdateCodes;
import de.hsh.kevin.logic.GameField;
import de.hsh.kevin.logic.Score;
import de.hsh.kevin.logic.Config;

/**
 * Erstellt SpielController
 * 
 * @author Kevin
 *
 */
public class TIGameController extends Observable implements Initializable {

    public static final String fxml = "TIGame.fxml";
    private boolean initialized = false;
    private boolean gameOver;

    private GameField gameField;
    private Score score;

    @FXML
    public Canvas gameCanvas;

    @FXML
    public Button btn_score;

    @FXML
    public Label lbl_leben;

    @FXML
    public Label lbl_score;

    /**
     * Erstellt den SpielController mit einem Score
     */
    public TIGameController(Score score) {
        this.score = score;
    }

    /**
     * Button zum Testen des GameOverScreens (derzeit deaktiviert)
     * 
     * @param event
     */
    @FXML
    void scorePressed(ActionEvent event) {
        this.setChanged();
        this.notifyObservers(UpdateCodes.TunnelInvader.gameOver);
    }

    /**
     * Initialisiert den Controller und das Spiel
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (initialized) {
            return;
        }
        this.btn_score.setVisible(false);
        this.gameCanvas.setFocusTraversable(true);

        double widthFactor = Config.getDifficultyFactor();
        this.gameCanvas.setWidth(gameCanvas.getWidth() * widthFactor);

        gameField = new GameField(this.gameCanvas, score);

        gameField.clearCanvas(gameCanvas);

        this.gameCanvas.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.NUMPAD0) {
                this.gameField.activateProjectileSpawn();
                this.gameField.player.switchFiring();
            } else {
                this.gameField.movePlayer(keyEvent);
            }
        });

        this.gameCanvas.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.NUMPAD0) {
                this.gameField.deactivateProjectileSpawn();
                this.gameField.setPlayerIdle();
            } else {
                this.gameField.movePlayer(keyEvent);

            }
        });

        initialized = true;
    }

    /**
     * Führt die nächste Spieliteration aus
     */
    public void render() {
        if (!initialized) {
            return;
        }
        checkGameOver();

        this.gameField.game(gameCanvas);

        updateLbl_leben();
        updateLbl_score();
    }

    /**
     * Prüft ob das Spiel verloren wurde
     */
    private void checkGameOver() {
        if (!gameOver && this.gameField.getLeben() <= 0) {
            gameOver = true;
            this.setChanged();
            this.notifyObservers(UpdateCodes.TunnelInvader.gameOver);
        }

    }

    /**
     * Ändert den Text des Leben Labels
     */
    private void updateLbl_leben() {
        Platform.runLater(() -> lbl_leben.setText("Leben: " + gameField.getLeben()));
    }

    /**
     * Ändert den Text des Score Labels
     */
    private void updateLbl_score() {
        Platform.runLater(() -> lbl_score.setText("Score: " + gameField.getScore()));
    }
}
