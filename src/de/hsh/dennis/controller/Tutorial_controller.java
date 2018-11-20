package de.hsh.dennis.controller;

import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Observable;

public class Tutorial_controller extends Observable {

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private ImageView imageView;

    @FXML
    private Button b_left;

    @FXML
    private Button b_right;

    @FXML
    private Button b_exit;

    @FXML
    private TextArea ta_left;

    @FXML
    private TextArea ta_right;

    @FXML
    private Text t_subject;

    @FXML
    void button_clicked(ActionEvent event) {
        String id = getId(event);
        switch (id){
            case "b_left":
                setChanged();
                notifyObservers(id);
                break;

            case "b_right":
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

}
