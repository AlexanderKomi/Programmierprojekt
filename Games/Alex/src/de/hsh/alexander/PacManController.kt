package de.hsh.alexander

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.updates.UpdateCodes
import common.util.Logger
import javafx.application.Platform
import java.util.*

class PacManController(o: Observer)
    : GameEntryPoint(o, WindowConfig.alexander_title) {

    private val changer: PacManFxmlChanger = PacManFxmlChanger(this, PacManMenu.fxml, pacManMenu)

    /**
     * PacManMenu sends a notification to change the fxml.
     */
    override fun update(o: Observable,
                        arg: Any) {
        if (arg is String) {
            when (arg) {
                UpdateCodes.PacMan.startGame -> startGame()
                UpdateCodes.PacMan.mainMenu -> {
                    Logger.log("-------------------MAIN MENU----------------------")
                    changer.changeFxml(pacManMenu, UpdateCodes.PacMan.mainMenu)
                    exitToMainGUI()
                }
                UpdateCodes.PacMan.showEndScreen -> showEndScreen()
                UpdateCodes.PacMan.repeatGame -> startGame()
                else                             -> logParsingError(o, arg)
            }
            this.notifyObservers(arg)
        } else {
            logParsingError(o, arg)
        }
    }

    private fun startGame() {
        if (game != null) {
            game!!.deleteObservers()
        }
        game = PacManGame()
        game!!.addObserver(this)
        changer.changeFxml(game, UpdateCodes.PacMan.startGame)
    }

    private fun showEndScreen() {
        Platform.runLater {
            PacManEndScreen.player1Points = game!!.currentLevel
                    .pacMan1Property
                    .get()
            PacManEndScreen.player2Points = game!!.currentLevel
                    .pacMan2Property
                    .get()
            changer.changeFxml(
                    pacManEndScreen,
                    UpdateCodes.PacMan.showEndScreen)
            if (game != null) {
                game!!.delete()
                game = null
            }
        }
    }

    override fun render(fps: Int) {
        pacManMenu.render()
        pacManEndScreen.render()
        if (game != null) {
            if (game!!.initialized) {
                game!!.render(fps)
            }
        }
    }

    companion object {
        private var game: PacManGame? = null
        private val pacManEndScreen: PacManEndScreen = PacManEndScreen()
        private val pacManMenu: PacManMenu = PacManMenu()
        private fun logParsingError(o: Observable,
                                    arg: Any) {
            Logger.log("In PacManController: update : from observable : $o Argument could not be parsed : $arg")
        }
    }
}
