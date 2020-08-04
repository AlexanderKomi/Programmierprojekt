package common.engine

import common.MainMenu
import common.config.WindowConfig
import common.engine.GameContainerInterface.Companion.gameEntryPoints
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.stage.Stage
import java.util.*

/**
 * FXGameContainer is a container for a game engine, a main menu and all gameEntryPoints.
 *
 * @author Alexander Komischke
 */
abstract class FXGameContainer : EngineGameContainer() {

    private lateinit var stage: Stage
    private lateinit var menu: MainMenu

    /**
     * Launches the javafx Thread. When using an FX Container, apply every interaction, only after the FXThread isLaunched.
     *
     * @param primaryStage
     * Main window used.
     */
    override fun start(primaryStage: Stage) {
        check(!isLaunched) { "Already isLaunched an JavaFX Application. Use existing Stage instead." }
        isLaunched = true

        stage = primaryStage // This line is required, for reference change.
        stage.title = WindowConfig.mainGui_title
        stage.isResizable = false
        stage.onCloseRequest = EventHandler { stopContainer() }

        gameEntryPoints = createGames(this)

        menu = configMainMenu(gameEntryPoints.names)
        menu.addObserver(this)
        menu.initGameNames()
        stage.scene = menu.scene
        engine.start()
        stage.show()
    }

    fun showMainMenu() {
        stage.scene = menu.scene
        stage.title = WindowConfig.mainGui_title
    }

    fun setGameShown(gameName: String) {
        gameEntryPoints.activeGame = gameName
        val gameEntryPoint = gameEntryPoints[gameName]
        stage.title = gameEntryPoint.name
        val s = gameEntryPoint.scene
        if (s.rootProperty().get() == null) {
            throw NullPointerException("Scene root property is null.")
        }
        stage.scene = s
    }

    /**
     * Stops the Container instance and the running engine.
     *
     * @see GameContainerInterface
     */
    override fun stopContainer(func: () -> Unit) {
        super.stopContainer {
            func()
            Platform.exit()
        }
    }

    protected abstract fun configMainMenu(games: List<String>): MainMenu
}
