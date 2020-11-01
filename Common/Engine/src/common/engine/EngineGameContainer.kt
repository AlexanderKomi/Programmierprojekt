package common.engine

import common.engine.components.game.GameEntryPoints

class EngineGameContainer(val gameEntryPoints: GameEntryPoints) : GameContainerInterface{

    @Suppress("LeakingThis")
    private val engine: Java2DEngine = Java2DEngine(this)

    override fun render(fps: Int) = gameEntryPoints.render(fps)

    fun containsGame(gameName: String): Boolean = gameEntryPoints.contains(gameName)
    fun startEngine() = engine.start()
    fun stopEngine() = engine.shutdown()
}
