/**
 * @author Julian Sender
 */
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

/**
 * Changer for fxml menues
 */
public class LKFxmlChanger extends FxmlChanger {

    private static final String fxmlPackage = "view/";

    /**
     *
     * @param fxModul giving all to the FxmlChanger above
     * @param fxmlPath Path of fxml file
     * @param fxController Every menue needs its own controller
     */
    LKFxmlChanger( FxModul fxModul, String fxmlPath, Observable fxController ) {
        super( fxModul, fxmlPackage + fxmlPath, fxController );
    }

    /**
     * Method to change the scene
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     */
    @Override
    public void changeScene( String fxmlLocation, Observable controller ) {
        super.changeScene( fxmlPackage + fxmlLocation, controller );
    }

    /**
     * Needed to change the fxml file
     * @param o Observer
     * @param msg Name of fxml
     */
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

                    LKEnd l = new LKEnd();
                    changeScene(LKEnd.fxml, l );
                    //l.passCanvas();
                    break;
                case "b_retry":
                    Logger.log("jo");
                    SpielBildschirm_controller d = new SpielBildschirm_controller();
                    changeScene(SpielBildschirm_controller.fxml, d);
                    d.passCanvas();
                    break;
                default:
                    Logger.log( this.getClass().getName() + " default SwitchCase." );
                    break;
            }

    }
}
