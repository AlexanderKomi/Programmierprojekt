package de.hsh.dennis.controller;

import common.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;

public class LevelMenu_controller extends Observable {

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private Button b_play1;

    @FXML
    private Button b_play;

    @FXML
    private Button b_tutorial;

    @FXML
    private Button b_exit;

    @FXML
    private Button b_exit1;

    @FXML
    void button_clicked(ActionEvent event) {
        String id = getId(event);
        switch (id){
            case "b_easy":
                setChanged();
                notifyObservers(id);
                break;

            case "b_medium":
                setChanged();
                notifyObservers(id);
                break;

            case "b_hard":
                setChanged();
                notifyObservers(id);
                break;

            case "b_nightmare":
                setChanged();
                notifyObservers(id);
                break;

            case "b_back":
                setChanged();
                notifyObservers(id);
                break;

            default:
                Logger.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }
    }

    private String getId(ActionEvent event){
        return ((Node) event.getSource()).getId();
    }

}
