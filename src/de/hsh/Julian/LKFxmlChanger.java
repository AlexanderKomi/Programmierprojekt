package de.hsh.Julian;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.util.Logger;
import de.hsh.Julian.controller.LKStart;
import de.hsh.Julian.controller.SpielBildschirm_controller;

import java.util.Observable;

public class LKFxmlChanger extends FxmlChanger {

    private static final String fxmlPackage = "view/";

    LKFxmlChanger( FxModul fxModul, String fxmlPath, Observable fxController ) {
        super( fxModul, fxmlPackage + fxmlPath, fxController );
    }

    @Override
    public void changeScene( String fxmlLocation, Observable controller ) {
        super.changeScene( fxmlPackage + fxmlLocation, controller );
    }

    @Override
    public void changeFxml( Observable o, String msg ) {
        if(o instanceof LKStart){
            switch (msg){
                case "b_backtomenu":
                    ((GameEntryPoint) getFxModul()).exitToMainGUI();
                    break;
                case "b_start":
                    SpielBildschirm_controller c = new SpielBildschirm_controller();
                    changeScene(SpielBildschirm_controller.fxml, c );
                    c.passCanvas();
                    break;
                default:
                    Logger.log( this.getClass().getName() + " default SwitchCase." );
                    break;
            }
        }
    }
}
