package de.hsh.kevin;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.kevin.controller.TIMenuController;

import java.util.Observable;

public class TIFxmlChanger extends FxmlChanger {

    public TIFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        if (o instanceof TIMenuController) {
            handle_TIMenuController(msg);
        } else if (false) {

        }
    }

    private void handle_TIMenuController(String msg) {
        switch (msg){
            case UpdateCodes.DefaultCodes.exitToMainGUI:
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
                break;
        }
    }
}
