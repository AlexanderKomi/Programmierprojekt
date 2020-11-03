package common.updates

import common.GameContainer
import common.util.Logger
import java.util.*

object Updater {
    private const val unknownErrorCode = "ATTENTION : UNKNOWN OBSERVABLE CAN NOT BE PARSED"
    private const val unknownParsingCode = "Unknown String argument: "

    /**
     * @param o
     * The observable notifying this observer
     */
    private fun usingGameUpdater(o: GameUpdater, arg: String, gameContainer: GameContainer) {
        if (!o.update(arg, gameContainer)) {
            when (arg) {
                MenuCodes.exitToMainGUI -> {
                    gameContainer.showMainMenu()
                    System.gc() //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                }
                else -> throw IllegalArgumentException(unknownParsingCode + arg)
            }
        }
    }

    fun update(o: Observable, arg: Any, gameContainer: GameContainer) =
            if (arg is String) {
                if (o is GameUpdater) {
                    usingGameUpdater(o, arg, gameContainer)
                } else {
                    when (arg) {
                        MenuCodes.exitToMainGUI -> gameContainer.showMainMenu()
                        UpdateCodes.MainMenu.shutdown -> gameContainer.stopContainer()
                        in gameContainer -> gameContainer showGame arg
                        else -> {
                            Logger.log(unknownErrorCode)
                            Logger.log(gameContainer, arg)
                        }
                    }
                }
            } else {
                Logger.log("$unknownErrorCode: $arg")
            }
}
