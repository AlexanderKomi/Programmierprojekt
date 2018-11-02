package common;

import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.alexander.util.Logger;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.KevinGame;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.util.Observable;

public class Updater {


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
            if ( game instanceof PacManCoop ) {
                update( (PacManCoop) game, arg, gameContainer );
            }
            else if ( game instanceof AmirsGame ) {
                update( (AmirsGame) game, arg, gameContainer );
            }
            else if ( game instanceof RAM ) {
                update( (RAM) game, arg, gameContainer );
            }
            else if ( game instanceof DennisGame ) {
                update( (DennisGame) game, arg, gameContainer );
            }
            else if ( game instanceof Leertastenklatsche ) {
                update( (Leertastenklatsche) game, arg, gameContainer );
            }
            else if ( game instanceof KevinGame ) {
                update( (KevinGame) game, arg, gameContainer );
            }
            Logger.log( "ATTENTION : UNKNOWN OBSERVABLE OF TYPE GAME IS NOT PARSED" );
            Logger.log( o, arg );
        }
        else if ( o instanceof MainMenu ) {
            update( (MainMenu) o, arg, gameContainer );
        }
        else {
            Logger.log( "ATTENTION : UNKNOWN OBSERVABLE TYPE IS NOT PARSED" );
            Logger.log( o, arg );
        }
    }

    public static void update( MainMenu menu, Object arg, GameContainer gameContainer ) {
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
        else if ( arg instanceof String ) {
            String message = (String) arg;
            if ( gameContainer.containsGame( message ) ) {
                gameContainer.setGameShown( message );
            }
        }
        else {
            Logger.log( "Can not parse : update(MainMenu, Object)" );
        }
        Logger.log( menu, arg );
    }

    public static void update( PacManCoop pacManCoop, Object arg, GameContainer gameContainer ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case "Start Game":
                    gameContainer.setGameShown( "Pacman Coop" );
                    break;
                case "Mainmenu":
                    gameContainer.showMainMenu();
                    break;
                default:
                    throw new IllegalArgumentException( "Unknown String argument" );
            }
        }
        else {
            Logger.log( pacManCoop, arg );
        }
    }

    public static void update( AmirsGame game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( RAM game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( DennisGame game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( Leertastenklatsche game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

    public static void update( KevinGame game, Object arg, GameContainer gameContainer ) {
        Logger.log( game, arg );
    }

}
