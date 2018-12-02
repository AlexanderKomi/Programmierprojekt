package de.hsh.kevin.controller;


import common.engine.components.menu.GameMenu;
import common.updates.UpdateCodes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Observer;

public class TIGameOverController extends GameMenu {

    public static AnchorPane gameOverPane;

     public static final String fxml = "res/TIGameOver.fxml";
    
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


    @FXML
    void menuPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.gameMenu);    }

    @FXML
    void nochamalPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.playGame);
    }

    @FXML
    void sammlungPressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.gameMenu);
	this.notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
    }

}
