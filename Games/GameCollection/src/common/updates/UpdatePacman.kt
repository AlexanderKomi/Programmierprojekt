package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.alexander.PacManController

object UpdatePacman {
    fun update(arg: String,
               gameContainer: GameContainer) =
       when (arg) {
           UpdateCodes.PacMan.startGame -> gameContainer.setGameShown(UpdateCodes.PacMan.gameName)
           else                         -> Updater.updateElse(arg, gameContainer)
       }
}
