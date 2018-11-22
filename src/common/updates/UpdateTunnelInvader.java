package common.updates;

import common.GameContainer;
import common.util.Logger;
import de.hsh.kevin.controller.TIController;

public class UpdateTunnelInvader {

    public static void update(TIController tiController, Object arg, GameContainer gameContainer) {
	if (arg instanceof String) {
	    String message = (String) arg;
	    switch (message) {
	    case UpdateCodes.TunnelInvader.startGame:
		gameContainer.setGameShown(UpdateCodes.TunnelInvader.gameName);
		break;
	    case UpdateCodes.TunnelInvader.mainMenu:
		gameContainer.showMainMenu();
		break;
	    default:
		throw new IllegalArgumentException(Updater.unkownParsingCode + message);
	    }
	} else {
	    Logger.log(tiController, arg);
	}
    }

}
