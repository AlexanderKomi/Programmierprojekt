package de.hsh.dennis.controller;

import common.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Level_controller extends Observable implements Initializable {

    private static boolean initialized = false;

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private Canvas canvas;

    @FXML
    void key_pressed(KeyEvent event) {
        setChanged();
        notifyObservers(event.getCode());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!initialized) {
            Logger.log("initializing ...");
            this.canvas.setFocusTraversable(true);  //!!!must have!!! for a working canvas
            Logger.log("initializing DONE");
        }
    }

    public void passCanvas() {
        setChanged();
        notifyObservers(canvas);
    }
}
