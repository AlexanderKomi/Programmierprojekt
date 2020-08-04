package de.hsh.Julian.controller;

import common.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Observable;

public class LKEnd extends Observable {

    public static final String fxml = "Endscreen.fxml";

    @FXML
    private Button b_backtomenu;

    @FXML
    private Button b_retry;

    @FXML
    void button_clicked(ActionEvent event) {
        String id = getId(event);
        switch (id){
            case "b_backtomenu":
                setChanged();
                notifyObservers(id);
                break;

            case "b_retry":
                setChanged();
                notifyObservers(id);
                break;
            default:
                Logger.INSTANCE.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }
    }
    private String getId(ActionEvent event){
        return ((Node) event.getSource()).getId();
    }
}
