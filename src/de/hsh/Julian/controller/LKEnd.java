package de.hsh.Julian.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Observable;

public class LKEnd extends Observable {

    public static final String fxml = "Endscreen.fxml";

    @FXML
    private Button b_backtomenu;

    @FXML
    private Button b_retry;
}