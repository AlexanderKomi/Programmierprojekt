package de.hsh.amir;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.amir.controller.AmirsMainMenuController;

import java.util.Observable;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class AmirFxmlChanger extends FxmlChanger {

    public AmirFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
            if (o instanceof AmirsMainMenuController){handle_MainMenu(msg);}
            else if(false){}
    }

    private void handle_MainMenu(String msg) {
        switch (msg){
            case UpdateCodes.DefaultCodes.exitToMainGUI:
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
        }
    }
}
