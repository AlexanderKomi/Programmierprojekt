package common.engine

import common.MainMenu
import common.config.WindowConfig
import common.engine.components.game.GameEntryPoints
import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.stage.Stage
import java.util.*

/**
 * FXGameContainer is a container for a game engine, a main menu and all gameEntryPoints.
 *
 * @author Alexander Komischke
 */
abstract class FXGameContainer : Application(), GameContainer, Observer {

    private lateinit var engineGameContainer: EngineGameContainer
    private lateinit var stage: Stage
    private lateinit var menu: MainMenu

    /**
     * Launches the javafx Thread. When using an FX Container, apply every interaction, only after the FXThread isLaunched.
     *
     * @param primaryStage
     * Main window used.
     */
    override fun start(primaryStage: Stage) {
        stage = configureStage(primaryStage) // This line is required, for reference change.
        stage.onCloseRequest = EventHandler { stopContainer() }

        engineGameContainer = EngineGameContainer(createGames(this))

        menu = configMainMenu(engineGameContainer.gameEntryPoints.names)
        menu.addObserver(this)
        menu.initGameNames()
        stage.scene = menu.scene
        engineGameContainer.startEngine()
        stage.show()
    }

    fun showMainMenu() {
        stage.scene = menu.scene
        stage.title = menu.title
    }

    fun setGameShown(gameName: String) {
        engineGameContainer.gameEntryPoints.activeGame = gameName
        val gameEntryPoint = engineGameContainer.gameEntryPoints[gameName]
        stage.title = gameEntryPoint.name
        if (gameEntryPoint.scene.rootProperty().get() == null) {
            throw NullPointerException("Scene root property is null.")
        }
        stage.scene = gameEntryPoint.scene
    }

    fun containsGame(gameName: String): Boolean = this.engineGameContainer.gameEntryPoints.contains(gameName)

    /**
     * Stops the engine container. Kills every process inside the container.
     */
    override fun stopContainer(func: () -> Unit) {
        beforeStoppingContainer()
        func()
        engineGameContainer.stopEngine()
        Platform.exit()
    }

    protected abstract fun configureStage(primaryStage: Stage): Stage
    protected abstract fun configMainMenu(games: List<String>): MainMenu
    abstract fun createGames(o: Observer): GameEntryPoints
    abstract fun beforeStoppingContainer()
}
