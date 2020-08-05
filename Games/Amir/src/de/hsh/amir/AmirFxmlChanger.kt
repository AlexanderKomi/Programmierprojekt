package de.hsh.amir

import common.engine.FxModul
import common.engine.FxmlChanger
import common.updates.UpdateCodes
import common.util.Logger
import de.hsh.amir.controller.AmirGameController
import de.hsh.amir.controller.AmirsMainMenuController
import java.util.*

/**
 * Klasse, die für das Wechseln der FXML-Dateien, sprich die das Menü und
 * das Spiel verantwortlich ist.
 */
class AmirFxmlChanger(fxModul: FxModul, fxmlPath: String, fxController: Observable) :
        FxmlChanger(fxModul, fxmlPath, fxController) {

    override fun changeFxml(o: Observable?, msg: String?) = when (msg) {
        UpdateCodes.Amir.startGame     -> changeScene(AmirGameController.fxml, o!!)
        UpdateCodes.Amir.mainMenu      -> changeScene(AmirsMainMenuController.fxml, o!!)
        UpdateCodes.Amir.showEndScreen -> changeScene(AmirsMainMenuController.fxmlGameOver, o!!)
        else                           -> Logger.log(this.javaClass.toString() + ": fxml not found")
    }

}
