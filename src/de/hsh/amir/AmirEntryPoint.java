package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.amir.controller.AmirGameController;
import de.hsh.amir.controller.AmirsMainMenuController;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirEntryPoint extends GameEntryPoint {

    private AmirFxmlChanger    changer;
    private AmirGameController amirGame;
    boolean initialized = false;

    public AmirEntryPoint(Observer o ) {
        super(o, WindowConfig.amir_title);
        this.amirGame = new AmirGameController();
        changer = new AmirFxmlChanger( this, AmirsMainMenuController.fxml, new AmirsMainMenuController() );
    }


    @Override
    public void update(Observable o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.Amir.startGame:
                    changer.changeFxml( this.amirGame, UpdateCodes.Amir.startGame );
                    break;
                case UpdateCodes.Amir.mainMenu:
                    changer.changeFxml( new AmirsMainMenuController(), AmirsMainMenuController.fxml );
                    break;
                case UpdateCodes.DefaultCodes.exitToMainGUI:
                    exitToMainGUI();
                    break;
                case UpdateCodes.DefaultCodes.exitToMainGUI:
                    changer.changeFxml(o, message);
                    break;
                default:
                    logParsingError( o, arg );
                    break;
            }
            initialized = true;
            //this.notifyObservers( message );
        }
        else {
            logParsingError( o, arg );
        }
    }

    @Override
    public void render(int fps) {
        if ( initialized ) {
            amirGame.render( fps );
        }
    }

    private static void logParsingError(Observable o, Object arg ) {
        Logger.log("AmirEntryPoint: update : from observable : " + o + " Argument could not be parsed : " + arg );
    }
}
