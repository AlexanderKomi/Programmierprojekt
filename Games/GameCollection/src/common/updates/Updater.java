package common.updates;

import common.GameContainer;
import common.MainMenu;
import common.engine.components.game.GameEntryPoint;
import common.util.Logger;
import de.hsh.Julian.LKEntryPoint;
import de.hsh.alexander.src.PacManController;
import de.hsh.amir.AmirEntryPoint;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGameEntryPoint;
import de.hsh.kevin.controller.TIController;

import java.util.Observable;

public final class Updater {

    private final static String unknownErrorCode  = "ATTENTION : UNKNOWN OBSERVABLE OF TYPE GAME IS NOT PARSED";
    public final static         String unkownParsingCode = "Unknown String argument: ";
    public final static         String parsingErrorCode  = "Can not parse : ";

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
        if ( o instanceof GameEntryPoint ) {
            GameEntryPoint gameEntryPoint = (GameEntryPoint) o;
            if ( gameEntryPoint instanceof PacManController ) {
                UpdatePacman.update( (PacManController) gameEntryPoint, arg, gameContainer );
            }
            else if ( gameEntryPoint instanceof AmirEntryPoint) {
                UpdateAmirsGame.update( (AmirEntryPoint) gameEntryPoint, arg, gameContainer );
            }
            else if ( gameEntryPoint instanceof RAM ) {
                UpdateRAM.update( (RAM) gameEntryPoint, arg, gameContainer );
            } else if (gameEntryPoint instanceof DennisGameEntryPoint) {
                UpdateDDOSDefender.update((DennisGameEntryPoint) gameEntryPoint, arg, gameContainer);
            }
            else if ( gameEntryPoint instanceof LKEntryPoint) {
                UpdateLK.update( (LKEntryPoint) gameEntryPoint, arg, gameContainer );
            }
            else if ( gameEntryPoint instanceof TIController ) {
                UpdateTunnelInvader.update( (TIController) gameEntryPoint, arg, gameContainer );
            }
            else {
                Logger.log( unknownErrorCode + " type : " + GameEntryPoint.class );
            }
        } else if (o instanceof MainMenu) {
            UpdateMainMenu.update( (MainMenu) o, arg, gameContainer );
        }
        else {
            Logger.log( unknownErrorCode );
            Logger.log( o, arg );
        }
    }

    public static void update( GameEntryPoint game, Object arg, GameContainer gameContainer ) {
        Logger.log( "Game is not parsed by " + Updater.class + " : " + game, arg );
    }

}
