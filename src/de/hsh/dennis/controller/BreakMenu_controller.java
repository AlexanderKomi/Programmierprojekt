package de.hsh.dennis.controller;

import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;

public class BreakMenu_controller extends Observable {

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private TextField tf_score;

    @FXML
    private Button b_play;

    @FXML
    private Button b_tutorial;

    @FXML
    private Button b_exit;

    @FXML
    void button_clicked(ActionEvent event) {
        String id = getId(event);
        switch (id){
            case "b_replay":
                setChanged();
                notifyObservers(id);
                break;

            case "b_main_menu":
                setChanged();
                notifyObservers(id);
                break;

            case "b_continue":
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
