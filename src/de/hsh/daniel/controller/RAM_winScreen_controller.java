package de.hsh.daniel.controller;

import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.model.board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Observable;

public class RAM_winScreen_controller extends Observable {

    public static final String fxmlP1Win = "view/p1Win.fxml";
    public static final String fxmlP2Win = "view/p2Win.fxml";
    public static final String fxmlTie = "view/tie.fxml";

    @FXML
    private Button replay;

    @FXML
    private Button quit;

    @FXML
    public void button_click(ActionEvent event) {
        String id = getId(event);
        Logger.log(this.getClass() + ": button clicked: " + event);
        switch (id) {
            case "b_replay":
                setChanged();
                notifyObservers(UpdateCodes.RAM.mainMenu);
                break;
            case "b_quit":
                setChanged();
                notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
                break;

            default:
                Logger.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }

    }

    private String getId(ActionEvent event) {
        return ((Node) event.getSource()).getId();
    }
}
