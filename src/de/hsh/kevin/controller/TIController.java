package de.hsh.kevin.controller;

import common.engine.components.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.updates.UpdateCodes;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class TIController extends Game implements Initializable {

    private TIMenuController gameMenu;
    private TIGameController game;
    private TIGameOverController gameOver;

    public TIController(Observer o) {
	super(o, UpdateCodes.TunnelInvader.gameName);
	if (!loadMenuFXML()) { // In case FXML could not be loaded, a default pane is set
		//this.setGameContentPane(new Pane());
	}

	 if (!loadGameOverFXML()) {
	 TIGameOverController.gameOverPane = new AnchorPane();
	 }

	 //loadGameFXML();
    }

    private boolean loadMenuFXML() {
	try {
	    this.gameMenu = new TIMenuController(this);
	    AnchorPane node = FXMLLoader.load(TIMenuController.class.getResource("../res/TIMenu.fxml"));
	    this.gameMenu.setMenuPane(node);
	    this.gameMenu.addObserver(this);
		//this.setGameContentPane(this.gameMenu.getMenuPane());
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private boolean loadGameFXML() {
	try {
	    this.game = new TIGameController(this);
	    TIGameController.gameBox = FXMLLoader.load(TIGameController.class.getResource(TIGameController.fxml));
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private boolean loadGameOverFXML() {
	try {
	    this.gameOver = new TIGameOverController(this);
	    TIGameOverController.gameOverPane = FXMLLoader
		    .load(TIGameOverController.class.getResource(TIGameOverController.fxml));
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(Observable o, Object arg) {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(KeyEventManager keyEventManager, Object arg) {

    }

    @Override
    public void update(MouseEventManager mouseEventManagerManager, Object arg) {

    }

    public void render() {

    }

}
