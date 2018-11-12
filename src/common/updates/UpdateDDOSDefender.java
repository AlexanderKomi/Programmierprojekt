package common.updates;

import common.GameContainer;
import de.hsh.alexander.game.PacManController;
import de.hsh.alexander.util.Logger;

public class UpdateDDOSDefender {
    public static void update(PacManController pacManController, Object arg, GameContainer gameContainer) {
        if (arg instanceof String) {
            String message = (String) arg;
            switch (message) {
                case UpdateCodes.Dennis.exit:
                    //gameContainer.setGameShown( UpdateCodes.PacMan.gameName );
                    break;
                case UpdateCodes.Dennis.levelMenu:

                    break;
                case UpdateCodes.Dennis.error:
                    Logger.log("ERROR: UpdateDDosDefender: code \"error\"");
                    break;
                default:
                    throw new IllegalArgumentException(Updater.unkownParsingCode + message);
            }
        } else {
            Logger.log(pacManController, arg);
        }
    }
}
