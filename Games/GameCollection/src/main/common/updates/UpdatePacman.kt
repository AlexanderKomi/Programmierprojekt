package common.updates

import main.common.GameContainer
import common.codes.EngineObserverCodes
import common.logger.Logger
import de.hsh.alexander.PacManController

object UpdatePacman {
    fun update(pacManController: PacManController,
               arg: Any?,
               gameContainer: GameContainer) {
        if (arg is String) {
            when (arg) {
                UpdateCodes.PacMan.startGame -> gameContainer.setGameShown(UpdateCodes.PacMan.gameName)
                EngineObserverCodes.exitToMainGUI      -> gameContainer.showMainMenu()
                else                         -> throw IllegalArgumentException(
                        Updater.unkownParsingCode + arg)
            }
        } else {
            Logger.log(UpdatePacman::class.java.toString() + " : Unknown Arguments :  " + pacManController, arg)
        }
    }
}
