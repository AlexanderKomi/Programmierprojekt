package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.dennis.DennisGameEntryPoint

object UpdateDDOSDefender {
    fun update(arg: String, gameContainer: GameContainer) = when (arg) {
       UpdateCodes.Dennis.gameName  -> {}
       UpdateCodes.Dennis.gameReady -> {}
       else                         -> Updater.updateElse(arg, gameContainer)
    }
}
