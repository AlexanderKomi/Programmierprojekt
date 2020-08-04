package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.amir.AmirEntryPoint

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
object UpdateAmirsGame {
    fun update(amirGame: AmirEntryPoint?,
               arg: Any?,
               gameContainer: GameContainer) {
        if (arg is String) {
            when (arg) {
                UpdateCodes.Amir.gameName  -> {
                }
                UpdateCodes.Amir.startGame -> {
                }
                MenuCodes.exitToMainGUI    -> {
                    gameContainer.showMainMenu()
                    System.gc() //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                }
                else                       -> throw IllegalArgumentException(
                        Updater.unkownParsingCode + arg)
            }
        } else {
            if (arg != null) {
                if (amirGame != null) {
                    Logger.log(amirGame, arg)
                }
            }
        }
    }
}
