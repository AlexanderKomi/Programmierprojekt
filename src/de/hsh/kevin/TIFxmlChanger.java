package de.hsh.kevin;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.kevin.controller.TIGameController;
import de.hsh.kevin.controller.TIGameOverController;
import de.hsh.kevin.controller.TIMenuController;

import java.util.Observable;

public class TIFxmlChanger extends FxmlChanger {

    public TIFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        if (o instanceof TIMenuController) {
            switch (msg) {
            case UpdateCodes.DefaultCodes.exitToMainGUI:
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
                break;
            }
        } else if (o instanceof TIGameOverController) {
            switch (msg) {
            case UpdateCodes.DefaultCodes.exitToMainGUI:
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
                break;
            case UpdateCodes.TunnelInvader.gameMenu:
                changeScene(TIMenuController.fxml, new TIMenuController());
                break;
            }
        }
    }

    public void changeGameFxml(Observable o, TIGameController game) {
        changeScene(TIGameController.fxml, game);
    }

    public void changeGameOverFxml(Observable o, TIGameOverController gameOver) {
        changeScene(TIGameOverController.fxml, gameOver);
    }
}
