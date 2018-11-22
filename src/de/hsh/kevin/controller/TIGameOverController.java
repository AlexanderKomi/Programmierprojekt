package de.hsh.kevin.controller;

import common.engine.game.GameMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class TIGameOverController extends GameMenu {

    public static AnchorPane gameOverPane;

     public static final String fxml = "../res/TIGameOver.fxml";
    
    @FXML
    public Button btn_start;
    
    @FXML
    public Button btn_menu;
    
    @FXML
    public Button btn_sammlung;
    
    @FXML
    public Label lbl_score;
    

    public TIGameOverController() {
    }

    public TIGameOverController(Observer observer) {
	super(observer);

    }

    @Override
    protected Pane initMenuPane() {
	return new Pane();
    }

    @FXML
    void menuPressed(ActionEvent event) {

    }

    @FXML
    void nochamalPressed(ActionEvent event) {

    }

    @FXML
    void sammlungPressed(ActionEvent event) {

    }

}