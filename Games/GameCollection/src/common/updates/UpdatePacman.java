package common.updates;

import common.GameContainer;
import common.util.Logger;
import de.hsh.alexander.src.PacManController;

public final class UpdatePacman {

    public static void update( PacManController pacManController, final Object arg, GameContainer gameContainer ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.PacMan.startGame:
                    gameContainer.setGameShown( UpdateCodes.PacMan.gameName );
                    break;
                case MenuCodes.exitToMainGUI:
                    gameContainer.showMainMenu();
                    break;
                default:
                    throw new IllegalArgumentException( Updater.unkownParsingCode + message );
            }
        }
        else {
            Logger.log( UpdatePacman.class + " : Unknown Arguments :  " + pacManController, arg );
        }
    }


}
