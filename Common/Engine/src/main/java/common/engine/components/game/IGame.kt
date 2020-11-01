package common.engine.components.game

import java.util.*

internal interface IGame : Observer {
    fun render(fps: Int)
    fun exitToMainGUI()
    override fun update(o: Observable,
                        arg: Any)
}
