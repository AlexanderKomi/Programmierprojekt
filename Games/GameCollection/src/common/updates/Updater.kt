package common.updates

import common.GameContainer
import common.MainMenu
import common.engine.components.game.GameEntryPoint
import common.util.Logger
import de.hsh.Julian.LKEntryPoint
import de.hsh.alexander.PacManController
import de.hsh.amir.AmirEntryPoint
import de.hsh.daniel.RAM
import de.hsh.dennis.DennisGameEntryPoint
import de.hsh.kevin.controller.TIController
import java.util.*

object Updater {
    const val unknownErrorCode = "ATTENTION : UNKNOWN OBSERVABLE CAN NOT BE PARSED"
    private const val unknownParsingCode = "Unknown String argument: "

    /**
     * Cast observables to the correct type, and call the correct method.<br></br>
     * Why?<br></br>
     * When "notifyObservers()" is called, the argument is always casted to observable.<br></br>
     * This is why this method is necessary.<br></br>
     *
     * @param o
     * The observable notifying this observer
     */
    fun update(o: Observable, arg: String, gameContainer: GameContainer) = when (o) {
        is PacManController -> UpdatePacman.update(arg, gameContainer)
        is AmirEntryPoint -> UpdateAmirsGame.update(arg, gameContainer)
        is RAM -> UpdateRAM.update(arg, gameContainer)
        is DennisGameEntryPoint -> UpdateDDOSDefender.update(arg, gameContainer)
        is LKEntryPoint -> UpdateLK.update(arg, gameContainer)
        is TIController -> UpdateTunnelInvader.update(arg, gameContainer)
        is MainMenu -> UpdateMainMenu.update(arg, gameContainer)
        else -> {
            Logger.log(unknownErrorCode)
            Logger.log(o, arg)
        }
    }

    fun updateElse(arg: String, gameContainer: GameContainer) = when(arg){
        MenuCodes.exitToMainGUI -> {
            gameContainer.showMainMenu()
            System.gc() //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
        }
        else -> throw IllegalArgumentException(unknownParsingCode + arg)
    }
}
