package de.hsh.kevin.controller;

import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.kevin.TIFxmlChanger;

import java.util.Observable;
import java.util.Observer;

public class TIController extends GameEntryPoint {

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

    public void render() {

    }

}
