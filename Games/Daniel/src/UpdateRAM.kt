package common.updates

import common.engine.GameContainer

object UpdateRAM : GameUpdater {
    override fun update(arg: String, gameContainer: GameContainer): Boolean = MenuCodes.exitToMainGUI != arg
}
