package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.daniel.RAM

object UpdateRAM {
    fun update(arg: String,
               gameContainer: GameContainer) {
        if (MenuCodes.exitToMainGUI == arg) {
            Updater.updateElse(arg, gameContainer)
        }
    }
}
