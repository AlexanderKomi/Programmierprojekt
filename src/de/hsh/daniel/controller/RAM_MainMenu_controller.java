package de.hsh.daniel.controller;

import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.model.BoardFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.util.Observable;

public class RAM_MainMenu_controller extends Observable {

    public static String fxml = "view/ram_Menu.fxml";

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
                if(BoardFactory.getBoardPairs() != 0 ) {
                    int pairs = BoardFactory.getBoardPairs();
                    BoardFactory.setBoardPairs(pairs);
                } else {
                    BoardFactory.setBoardPairs(6);
                }
                notifyObservers(UpdateCodes.RAM.startGame);
                break;

            case "twelvePairs":
                notifyObservers(UpdateCodes.RAM.fieldSize);
                BoardFactory.setBoardPairs(6);
                notifyObservers(UpdateCodes.RAM.startGame);
                break;

            case "sixteenPairs":
                setChanged();
                notifyObservers(UpdateCodes.RAM.fieldSize);
                BoardFactory.setBoardPairs(8);
                break;

            case "twentyPairs":
                setChanged();
                notifyObservers(UpdateCodes.RAM.fieldSize);
                BoardFactory.setBoardPairs(10);
                break;

            case "b_back":
                setChanged();
                notifyObservers(id);
                break;

            default:
                Logger.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }

    }

    private String getId(ActionEvent event) {
        return ((Node) event.getSource()).getId();
    }

}
