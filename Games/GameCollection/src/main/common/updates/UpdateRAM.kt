package common.updates

import main.common.GameContainer
import common.codes.EngineObserverCodes
import common.logger.Logger
import de.hsh.daniel.RAM

object UpdateRAM {
    fun update(game: RAM,
               arg: Any?,
               gameContainer: GameContainer) {
        Logger.log(UpdateRAM::class.java.toString() + ": " + game,
                   arg)
        if (arg is String) {
            if (EngineObserverCodes.exitToMainGUI == arg) {
                gameContainer.showMainMenu()
                System.gc() //remind the garbage collector. he may trow some unused objects away after
                // the game session should be closed.
            }
        }
    }
}
