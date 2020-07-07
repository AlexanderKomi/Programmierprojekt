package common.updates;

import common.GameContainer;
import common.util.Logger;
import de.hsh.Julian.LKEntryPoint;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class UpdateLK {

    public static void update(LKEntryPoint LKGame, Object arg, GameContainer gameContainer) {
        if (arg instanceof String) {
            String message = (String) arg;
            switch (message) {
                case UpdateCodes.LK.gameName:
                    //don't know what belongs in here
                    break;
                case UpdateCodes.LK.startGame:
                    //don't know what belongs in here
                    break;
                case MenuCodes.exitToMainGUI:
                    gameContainer.showMainMenu();
                    System.gc();                        //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                    break;
                default:
                    throw new IllegalArgumentException(Updater.unkownParsingCode + message);
            }
        } else {
            Logger.log(LKGame, arg);
        }
    }
}
