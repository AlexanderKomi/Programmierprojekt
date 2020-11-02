package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.Julian.LKEntryPoint

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
object UpdateLK {
    fun update(arg: String, gameContainer: GameContainer) = when (arg) {
       UpdateCodes.LK.gameName  -> gameContainer.setGameShown(UpdateCodes.LK.gameName)
       UpdateCodes.LK.startGame -> gameContainer.setGameShown(UpdateCodes.LK.gameName)
       else                       -> Updater.updateElse(arg, gameContainer)
    }

}
