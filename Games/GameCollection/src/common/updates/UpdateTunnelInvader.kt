package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.kevin.controller.TIController

object UpdateTunnelInvader {
    fun update(arg: String, gameContainer: GameContainer) = when (arg) {
       UpdateCodes.TunnelInvader.startGame -> gameContainer.setGameShown(UpdateCodes.TunnelInvader.gameName)
       else                       -> Updater.updateElse(arg, gameContainer)
    }
}
