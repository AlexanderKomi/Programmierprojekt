package de.hsh.amir;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.amir.controller.AmirGameController;
import de.hsh.amir.controller.AmirsMainMenuController;
import sun.rmi.runtime.Log;

import java.util.Observable;

/**
 * TODO maybe for fixation of exitButton
 */
public class AmirFxmlChanger extends FxmlChanger {

    public AmirFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        Logger.log(this.getClass() + " :logger vor " + msg );
        Logger.log(this.getClass() + " :logger nach " + UpdateCodes.Amir.mainMenu );
        if ( msg.equals( UpdateCodes.Amir.startGame ) ) {
            changeScene( AmirGameController.fxml, o );
        }
        else if ( msg.equals( UpdateCodes.Amir.mainMenu ) ) {
            this.changeScene( AmirsMainMenuController.fxml, o );
        }
        else {
            Logger.log( this.getClass() + ": fxml not found" );
        }
    }
}
