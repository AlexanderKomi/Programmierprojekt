package common.updates;

import common.GameContainer;
import common.MainMenu;
import de.hsh.alexander.util.Logger;

public class UpdateMainMenu {

    public static void update( MainMenu menu, Object arg, GameContainer gameContainer ) {
        /*
        if ( arg instanceof ActionEvent ) {
            ActionEvent event = (ActionEvent) arg;
            if ( event.getSource() instanceof Button ) {
                Button button   = (Button) event.getSource();
                String gameName = button.getText();
                Games  games    = gameContainer.getGames();
                Game   g        = games.get( gameName );
                gameContainer.setGameShown( g );
                gameContainer.getStage().setTitle( gameName );
                Logger.log( "Game set : " + gameName );
            }
        }
        else */
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
        Logger.log( menu, arg );
    }

}
