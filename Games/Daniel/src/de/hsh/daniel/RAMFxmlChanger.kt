package de.hsh.daniel

import common.engine.FxModul
import common.engine.FxmlChanger
import common.codes.EngineObserverCodes
import common.updates.UpdateCodes
import common.logger.Logger
import de.hsh.daniel.controller.RAM_MainMenu_controller
import de.hsh.daniel.controller.RAM_winScreen_controller
import de.hsh.daniel.controller.RamGame_controller
import java.util.*

class RAMFxmlChanger internal constructor(fxModul: FxModul,
                                          fxmlPath: String,
                                          fxController: Observable) : FxmlChanger(fxModul,
                                                                                   fxmlPath,
                                                                                   fxController) {
    override fun changeFxml(o: Observable?, msg: String?) {
        when (msg) {
            UpdateCodes.RAM.startGame                      -> {
                changeScene(RamGame_controller.fxml, o!!)
            }
            UpdateCodes.RAM.mainMenu, UpdateCodes.RAM.quit -> {
                changeScene(RAM_MainMenu_controller.fxml, o!!)
            }
            EngineObserverCodes.exitToMainGUI                        -> {
                changeScene("common/gui/P3_Gui.fxml", o!!)
            }
            UpdateCodes.RAM.p1Win                          -> {
                changeScene(RAM_winScreen_controller.fxmlP1Win, o!!)
            }
            UpdateCodes.RAM.p2Win                          -> {
                changeScene(RAM_winScreen_controller.fxmlP2Win, o!!)
            }
            UpdateCodes.RAM.tie                            -> {
                changeScene(RAM_winScreen_controller.fxmlTie, o!!)
            }
            else                                           -> {
                Logger.log(this.javaClass.toString() + ": fxml not found")
            }
        }
    }
}
