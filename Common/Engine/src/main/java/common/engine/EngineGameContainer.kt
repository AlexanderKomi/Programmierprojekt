package common.engine

import common.engine.GameContainerInterface.Companion.gameEntryPoints
import common.engine.components.game.GameEntryPoints
import javafx.application.Application
import javafx.application.Platform
import java.util.*

abstract class EngineGameContainer : Application(),
        GameContainerInterface {

    var isLaunched = false

    @Suppress("LeakingThis")
    protected val engine: Java2DEngine = Java2DEngine(this)

    override fun render(fps: Int) = gameEntryPoints.render(fps)
    abstract fun createGames(o: Observer): GameEntryPoints
    abstract override fun update(observable: Observable, arg: Any)

    fun containsGame(gameName: String): Boolean = gameEntryPoints.contains(gameName)

    /**
     * Stops the engine container. Kills every process inside the container.
     */
    override fun stopContainer(toDoBeforeStoppingContainer: () -> Unit) =
            super.stopContainer {
                toDoBeforeStoppingContainer()
                engine.shutdown()
                Platform.exit()
            }

}
