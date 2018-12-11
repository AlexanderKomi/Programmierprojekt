package de.hsh.alexander.src;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;
import java.util.Observer;

public class PacManController extends GameEntryPoint {

    private final PacManFxmlChanger changer;
    private       PacManGame        game;
    private       PacManEndScreen   pacManEndScreen;

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        this.changer = new PacManFxmlChanger( this, PacManMenu.fxml, new PacManMenu() );
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
                    startGame();
                    break;
                case UpdateCodes.PacMan.mainMenu:
                    Logger.log( "-------------------MAIN MENU----------------------" );
                    this.game.deleteObservers();
                    this.game = null;
                    exitToMainGUI();
                    break;
                case UpdateCodes.PacMan.showEndScreen:
                    this.pacManEndScreen = new PacManEndScreen();
                    changer.changeFxml( pacManEndScreen, UpdateCodes.PacMan.showEndScreen );
                    pacManEndScreen.afterInitialization(
                            game.getCurrentLevel().getPacMan1Property().get(),
                            game.getCurrentLevel().getPacMan2Property().get()
                                                       );
                    break;
                case UpdateCodes.PacMan.repeatGame:
                    startGame();
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

    private void startGame() {
        if ( this.game != null ) {
            this.game.deleteObservers();
        }
        this.game = new PacManGame();
        game.addObserver( this );
        changer.changeFxml( this.game, UpdateCodes.PacMan.startGame );
    }

    private static void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacManController: update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    @Override
    public void render(int fps) {
        if ( game != null ) {
            if ( game.initialized ) {
                game.render( fps );
            }
        }
    }


}
