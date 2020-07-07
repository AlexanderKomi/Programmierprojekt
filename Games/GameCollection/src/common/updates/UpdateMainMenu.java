package common.updates;

import common.GameContainer;
import common.MainMenu;
import common.util.Logger;

public class UpdateMainMenu {

    public static void update( MainMenu menu, Object arg, GameContainer gameContainer ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            if ( gameContainer.containsGame( message ) ) {
                gameContainer.setGameShown( message );
            }
            else if ( message.equals( UpdateCodes.MainMenu.shutdown ) ) {
                gameContainer.stopContainer();
            }
        }
        else {
            Logger.log( Updater.parsingErrorCode + " update(MainMenu, Object)" );
        }
    }

}
