package de.hsh.dennis.controller;

import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainMenu_controller extends Observable implements Initializable {

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
                setChanged();
                notifyObservers(id);
                break;

            case "b_tutorial":
                setChanged();
                notifyObservers(id);
                break;

            case "b_exit":
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

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
