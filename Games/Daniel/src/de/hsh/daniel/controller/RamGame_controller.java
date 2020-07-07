package de.hsh.daniel.controller;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * This is the game fxml-controller started
 */
public class RamGame_controller extends Observable implements Initializable {

    public static final String fxml = "view/RAMGame.fxml";

    private Game game;
    private boolean initialized = false;
    private int p1Score, p2Score;

    @FXML
    private Canvas gameCanvas;
    @FXML
    private Button b_X;
    @FXML
    private Label p1_score, p2_score;

    public void render(final int fps) {
        if (game != null) {
            if (initialized) {
                gameCanvas.getGraphicsContext2D().clearRect(0, 0, WindowConfig.window_width, WindowConfig.window_height);
                game.render(gameCanvas, fps);
                update_p1Score();
                update_p2Score();

                if (game.getGUIBoard().getWinner() != null) {
                    handleUpdateCodes();
                }


            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logger.log(this.getClass() + ": initialized");

        gameCanvas.setFocusTraversable(true);
        game = new Game();
        game.initialize(gameCanvas);
        initialized = true;
    }


    @FXML
    public void button_click(ActionEvent event) {
        String id = getId(event);
        Logger.log(this.getClass() + ": button clicked: " + event);
        switch (id) {
            case "b_X":
                setChanged();
                notifyObservers(UpdateCodes.RAM.mainMenu);
                break;
            default:
                Logger.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }
    }

    private String getId(ActionEvent event) {
        return ((Node) event.getSource()).getId();
    }

    public void handleUpdateCodes() {
        if (gameWon().equals(UpdateCodes.RAM.p1Win)) {
            setChanged();
            notifyObservers(UpdateCodes.RAM.p1Win);
            initialized = false;

        } else if (gameWon().equals(UpdateCodes.RAM.p2Win)) {
            setChanged();
            notifyObservers(UpdateCodes.RAM.p2Win);
            initialized = false;

        } else if (gameWon().equals(UpdateCodes.RAM.tie)) {
            setChanged();
            notifyObservers(UpdateCodes.RAM.tie);
            initialized = false;
        } else {
            return;
        }
    }

    /**
     * Checks which player won
     *
     * @return UpdateCode for FXML Changer
     */
    private String gameWon() {
        if (game.getGUIBoard().getWinner().getName().equals("P1")) {
            return UpdateCodes.RAM.p1Win;
        } else if (game.getGUIBoard().getWinner().getName().equals("P2")) {
            return UpdateCodes.RAM.p2Win;
        } else if (game.getGUIBoard().getWinner().getName().equals("BOTH")) {
            return UpdateCodes.RAM.tie;
        } else {
            return null;
        }

    }

    private void update_p1Score() {
        Platform.runLater(() -> p1_score.setText("P1: " + game.getGUIBoard().getP1Points()));
        if (game.getGUIBoard().isP1Turn()) {
            p1_score.setDisable(false);
        } else {
            p1_score.setDisable(true);
        }
    }

    private void update_p2Score() {
        Platform.runLater(() -> p2_score.setText("P2: " + game.getGUIBoard().getP2Points()));
        if (game.getGUIBoard().isP2Turn()) {
            p2_score.setDisable(false);
        } else {
            p2_score.setDisable(true);
        }
    }
}




