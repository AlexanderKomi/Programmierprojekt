package common.engine.components.game

import common.engine.FxModul
import common.updates.MenuCodes
import java.util.*

/***
 * Neue GameEntryPoint Klasse mit FxModul als Unterbau
 *
 * @author Alexander Komischke
 * @author Dennis Sellemann
 *
 * @version 2
 */
abstract class GameEntryPoint @JvmOverloads constructor(container: Observer?,
        // ----------------------------------- GETTER & SETTER  -----------------------------------
                                                        val name: String = "- Name not set -") :
        FxModul(container!!),
        IGame {
    override fun equals(obj: Any?): Boolean {
        if (obj is GameEntryPoint) {
            val g = obj
            return name == g.name && g.scene == scene
        }
        return false
    }

    override fun exitToMainGUI() {
        setChanged()
        super.notifyObservers(MenuCodes.exitToMainGUI)
    }

}
