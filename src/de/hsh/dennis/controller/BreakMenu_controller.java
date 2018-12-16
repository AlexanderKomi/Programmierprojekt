package de.hsh.dennis.controller;

import common.util.Logger;
import de.hsh.dennis.model.GameModel;
import de.hsh.dennis.model.KeyLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class BreakMenu_controller extends Observable implements Initializable {

    private static boolean initialized = false;

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private TextField tf_score;

    @FXML
    private Text t_banner;

    @FXML
    private Button b_replay;

    @FXML
    private Button b_main_menu;

    @FXML
    private Button b_continue;

    @FXML
    void keyInputHandler(KeyEvent event) {
        if (event.getCode() == KeyLayout.Control.ESC) {
            setChanged();
            notifyObservers("b_continue");
        }
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!initialized) {

            tf_score.setText("0");
            tf_score.textProperty().bind(GameModel.score_string);

            Logger.log("initializing DONE");
        }
    }
}
