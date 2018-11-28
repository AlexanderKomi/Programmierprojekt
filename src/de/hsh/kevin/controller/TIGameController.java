package de.hsh.kevin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import common.updates.UpdateCodes;

public class TIGameController extends Observable implements Initializable {

    public static final String fxml = "res/TIGame.fxml";
    
    @FXML
    public Canvas gameCanvas;
    
    @FXML
    public Button btn_score;
    
    @FXML
    public Label lbl_leben;
    
    @FXML
    public Label lbl_score;
    
    private double factor = 1;
    
    public TIGameController(){
	gameCanvas.setWidth(factor * gameCanvas.getWidth());
    }
    
    public void render() {
		
    }

    @FXML
    void initialize() {

    }
    
    // Vorläufiger Button zum Testen der Funktionalität
    @FXML
    void scorePressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.gameOver);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	gameCanvas.setFocusTraversable(true);
    }
}
