package de.hsh.alexander.src;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.application.Platform;

import java.util.Observable;
import java.util.Observer;

public final class PacManController extends GameEntryPoint {

    private final  PacManFxmlChanger changer;
    private static PacManGame        game;
    private static PacManEndScreen   pacManEndScreen = new PacManEndScreen();
    private static PacManMenu        pacManMenu      = new PacManMenu();

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        changer = new PacManFxmlChanger( this, PacManMenu.fxml, pacManMenu );
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
                    changer.changeFxml( pacManMenu, UpdateCodes.PacMan.mainMenu );
                    exitToMainGUI();
                    break;
                case UpdateCodes.PacMan.showEndScreen:
                    showEndScreen();
                    break;
                case UpdateCodes.PacMan.repeatGame:
                    //changer.changeFxml( pacManEndScreen, UpdateCodes.PacMan.showEndScreen );
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
        if ( game != null ) {
            game.deleteObservers();
        }
        game = new PacManGame();
        game.addObserver( this );
        changer.changeFxml( game, UpdateCodes.PacMan.startGame );
    }

    private void showEndScreen() {
        Platform.runLater( () -> {
            PacManEndScreen.player1Points = game.getCurrentLevel().getPacMan1Property().get();
            PacManEndScreen.player2Points = game.getCurrentLevel().getPacMan2Property().get();
            changer.changeFxml( pacManEndScreen, UpdateCodes.PacMan.showEndScreen );

            if ( game != null ) {
                game.delete();
                game = null;
            }
        } );
    }

    private static void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacManController: update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    @Override
    public void render(int fps) {
        if ( pacManMenu != null ) {
            pacManMenu.render();
        }
        if ( pacManEndScreen != null ) {
            pacManEndScreen.render();
        }
        if ( game != null ) {
            if ( game.initialized ) {
                game.render( fps );
            }
        }
    }


}
