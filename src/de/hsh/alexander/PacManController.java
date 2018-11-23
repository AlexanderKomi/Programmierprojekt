package de.hsh.alexander;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.scene.input.KeyEvent;

import java.util.Observable;
import java.util.Observer;

public class PacManController extends GameEntryPoint {

    private PacManFxmlChanger changer;
    private PacManMenu gameMenu;
    private PacManGame game;

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        changer = new PacManFxmlChanger( this, PacManMenu.fxml, new PacManMenu() );
    }



    /* Currently not used... */
    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof PacManMenu ) {
            update( (PacManMenu) o, arg );
        }
        else if ( o instanceof KeyEventManager ) {
            update( (KeyEventManager) o, arg );
        }
        else if ( o instanceof MouseEventManager ) {
            update( (MouseEventManager) o, arg );
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
                    this.game = new PacManGame();
                    changer.changeScene( PacManGame.fxml, this.game );
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

    public void update( KeyEventManager o, Object arg ) {
        if ( arg instanceof KeyEvent ) {
            KeyEvent keyEvent = (KeyEvent) arg;
            this.game.movePacMan1( keyEvent );
        }
    }

    public void update( MouseEventManager o, Object arg ) {}

    private void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacMan Coop update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    @Override
    public void render() {
        if ( game != null ) {
            if ( game.initialized ) {
                game.render();
            }
        }
    }


}
