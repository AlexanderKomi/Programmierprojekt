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
abstract class GameEntryPoint(container: Observer, val name: String = "- Name not set -")
    : FxModul(container), IGame {

    override fun equals(other: Any?): Boolean {
        if (other is GameEntryPoint) {
            return name == other.name && other.scene == scene
        }
        return false
    }

    override fun exitToMainGUI() {
        setChanged()
        super.notifyObservers(MenuCodes.exitToMainGUI)
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}
