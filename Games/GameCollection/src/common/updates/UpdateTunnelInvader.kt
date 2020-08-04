package common.updates

import common.GameContainer
import common.util.Logger
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
                MenuCodes.exitToMainGUI             -> gameContainer.showMainMenu()
                else                                -> throw IllegalArgumentException(
                        Updater.unkownParsingCode + message)
            }
        } else {
            Logger.log(tiController, arg)
        }
    }
}
