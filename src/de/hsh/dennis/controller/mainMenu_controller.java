package de.hsh.dennis.controller;

import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class mainMenu_controller {

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private ImageView pic_header;

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
            case "b_play":
                Logger.log("button_clicked Aufruf mit b_play.");
                break;
            case "b_tutorial":
                Logger.log("button_clicked Aufruf mit b_tutorial.");
                break;
            case "b_exit":
                Logger.log("button_clicked Aufruf mit b_exit.");
                break;
            default:
                Logger.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }
    }

    private String getId(ActionEvent event){
        return ((Node) event.getSource()).getId();
    }

}
