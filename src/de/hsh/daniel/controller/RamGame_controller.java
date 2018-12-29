package de.hsh.daniel.controller;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.model.Game;
import de.hsh.daniel.model.board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

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

    @FXML
    private Canvas gameCanvas;
    @FXML
    private Button b_X;

    public void render(final int fps) {
        if (game != null) {
            if (initialized) {
                gameCanvas.getGraphicsContext2D().clearRect(0, 0, WindowConfig.window_width, WindowConfig.window_height);
                game.render(gameCanvas, fps);

                if (game.getGUIBoard().getWinner() != null) {
                    if (gameWon().equals(UpdateCodes.RAM.p1Win)) {
                        setChanged();
                        notifyObservers(UpdateCodes.RAM.p1Win);
                        Board.reset();
                        initialized = false;

                    } else if (gameWon().equals(UpdateCodes.RAM.p2Win)) {
                        setChanged();
                        notifyObservers(UpdateCodes.RAM.p2Win);
                        Board.reset();
                        initialized = false;

                    } else if (gameWon().equals(UpdateCodes.RAM.tie)) {
                        setChanged();
                        notifyObservers(UpdateCodes.RAM.tie);
                        Board.reset();
                        initialized = false;
                    }

                } else {
                    return;
                }
            }
        }
    }


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
}


