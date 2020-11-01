package de.hsh.daniel.controller;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.logger.Logger;
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
import java.util.Objects;
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
                game.render(gameCanvas);
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
        Logger.INSTANCE.log(this.getClass() + ": initialized");

        gameCanvas.setFocusTraversable(true);
        game = new Game(gameCanvas);
        initialized = true;
    }


    @FXML
    public void button_click(ActionEvent event) {
        String id = getId(event);
        Logger.INSTANCE.log(this.getClass() + ": button clicked: " + event);
        if ("b_X".equals(id)) {
            setChanged();
            notifyObservers(UpdateCodes.RAM.mainMenu);
        } else {
            Logger.INSTANCE.log("ERROR : button_clicked Aufruf mit default Ergebniss!");
        }
    }

    private String getId(ActionEvent event) {
        return ((Node) event.getSource()).getId();
    }

    public void handleUpdateCodes() {
        switch (Objects.requireNonNull(gameWon())) {
            case UpdateCodes.RAM.p1Win:
                setChanged();
                notifyObservers(UpdateCodes.RAM.p1Win);
                initialized = false;
                break;
            case UpdateCodes.RAM.p2Win:
                setChanged();
                notifyObservers(UpdateCodes.RAM.p2Win);
                initialized = false;
                break;
            case UpdateCodes.RAM.tie:
                setChanged();
                notifyObservers(UpdateCodes.RAM.tie);
                initialized = false;
                break;
        }
    }

    /**
     * Checks which player won
     *
     * @return UpdateCode for FXML Changer
     */
    private String gameWon() {
        if (Objects.requireNonNull(game.getGUIBoard().getWinner()).getName().equals("P1")) {
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
        p1_score.setDisable(!game.getGUIBoard().isP1Turn());
    }

    private void update_p2Score() {
        Platform.runLater(() -> p2_score.setText("P2: " + game.getGUIBoard().getP2Points()));
        p2_score.setDisable(!game.getGUIBoard().isP2Turn());
    }
}




