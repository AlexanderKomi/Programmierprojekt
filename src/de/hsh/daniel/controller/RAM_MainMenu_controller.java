package de.hsh.daniel.controller;

import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;

import java.util.Observable;

public class RAM_MainMenu_controller extends Observable {

    public static String fxml = "view/ram_Menu.fxml";

    @FXML
    private Button b_play;
    @FXML
    private MenuButton mb_level;

    @FXML
    private Button b_back;


    @FXML
    public void button_click(ActionEvent event) {
        String id = getId(event);
        Logger.log(this.getClass()+": button clicked: " + event);
        switch (id){
            case "b_play":
                setChanged();
                notifyObservers( UpdateCodes.RAM.startGame );
                break;

            case "mb_level":
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
