package de.hsh.alexander

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.updates.GameUpdater
import common.updates.UpdateCodes
import common.updates.UpdatePacman
import common.util.Logger
import javafx.application.Platform
import java.util.*

class PacManController(o: Observer)
    : GameEntryPoint(o, WindowConfig.alexander_title) {

    private var game: PacManGame? = null
    private val pacManEndScreen: PacManEndScreen = PacManEndScreen()
    private val pacManMenu: PacManMenu = PacManMenu()
    private val changer: PacManFxmlChanger = PacManFxmlChanger(this, PacManMenu.fxml, pacManMenu)

    private fun logParsingError(o: Observable,
                                arg: Any) =
            Logger log "In PacManController: update : from observable : $o Argument could not be parsed : $arg"

    /**
     * PacManMenu sends a notification to change the fxml.
     */
    override fun update(o: Observable,
                        arg: Any) =
            if (arg is String) {
                fun startGame() {
                    if (game != null) {
                        game!!.deleteObservers()
                    }
                    game = PacManGame()
                    game!!.addObserver(this)
                    changer.changeFxml(game!!, UpdateCodes.PacMan.startGame)
                }

                fun showEndScreen() {
                    Platform.runLater {
                        PacManEndScreen.player1Points = game!!.currentLevel.pacMan1Property.get()
                        PacManEndScreen.player2Points = game!!.currentLevel.pacMan2Property.get()
                        changer.changeFxml(pacManEndScreen, UpdateCodes.PacMan.showEndScreen)
                        if (game != null) {
                            game!!.delete()
                            game = null
                        }
                    }
                }

                when (arg) {
                    UpdateCodes.PacMan.startGame -> startGame()
                    UpdateCodes.PacMan.mainMenu -> {
                        changer.changeFxml(pacManMenu, UpdateCodes.PacMan.mainMenu)
                        exitToMainGUI()
                    }
                    UpdateCodes.PacMan.showEndScreen -> showEndScreen()
                    UpdateCodes.PacMan.repeatGame -> startGame()
                    else -> logParsingError(o, arg)
                }
            } else {
                logParsingError(o, arg)
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

    override fun gameUpdater(): GameUpdater = UpdatePacman

}
