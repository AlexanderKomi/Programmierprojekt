package de.hsh.kevin;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.DefaultCodes;
import common.updates.UpdateCodes;
import de.hsh.kevin.controller.TIGameController;
import de.hsh.kevin.controller.TIGameOverController;
import de.hsh.kevin.controller.TIMenuController;

import java.util.Observable;

public class TIFxmlChanger extends FxmlChanger {

    private static final String fxmlPackage = "res/";

    /**
     * Erstellt den FXMLChanger zum wechseln der Scenes
     */
    public TIFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPackage + fxmlPath, fxController);
    }

    /**
     * Ändert die Scene zum Hauptmenü oder Spielmenü
     */
    @Override
    public void changeFxml(Observable o, String msg) {
        if (o instanceof TIMenuController) {
            switch (msg) {
            case DefaultCodes.exitToMainGUI:
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
                break;
            }
        } else if (o instanceof TIGameOverController) {
            switch (msg) {
            case DefaultCodes.exitToMainGUI:
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
                break;
            case UpdateCodes.TunnelInvader.gameMenu:
                changeScene(fxmlPackage + TIMenuController.fxml, new TIMenuController());
                break;
            }
        }
    }

    /**
     * Ändert die Scene zum SpielScreen
     * @param o
     * @param game
     */
    public void changeGameFxml(Observable o, TIGameController game) {
        changeScene(fxmlPackage + TIGameController.fxml, game);
    }

    /**
     * Ändert die Scene zum GameOverScreen
     * @param o
     * @param gameOver
     */
    public void changeGameOverFxml(Observable o, TIGameOverController gameOver) {
        changeScene(fxmlPackage + TIGameOverController.fxml, gameOver);
    }
}
