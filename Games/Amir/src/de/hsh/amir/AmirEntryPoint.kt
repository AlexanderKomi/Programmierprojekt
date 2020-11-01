package de.hsh.amir

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.codes.EngineObserverCodes
import common.updates.UpdateCodes
import common.logger.Logger
import de.hsh.amir.controller.AmirGameController
import de.hsh.amir.controller.AmirsMainMenuController
import javafx.application.Platform
import java.util.*

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
class AmirEntryPoint(o: Observer) : GameEntryPoint(o, WindowConfig.amir_title) {
    private val changer: AmirFxmlChanger = AmirFxmlChanger(this, AmirsMainMenuController.fxml, AmirsMainMenuController())
    override fun update(o: Observable, arg: Any) {
        if (arg is String) {
            when (arg) {
                UpdateCodes.Amir.startGame     -> {
                    amirGame = AmirGameController()
                    changer.changeFxml(amirGame, UpdateCodes.Amir.startGame)
                }
                UpdateCodes.Amir.mainMenu      -> changer.changeFxml(AmirsMainMenuController(),
                                                                     UpdateCodes.Amir.mainMenu)
                UpdateCodes.Amir.showEndScreen -> {
                    if (amirGame != null) {
                        amirGame!!.deleteObservers()
                        amirGame = null
                    }
                    changer.changeFxml(AmirsMainMenuController(), UpdateCodes.Amir.showEndScreen)
                }
                EngineObserverCodes.exitToMainGUI        -> {
                    if (amirGame != null) {
                        amirGame!!.deleteObservers()
                        amirGame = null
                    }
                    changer.changeFxml(AmirsMainMenuController(), UpdateCodes.Amir.mainMenu)
                    exitToMainGUI()
                }
                else                           -> logParsingError(o, arg)
            }
        } else {
            logParsingError(o, arg)
        }
    }

    override fun render(fps: Int) = Platform.runLater {
        if (amirGame != null) {
            amirGame!!.render(fps)
        }
    }

    companion object {
        private var amirGame: AmirGameController? = null
        private fun logParsingError(o: Observable, arg: Any) {
            Logger.log("AmirEntryPoint: update : from observable : $o Argument could not be parsed : $arg")
        }
    }

}
