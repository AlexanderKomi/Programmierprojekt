package de.hsh.alexander;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;
import java.util.Observer;

public class PacManController extends GameEntryPoint {

    private PacManFxmlChanger changer;
    private PacManMenu gameMenu;
    private PacManGame game;

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        this.game = new PacManGame();
        changer = new PacManFxmlChanger( this, PacManMenu.fxml, new PacManMenu() );
    }



    /* Currently not used... */
    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof PacManMenu ) {
            update( (PacManMenu) o, arg );
        }
        else if ( o instanceof PacManGame ) {
            update( (PacManGame) o, arg );
        }
        else {
            logParsingError( o, arg );
        }
    }

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
                default:
                    logParsingError( o, arg );
                    break;
            }
        }
        else {
            logParsingError( o, arg );
        }
    }

    public void update( PacManGame game, Object arg ) {
        Logger.log( this.getClass() + ": " + game + ", " + arg );
    }

    @Override
    public void update( KeyEventManager keyEventManager, Object arg ) {
        Logger.log( this.getClass() + ": " + keyEventManager + ", " + arg );
    }

    public void update( MouseEventManager o, Object arg ) {
        Logger.log( this.getClass() + ": " + o + ", " + arg );
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
