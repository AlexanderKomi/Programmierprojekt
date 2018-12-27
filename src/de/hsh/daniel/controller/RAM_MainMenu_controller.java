package de.hsh.daniel.controller;

import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.model.board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Observable;

public class RAM_MainMenu_controller extends Observable {

    public static String fxml = "view/ramMenuNew.fxml";

    @FXML
    private Button b_play;

    @FXML
    private Button twelvePairs;
    @FXML
    private Button sixteenPairs;
    @FXML
    private Button twentyPairs;

    @FXML
    private Button b_back;


    @FXML
    public void button_click(ActionEvent event) {
        String id = getId(event);
        Logger.log(this.getClass() + ": button clicked: " + event);
        switch (id) {
            case "b_play":
                setChanged();
                if(Board.numberOfPairs == 0){
                    Board.numberOfPairs = 6;
                }
                notifyObservers(UpdateCodes.RAM.startGame);
                break;

            case "sixPairs":
                setChanged();
                Board.numberOfPairs = 6;
                notifyObservers(UpdateCodes.RAM.fieldSize);
                break;

            case "eightPairs":
                setChanged();
                Board.numberOfPairs = 8;
                notifyObservers(UpdateCodes.RAM.fieldSize);
                break;

            case "tenPairs":
                setChanged();
                Board.numberOfPairs = 10;
                notifyObservers(UpdateCodes.RAM.fieldSize);
                break;

            case "b_back":
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
