package common.updates

import main.common.GameContainer
import common.codes.EngineObserverCodes
import common.logger.Logger
import de.hsh.dennis.DennisGameEntryPoint

object UpdateDDOSDefender {
    fun update(dennisGameEntryPoint: DennisGameEntryPoint?,
               arg: Any?,
               gameContainer: GameContainer) {
        if (arg is String) {
            val message = arg
            when (message) {
                UpdateCodes.Dennis.gameName  -> {
                }
                UpdateCodes.Dennis.gameReady -> {
                }
                EngineObserverCodes.exitToMainGUI      -> {
                    gameContainer.showMainMenu()
                    System.gc() //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                }
                else                         -> throw IllegalArgumentException(
                        Updater.unkownParsingCode + message)
            }
        } else {
            Logger.log(dennisGameEntryPoint, arg)
        }
    }
}
