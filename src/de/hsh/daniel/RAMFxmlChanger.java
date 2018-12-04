package de.hsh.daniel;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.de.hsh.daniel.controller.RamGame_controller;
import de.hsh.dennis.controller.LevelMenu_controller;
import de.hsh.dennis.controller.MainMenu_controller;

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
        if(msg.equals("b_play")){
            RamGame_controller c = new RamGame_controller();
            changeScene("view/RAMGame.fxml", c);
            c.passCanvas();

        }
        else if(msg.equals(UpdateCodes.RAM.startGame)){
            Logger.log(this.getClass()+": " + msg);
        }
        else if(msg.equals(UpdateCodes.RAM.mainMenu)){
            Logger.log(this.getClass()+": " + msg);
            this.changeScene( RAM_MainMenu_controller.fxml, o );
        } else {
            Logger.log(this.getClass()+": fxml not found");
        }

    }

}
