package de.hsh.alexander;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;
import java.util.Observer;

public class PacManController extends GameEntryPoint {

    private PacManFxmlChanger changer;
    private PacManGame game;

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        this.game = new PacManGame();
        this.changer = new PacManFxmlChanger( this, PacManMenu.fxml, new PacManMenu() );
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof PacManMenu ) {
            update( (PacManMenu) o, arg );
        }
        else {
            logParsingError( o, arg );
        }
    }

    /**
     * PacManMenu sends a notification to change the fxml.
     */
    private void update( PacManMenu o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.PacMan.startGame:
                    changer.changeFxml( this.game, UpdateCodes.PacMan.startGame );
                    this.notifyObservers( message );
                    break;
                case UpdateCodes.PacMan.mainMenu:
                    exitToMainGUI();
                    this.notifyObservers( message );
                    break;
                case UpdateCodes.PacMan.showEndScreen:
                    changer.changeFxml( new PacManEndScreen(), UpdateCodes.PacMan.showEndScreen );
                    break;
                default:
                    logParsingError( o, arg );
                    break;
            }
        }
        else {
            logParsingError( o, arg );
        }
    }

    private void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacMan Coop update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    @Override
    public void render() {
        if ( game != null ) {
            game.render();
        }
    }


}
