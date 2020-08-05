/**
 * @author Julian Sender
 */
package de.hsh.Julian

import common.engine.FxModul
import common.engine.FxmlChanger
import common.engine.components.game.GameEntryPoint
import common.util.Logger
import de.hsh.Julian.controller.LKEnd
import de.hsh.Julian.controller.SpielBildschirm_controller
import java.util.*

/**
 * Changer for fxml menues
 */
class LKFxmlChanger
/**
 *
 * @param fxModul giving all to the FxmlChanger above
 * @param fxmlPath Path of fxml file
 * @param fxController Every menue needs its own controller
 */
internal constructor(fxModul: FxModul, fxmlPath: String, fxController: Observable) : FxmlChanger(
        fxModul,
        fxmlPackage + fxmlPath,
        fxController) {
    /**
     * Method to change the scene
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     */
    override fun changeScene(fxmlLocation: String, controller: Observable) {
        super.changeScene(fxmlPackage + fxmlLocation, controller)
    }

    /**
     * Needed to change the fxml file
     * @param o Observer
     * @param msg Name of fxml
     */
    override fun changeFxml(o: Observable?, msg: String?) {
        when (msg) {
            "b_backtomenu" -> {
                Logger.log(this.javaClass.toString() + " b_backtomenu_clicked")
                (fxModul as GameEntryPoint).exitToMainGUI()
            }
            "b_start"      -> {
                val c = SpielBildschirm_controller()
                changeScene(SpielBildschirm_controller.fxml, c)
                c.passCanvas()
            }
            "gameover"     -> {
                val l = LKEnd()
                changeScene(LKEnd.fxml, l)
            }
            "b_retry"      -> {
                Logger.log("jo")
                val d = SpielBildschirm_controller()
                changeScene(SpielBildschirm_controller.fxml, d)
                d.passCanvas()
            }
            else           -> Logger.log(this.javaClass.name + " default SwitchCase.")
        }
    }

    companion object {
        private const val fxmlPackage = "view/"
    }
}
