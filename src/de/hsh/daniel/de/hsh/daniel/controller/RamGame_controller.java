package de.hsh.daniel.de.hsh.daniel.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import java.util.Observable;

public class RamGame_controller extends Observable {

    @FXML
    private Canvas gameCanvas;

    public void passCanvas(){
        setChanged();
        notifyObservers(gameCanvas);
    }

}

