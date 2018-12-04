package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.src.PacManEndScreen;
import de.hsh.alexander.src.PacManMenu;
import de.hsh.amir.controller.AmirsMainMenuController;
import de.hsh.amir.logik.AmirGame;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirEntryPoint extends GameEntryPoint {

    private AmirFxmlChanger changer;
    private AmirGame amirGame;

    public AmirEntryPoint(Observer o ) {
        super(o, WindowConfig.amir_title);
        this.amirGame = new AmirGame();
        changer = new AmirFxmlChanger(this, "view/AmirsMenu.fxml", new AmirsMainMenuController());
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
                    exitToMainGUI();
                    break;
                case UpdateCodes.Amir.showEndScreen:
                    changer.changeFxml( new PacManEndScreen(), UpdateCodes.Amir.showEndScreen );
                    break;
                case UpdateCodes.DefaultCodes.exitToMainGUI:
                    changer.changeFxml(o, message);
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

    @Override
    public void render(int fps) {

    }

    private static void logParsingError(Observable o, Object arg ) {
        Logger.log("AmirEntryPoint: update : from observable : " + o + " Argument could not be parsed : " + arg );
    }
}
