package de.hsh.kevin;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.updates.UpdateTunnelInvader;
import de.hsh.dennis.controller.MainMenu_controller;
import de.hsh.kevin.controller.TIGameController;
import de.hsh.kevin.controller.TIGameOverController;
import de.hsh.kevin.controller.TIMenuController;

import java.util.Observable;
import java.util.Observer;

public class TIFxmlChanger extends FxmlChanger {

    public TIFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
	super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
	if (o instanceof TIMenuController) {
	    handle_TIMenuController(msg);
	} else if (o instanceof TIGameController) {
	    handle_TIGameController(msg);
	} else if (o instanceof TIGameOverController) {
	    handle_TIGameOverController(msg);
	}
    }

    private void handle_TIMenuController(String msg) {
	switch (msg) {
	case UpdateCodes.DefaultCodes.exitToMainGUI:
	    ((GameEntryPoint) getFxModul()).exitToMainGUI();
	    break;
	case UpdateCodes.TunnelInvader.startGame:
	    // TODO
	    // changeScene(TIGameController.fxml, new TIGameController();
	    break;
	}
    }

    private void handle_TIGameController(String msg) {
	switch (msg) {
	case UpdateCodes.TunnelInvader.gameOver:
	    // TODO
	    changeScene(TIGameOverController.fxml, new TIGameOverController());
	    break;
	}
    }

    private void handle_TIGameOverController(String msg) {
	switch (msg) {
	case UpdateCodes.DefaultCodes.exitToMainGUI:
	    ((GameEntryPoint) getFxModul()).exitToMainGUI();
	    break;
	case UpdateCodes.TunnelInvader.startGame:
	    // TODO
	    // changeScene(TIGameController.fxml, new TIGameController();
	    break;
	case UpdateCodes.TunnelInvader.gameMenu:
	    changeScene(TIMenuController.fxml, new TIMenuController());
	    break;
	}
    }
}
