package common.updates;

import common.GameContainer;
import common.MainMenu;
import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.game.PacManController;
import de.hsh.alexander.util.Logger;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.KevinGame;
import de.hsh.kevin.TIController;

import java.util.Observable;

public class Updater {

    final static String unknownErrorCode  = "ATTENTION : UNKNOWN OBSERVABLE OF TYPE GAME IS NOT PARSED";
    final static String unkownParsingCode = "Unknown String argument: ";
    final static String parsingErrorCode  = "Can not parse : ";

    /**
     * Cast observables to the correct type, and call the correct method.<br>
     * Why?<br>
     * When "notifyObservers()" is called, the argument is always casted to observable.<br>
     * This is why this method is necessary.<br>
     *
     * @param o
     *         The observable notifying this observer
     */
    public static void update( Observable o, Object arg, GameContainer gameContainer ) {
        if ( o instanceof Game ) {
            Game game = (Game) o;
            if ( game instanceof PacManController ) {
                UpdatePacman.update( (PacManController) game, arg, gameContainer );
            }
            else if ( game instanceof AmirsGame ) {
                update( (AmirsGame) game, arg, gameContainer );
            }
            else if ( game instanceof RAM ) {
                update( (RAM) game, arg, gameContainer );
            }
            else if ( game instanceof DennisGame ) {
                UpdateDDOSDefender.update( (DennisGame) game, arg, gameContainer );
            }
            else if ( game instanceof Leertastenklatsche ) {
                update( (Leertastenklatsche) game, arg, gameContainer );
            }
            else if ( game instanceof TIController ) {
                update( (TIController) game, arg, gameContainer );
            }
            else {
                Logger.log( unknownErrorCode + " type : " + Game.class );
            }
        }
        else if ( o instanceof MainMenu ) {
            UpdateMainMenu.update( (MainMenu) o, arg, gameContainer );
        }
        else {
            Logger.log( unknownErrorCode );
            Logger.log( o, arg );
        }
    }

    public static void update( AmirsGame game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( RAM game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( DennisGame game, Object arg, GameContainer gameContainer ) { Logger.log( game, arg );
    }

    public static void update( Leertastenklatsche game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( TIController tiController, Object arg, GameContainer gameContainer ) {
        Logger.log( tiController, arg );
    }

}
