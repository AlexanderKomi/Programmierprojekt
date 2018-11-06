package common.updates;

import common.GameContainer;
import de.hsh.alexander.game.PacManController;
import de.hsh.alexander.util.Logger;

public class UpdatePacman {

    public static void update( PacManController pacManController, Object arg, GameContainer gameContainer ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.PacMan.startGame:
                    gameContainer.setGameShown( UpdateCodes.PacMan.gameName );
                    break;
                case UpdateCodes.PacMan.mainMenu:
                    gameContainer.showMainMenu();
                    break;
                default:
                    throw new IllegalArgumentException( Updater.unkownParsingCode + message );
            }
        }
        else {
            Logger.log( pacManController, arg );
        }
    }


}
