package de.hsh.alexander;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;
import java.util.Observer;

public class PacManController extends GameEntryPoint {

    private final de.hsh.alexander.PacManFxmlChanger changer;
    private       de.hsh.alexander.PacManGame        game;

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        this.game = new de.hsh.alexander.PacManGame();
        game.addObserver( this );
        changer = new de.hsh.alexander.PacManFxmlChanger( this,
                                                          de.hsh.alexander.PacManMenu.fxml,
                                                          new de.hsh.alexander.PacManMenu() );
    }

    /**
     * PacManMenu sends a notification to change the fxml.
     */
    @Override
    public void update( Observable o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.PacMan.startGame:
                    this.game = new de.hsh.alexander.PacManGame();
                    game.addObserver( this );
                    changer.changeFxml( this.game, UpdateCodes.PacMan.startGame );
                    break;
                case UpdateCodes.PacMan.mainMenu:
                    this.game = new de.hsh.alexander.PacManGame();
                    game.addObserver( this );
                    changer.changeFxml( this.game, UpdateCodes.PacMan.startGame );
                    exitToMainGUI();
                    break;
                case UpdateCodes.PacMan.showEndScreen:
                    changer.changeFxml( new de.hsh.alexander.PacManEndScreen(), UpdateCodes.PacMan.showEndScreen );
                    break;
                case UpdateCodes.PacMan.repeatGame:
                    this.game = new de.hsh.alexander.PacManGame();
                    changer.changeFxml( this.game, UpdateCodes.PacMan.startGame );
                    break;
                default:
                    logParsingError( o, arg );
                    break;
            }
            this.notifyObservers( message );
        }
        else {
            logParsingError( o, arg );
        }
    }

    private static void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacManController: update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    @Override
    public void render( int fps ) {
        if ( game != null ) {
            if ( game.initialized ) {
                game.render( fps );
            }
        }
    }


}
