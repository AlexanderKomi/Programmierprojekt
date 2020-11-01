package common

import common.config.WindowConfig
import common.engine.FXGameContainer
import common.engine.components.game.GameEntryPoints
import common.updates.Updater
import de.hsh.Julian.LKEntryPoint
import de.hsh.alexander.PacManController
import de.hsh.amir.AmirEntryPoint
import de.hsh.daniel.RAM
import de.hsh.dennis.DennisGameEntryPoint
import de.hsh.dennis.model.MusicPlayer
import de.hsh.kevin.controller.TIController
import javafx.fxml.FXMLLoader
import javafx.stage.Stage
import java.io.IOException
import java.util.*

class GameContainer : FXGameContainer() {

    override fun update(observable: Observable, arg: Any) = Updater.update(observable, arg, this)

    override fun createGames(o: Observer): GameEntryPoints = GameEntryPoints(
                    PacManController(o),
                    AmirEntryPoint(o),
                    RAM(o),
                    TIController(o),
                    DennisGameEntryPoint(o),
                    LKEntryPoint(o))

    override fun configureStage(primaryStage: Stage): Stage {
        primaryStage.title = WindowConfig.mainGui_title
        primaryStage.isResizable = false
        return primaryStage
    }

    override fun configMainMenu(games: List<String>): MainMenu {
        var mainMenu = MainMenu()
        try {
            val location = javaClass.getResource(mainMenuFXMLPath)
            mainMenu.vbox = FXMLLoader.load(location)
            mainMenu.setMenuPane(mainMenu.vbox)
        } catch (e: IOException) {
            mainMenu = MainMenu()
            e.printStackTrace()
        }
        mainMenu.addObserver(this)
        return mainMenu
    }

    /**
     * Muss in der aufgerufenen Klasse implementiert sein!
     */
    override fun startContainer(args: Array<String>) = launch(*args)
    override fun beforeStoppingContainer() = MusicPlayer.shutdown()
    override fun toString(): String {
        return "GameContainer(" +
               "superclass:" + super.toString() +
               ")"
    }

    companion object {
        private const val mainMenuFXMLPath = "gui/P3_Gui.fxml"
    }
}
