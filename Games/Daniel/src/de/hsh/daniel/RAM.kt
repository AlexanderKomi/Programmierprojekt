package de.hsh.daniel

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.codes.EngineObserverCodes
import common.updates.UpdateCodes
import common.logger.Logger
import de.hsh.daniel.controller.RAM_MainMenu_controller
import de.hsh.daniel.controller.RAM_winScreen_controller
import de.hsh.daniel.controller.RamGame_controller
import javafx.application.Platform
import java.util.*

class RAM(o: Observer) : GameEntryPoint(o, WindowConfig.daniel_title) {
    private var initialized = false
    private val changer: RAMFxmlChanger = RAMFxmlChanger(this, RAM_MainMenu_controller.fxml, RAM_MainMenu_controller())

    //hier melden sich die Controller
    override fun update(o: Observable, arg: Any) {
        if (arg is String) {
            val message = arg
            Logger.log(this.javaClass.toString() + ": arg = " + message)
            when (message) {
                UpdateCodes.RAM.startGame                                         -> startGame(message)
                UpdateCodes.RAM.mainMenu                                          -> showMainMenu(message)
                EngineObserverCodes.exitToMainGUI, UpdateCodes.RAM.quit                     -> exit()
                UpdateCodes.RAM.p1Win, UpdateCodes.RAM.p2Win, UpdateCodes.RAM.tie -> showEndScreen(message)
                else                                                              -> changer.changeFxml(o,
                                                                                                        arg)
            }
        }
    }

    private fun startGame(message: String) {
        if (!initialized) {
            game = RamGame_controller()
            changer.changeFxml(game, message)
            initialized = true
        }
    }

    private fun showMainMenu(message: String) {
        ramMenu = RAM_MainMenu_controller()
        initialized = false
        changer.changeFxml(ramMenu, message)
    }

    private fun showEndScreen(message: String) {
        winScreen = RAM_winScreen_controller()
        initialized = false
        changer.changeFxml(winScreen, message)
    }

    private fun exit() {
        Logger.log("exit reached")
        initialized = false
        exitToMainGUI()
    }

    override fun render(fps: Int) {
        if (initialized) {
            if (game != null) {
                Platform.runLater { game!!.render(fps) }
            }
        }
    }

    companion object {
        private var game: RamGame_controller? = null
        private var ramMenu: RAM_MainMenu_controller? = null
        private var winScreen: RAM_winScreen_controller? = null
    }

}
