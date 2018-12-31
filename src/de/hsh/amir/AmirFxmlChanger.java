package de.hsh.amir;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.amir.controller.AmirGameController;
import de.hsh.amir.controller.AmirsMainMenuController;
import java.util.Observable;

/**
 * Klasse, die für das Wechseln der FXML-Dateien, sprich die das Menü und
 * das Spiel verantwortlich ist.
 */
public class AmirFxmlChanger extends FxmlChanger {

    public AmirFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        if (msg.equals(UpdateCodes.Amir.startGame)) {
            changeScene(AmirGameController.fxml, o);
        } else if (msg.equals(UpdateCodes.Amir.mainMenu)) {
            this.changeScene(AmirsMainMenuController.fxml, o);
        } else if (msg.equals(UpdateCodes.Amir.showEndScreen)) {
            this.changeScene(AmirsMainMenuController.fxmlGameOver, o);
        }  else {
            Logger.log(this.getClass() + ": fxml not found");
        }
    }
}
