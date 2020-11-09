package common.engine.components.game

import common.updates.Updater

internal interface IGame : Updater {
    fun render(fps: Int)
    fun exitToMainGUI()
}
