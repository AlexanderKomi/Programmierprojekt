package de.hsh.kevin.controller;

import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.kevin.TIFxmlChanger;

import java.util.Observable;
import java.util.Observer;

public class TIController extends GameEntryPoint {

    private TIFxmlChanger changer;
    private TIGameController game;

    public TIController(Observer o) {
	super(o, UpdateCodes.TunnelInvader.gameName);
	changer = new TIFxmlChanger(this, TIMenuController.fxml, new TIMenuController());
    }

    @Override
    public void update(Observable o, Object arg) {
	if (arg instanceof String) {
	    String msg = (String) arg;

	    switch (msg) {
	    case UpdateCodes.TunnelInvader.playGame:
		game = new TIGameController();
		changer.changeGameFxml(o, game);
		break;
	    default:
		changer.changeFxml(o, (String) arg);
		break;
	    }

	}
    }

    public void render() {
	if (game != null) {
	    game.render();
	}
    }

}
