package de.hsh.kevin.controller;

import common.engine.components.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.updates.UpdateCodes;
import de.hsh.kevin.TIFxmlChanger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class TIController extends Game {

    private TIMenuController gameMenu;
    private TIGameController game;
    private TIGameOverController gameOver;
	private TIFxmlChanger changer;

    public TIController(Observer o) {
	super(o, UpdateCodes.TunnelInvader.gameName);
	changer = new TIFxmlChanger(this, "res/TIMenu.fxml", new TIMenuController());
    }

    @Override
    public void update(Observable o, Object arg) {

    	//weiterleiten an den changer
		changer.changeFxml(o, (String) arg);

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
