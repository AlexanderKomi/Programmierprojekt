package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.MenuCodes;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.amir.controller.AmirGameController;
import de.hsh.amir.controller.AmirsMainMenuController;
import javafx.application.Platform;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirEntryPoint extends GameEntryPoint {

    private final  AmirFxmlChanger    changer;
    private static AmirGameController amirGame;

    public AmirEntryPoint( Observer o ) {
        super( o, WindowConfig.amir_title );
        changer = new AmirFxmlChanger( this, AmirsMainMenuController.fxml, new AmirsMainMenuController() );
    }


    @Override
    public void update( Observable o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.Amir.startGame:
                    amirGame = new AmirGameController();
                    changer.changeFxml( amirGame, UpdateCodes.Amir.startGame );
                    break;
                case UpdateCodes.Amir.mainMenu:
                    changer.changeFxml( new AmirsMainMenuController(), UpdateCodes.Amir.mainMenu );
                    break;
                case UpdateCodes.Amir.showEndScreen:
                    if ( amirGame != null ) {
                        amirGame.deleteObservers();
                        amirGame = null;
                    }
                    changer.changeFxml( new AmirsMainMenuController(), UpdateCodes.Amir.showEndScreen );
                    break;
                case MenuCodes.exitToMainGUI:
                    if ( amirGame != null ) {
                        amirGame.deleteObservers();
                        amirGame = null;
                    }
                    changer.changeFxml( new AmirsMainMenuController(), UpdateCodes.Amir.mainMenu );
                    exitToMainGUI();
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

    @Override
    public void render( final int fps ) {
        Platform.runLater( () -> {
            if ( amirGame != null ) {
                amirGame.render( fps );
            }
        } );

    }

    private static void logParsingError( Observable o, Object arg ) {
        Logger.log( "AmirEntryPoint: update : from observable : " + o + " Argument could not be parsed : " + arg );
    }
}
