package common.engine.components.game

import common.engine.FxModul
import common.updates.ContainsGameUpdater
import common.updates.MenuCodes
import common.updates.Updater

/***
 * Neue GameEntryPoint Klasse mit FxModul als Unterbau
 *
 * @author Alexander Komischke
 * @author Dennis Sellemann
 *
 * @version 2
 */
abstract class GameEntryPoint(container: Updater, val name: String = "- Name not set -")
    : FxModul(container), IGame, ContainsGameUpdater {

    override fun equals(other: Any?): Boolean =
            if (other is GameEntryPoint) name == other.name && other.scene == scene
            else false

    override fun exitToMainGUI() = super.notifyUpdater(MenuCodes.exitToMainGUI)

    override fun hashCode(): Int = name.hashCode()
}
