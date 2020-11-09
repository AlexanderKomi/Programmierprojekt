package de.hsh.alexander

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.updates.*
import common.util.Logger
import javafx.application.Platform

class PacManController(o: Updater)
    : GameEntryPoint(o, WindowConfig.alexander_title) {

    override val updaterList = mutableListOf<Updater>()
    private var game: PacManGame? = null
    private val pacManEndScreen: PacManEndScreen = PacManEndScreen()
    private val pacManMenu: PacManMenu = PacManMenu()
    private val changer: PacManFxmlChanger = PacManFxmlChanger(this, PacManMenu.fxml, pacManMenu)

    /**
     * PacManMenu sends a notification to change the fxml.
     */
    override fun update(updatable: Updatable, message: String?) {
        fun startGame() {
            if (game != null) {
                game!!.deleteObservers()
            }
            game = PacManGame()
            game!!.addUpdater(this)
            changer.changeFxml(game!!, UpdateCodes.PacMan.startGame)
        }

        fun showEndScreen() = Platform.runLater {
            PacManEndScreen.player1Points = game!!.currentLevel.pacMan1Property.get()
            PacManEndScreen.player2Points = game!!.currentLevel.pacMan2Property.get()
            changer.changeFxml(pacManEndScreen, UpdateCodes.PacMan.showEndScreen)
            if (game != null) {
                game!!.delete()
                game = null
            }
        }
        when (message) {
            UpdateCodes.PacMan.startGame -> startGame()
            UpdateCodes.PacMan.mainMenu -> {
                changer.changeFxml(pacManMenu, UpdateCodes.PacMan.mainMenu)
                exitToMainGUI()
            }
            UpdateCodes.PacMan.showEndScreen -> showEndScreen()
            UpdateCodes.PacMan.repeatGame -> startGame()
            else -> Logger log "In PacManController: update : from observable : $updatable Argument could not be parsed : $message"
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

    override fun gameUpdater(): GameUpdater = UpdatePacman

}
