package de.hsh.daniel;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.controller.RamGame_controller;

import java.util.Observable;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class RAMFxmlChanger extends FxmlChanger {

    RAMFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        if ( msg.equals( UpdateCodes.RAM.startGame ) ) {
            changeScene( RamGame_controller.fxml, o );
        }
        else if(msg.equals(UpdateCodes.RAM.mainMenu)){
            this.changeScene( RAM_MainMenu_controller.fxml, o );
        } else {
            Logger.log(this.getClass()+": fxml not found");
        }

    }

}
