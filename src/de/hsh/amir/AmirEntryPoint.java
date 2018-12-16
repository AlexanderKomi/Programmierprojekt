package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
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

    private AmirFxmlChanger changer;
    private AmirGameController amirGame;
    private AmirsMainMenuController mainMenuController;

    public AmirEntryPoint(Observer o) {
        super(o, WindowConfig.amir_title);
        this.amirGame = new AmirGameController();
        this.mainMenuController = new AmirsMainMenuController();
        changer = new AmirFxmlChanger(this, AmirsMainMenuController.fxml, mainMenuController);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String message = (String) arg;
            switch (message) {
                case UpdateCodes.Amir.startGame:
                    changer.changeFxml(this.amirGame, UpdateCodes.Amir.startGame);
                    break;
                case UpdateCodes.Amir.mainMenu:
                    mainMenuController.switchLabels();
                    changer.changeFxml(mainMenuController, UpdateCodes.Amir.mainMenu);
                    break;
                case UpdateCodes.DefaultCodes.exitToMainGUI:
                    AmirsMainMenuController.gameStarted = false;
                    exitToMainGUI();
                    break;
                default:
                    logParsingError(o, arg);
                    break;
            }
            //this.notifyObservers( message );
        } else {
            logParsingError(o, arg);
        }
    }

    @Override
    public void render(int fps) {
        if (amirGame != null) {
            Platform.runLater(() -> {
                amirGame.render(fps);
            });
        } if(mainMenuController!= null){
           Platform.runLater(()->{
               mainMenuController.render(fps);
           });
        }
    }

    private static void logParsingError(Observable o, Object arg) {
        Logger.log("AmirEntryPoint: update : from observable : " + o + " Argument could not be parsed : " + arg);
    }
}
