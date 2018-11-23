package common.updates;

import common.GameContainer;
import common.util.Logger;
import de.hsh.amir.AmirsGame;
import de.hsh.dennis.DennisGame;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class UpdateAmirsGame {

    public static void update(AmirsGame amirGame, Object arg, GameContainer gameContainer) {
        if (arg instanceof String) {
            String message = (String) arg;
            switch (message) {
                case UpdateCodes.Amir.gameName:
                    //don't know what belongs in here
                    break;
                case UpdateCodes.Amir.startGame:
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
            Logger.log(amirGame, arg);
        }
    }
}
