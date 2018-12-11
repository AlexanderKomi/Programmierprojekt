package de.hsh.Julian;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.util.Logger;
import de.hsh.Julian.controller.LKEnd;
import de.hsh.Julian.controller.LKStart;
import de.hsh.Julian.controller.SpielBildschirm_controller;
import sun.rmi.runtime.Log;

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

            switch (msg){
                case "b_backtomenu":
                    Logger.log(this.getClass()+" b_backtomenu_clicked");
                    ((GameEntryPoint) getFxModul()).exitToMainGUI();
                    break;
                case "b_start":
                    SpielBildschirm_controller c = new SpielBildschirm_controller();
                    changeScene(SpielBildschirm_controller.fxml, c );
                    c.passCanvas();
                    break;
                case "gameover":
                    Logger.log(this.getClass()+" LKEND "+LKEnd.fxml);
                    Logger.log("blyat");
                    LKEnd l = new LKEnd();
                    changeScene(LKEnd.fxml, l );
                    //l.passCanvas();
                    break;
                default:
                    Logger.log( this.getClass().getName() + " default SwitchCase." );
                    break;
            }

    }
}
