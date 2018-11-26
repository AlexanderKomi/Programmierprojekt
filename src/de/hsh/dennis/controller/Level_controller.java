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

//TODO: outsorce for mvc!
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

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!initialized) {
            Logger.log("initializing ...");
            this.canvas.setFocusTraversable(true);  //!!!must have!!!
            this.canvas.setOnKeyPressed(this::heyInputHandler);
            Logger.log("initializing DONE");
        }
    }

    private void heyInputHandler(KeyEvent event) {
        Logger.log(event.getCode());

        switch (event.getCode()) {
            case ESCAPE:
                setChanged();
                notifyObservers("BREAK");
                break;
            case P:
                setChanged();
                notifyObservers("BREAK");
                break;
            case W:

                break;
            case A:

                break;
            case S:

                break;
            case D:

                break;
            case UP:

                break;
            case DOWN:

                break;
            case LEFT:

                break;
            case RIGHT:

                break;

        }

    }

}
