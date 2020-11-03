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

    override fun configureStage(primaryStage: Stage): Stage = primaryStage.also {
        primaryStage.title = WindowConfig.mainGui_title
        primaryStage.isResizable = false
    }

    override fun configMainMenu(): MainMenu =
        MainMenu().also {
            it.vbox = FXMLLoader.load(javaClass.getResource(mainMenuFXMLPath))
            it.setMenuPane(it.vbox)
            it.addObserver(this)
        }

    /**
     * Muss in der aufgerufenen Klasse implementiert sein!
     */
    override fun startContainer(args: Array<String>) = launch(*args)
    override fun beforeStoppingContainer() = MusicPlayer.shutdown()
    override fun toString(): String = "GameContainer(superclass:${super.toString()})"

    companion object {
        private const val mainMenuFXMLPath = "gui/P3_Gui.fxml"
    }
}
