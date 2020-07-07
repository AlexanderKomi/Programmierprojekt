package de.hsh.Julian.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import java.util.Observable;

public class SpielBildschirm_controller extends Observable {

    public static final String fxml = "SpielBildschirm.fxml";

    @FXML
    private Canvas canvas;

    public void passCanvas() {
        setChanged();
        notifyObservers(canvas);
    }
}
