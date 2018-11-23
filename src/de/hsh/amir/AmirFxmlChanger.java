package de.hsh.amir;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.Game;
import common.updates.UpdateCodes;
import de.hsh.amir.controller.MainMenu_controller;

import java.util.Observable;
import common.util.Logger;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class AmirFxmlChanger extends FxmlChanger {

    public AmirFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
            if (o instanceof MainMenu_controller){handle_MainMenu(msg);}
            else if(false){}
    }

    private void handle_MainMenu(String msg) {
        switch (msg){
            case UpdateCodes.DefaultCodes.exitToMainGUI:
                ((Game) getFxModul()).exitToMainGUI();
        }
    }
}
