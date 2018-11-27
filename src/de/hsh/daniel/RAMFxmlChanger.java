package de.hsh.daniel;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.dennis.controller.MainMenu_controller;

import java.util.Observable;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class RAMFxmlChanger extends FxmlChanger {

    public RAMFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        if(msg.equals(UpdateCodes.RAM.startGame)){}
        else if(msg.equals(UpdateCodes.RAM.mainMenu)){
            this.changeScene( RAM_MainMenu_controller.fxml, o );
        } else {
            Logger.log(this.getClass()+": fxml not found");
        }
        /*
        if(o instanceof RAM_MainMenu_controller) {
            handle_RAM_MainMenu_controller(msg);
        }
    */

    }

    private void handle_RAM_MainMenu_controller(String code) {
        switch (code) {
            case "b_back":
                Logger.log("handle_RAM_MainMenu_controller: b_back erreicht");

                break;

            case "b_play":
                Logger.log("handle_RAM_MainMenu_controller:  b_play erreicht");
                break;

            case "mb_level":
                Logger.log("handle_RAM_MainMenu_controller:  mb_level erreicht");
                break;
            default:
                Logger.log("handle_Tutorial_controller:  default erreicht");
        }
    }

}
