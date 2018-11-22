package de.hsh.alexander;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;

public class PacManFxmlChanger extends FxmlChanger {

    public PacManFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        switch (msg) {
            case UpdateCodes.PacMan.startGame:
                changeScene("view/PacManGame.fxml", new PacManGame());
                break;
            default:
                Logger.log("changeFxml in PacManFxmlCanger: default");
        }
    }
}
