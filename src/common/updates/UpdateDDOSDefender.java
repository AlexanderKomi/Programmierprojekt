package common.updates;

import common.GameContainer;
import common.util.Logger;
import de.hsh.dennis.DennisGameEntryPoint;

public class UpdateDDOSDefender {
    public static void update(DennisGameEntryPoint dennisGameEntryPoint, Object arg, GameContainer gameContainer) {
        if (arg instanceof String) {
            String message = (String) arg;
            switch (message) {
                case UpdateCodes.Dennis.gameName:
                    //don't know what belongs in here
                    break;
                case UpdateCodes.Dennis.gameReady:
                    //don't know what belongs in here
                    break;
                case UpdateCodes.DefaultCodes.exitToMainGUI:
                    gameContainer.showMainMenu();
                    System.gc();                        //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                    break;
                default:
                    throw new IllegalArgumentException(Updater.unkownParsingCode + message);
            }
        } else {
            Logger.log(dennisGameEntryPoint, arg);
        }
    }
}
