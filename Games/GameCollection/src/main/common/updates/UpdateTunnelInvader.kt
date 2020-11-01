package common.updates

import main.common.GameContainer
import common.codes.EngineObserverCodes
import common.logger.Logger
import de.hsh.kevin.controller.TIController

object UpdateTunnelInvader {
    fun update(tiController: TIController?,
               arg: Any?,
               gameContainer: GameContainer) {
        if (arg is String) {
            val message = arg
            when (message) {
                UpdateCodes.TunnelInvader.startGame -> gameContainer.setGameShown(
                        UpdateCodes.TunnelInvader.gameName)
                EngineObserverCodes.exitToMainGUI             -> gameContainer.showMainMenu()
                else                                -> throw IllegalArgumentException(
                        Updater.unkownParsingCode + message)
            }
        } else {
            Logger.log(tiController, arg)
        }
    }
}
