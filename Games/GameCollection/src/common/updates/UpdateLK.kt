package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.Julian.LKEntryPoint

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
object UpdateLK {
    fun update(LKGame: LKEntryPoint?,
               arg: Any?,
               gameContainer: GameContainer) {
        if (arg is String) {
            when (arg) {
                UpdateCodes.LK.gameName  -> {
                    gameContainer.setGameShown(UpdateCodes.LK.gameName)
                }
                UpdateCodes.LK.startGame -> {
                    gameContainer.setGameShown(UpdateCodes.LK.gameName)
                }
                MenuCodes.exitToMainGUI  -> {
                    gameContainer.showMainMenu()
                    System.gc() //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                }
                else                     -> throw IllegalArgumentException(
                        Updater.unkownParsingCode + arg)
            }
        } else {
            Logger.log(LKGame, arg)
        }
    }
}
