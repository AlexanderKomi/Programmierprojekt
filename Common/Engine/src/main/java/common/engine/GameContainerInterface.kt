package common.engine

import common.engine.components.game.GameEntryPoints
import java.util.*

interface GameContainerInterface : Observer {
    companion object {
        var gameEntryPoints = GameEntryPoints()
    }

    /**
     * Start the new game. Must be called from the class launching the application!
     *
     * @param args
     * Program arguments
     */
    fun startContainer(args: Array<String>)

    /**
     * Gets called every time a new frame is rendered.
     * Use this to update every frame.
     * @param fps
     */
    fun render(fps: Int)
    override fun update(observable: Observable,
                        arg: Any)

    /**
     * Stops the engine container. Kills every process inside the container.
     */
    fun stopContainer(toDoBeforeStoppingContainer : () -> Unit = {}) {
        beforeStoppingContainer()
        toDoBeforeStoppingContainer()
    }

    fun beforeStoppingContainer()
}
