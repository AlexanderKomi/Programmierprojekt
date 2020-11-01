package common.updates

import main.common.GameContainer
import common.MainMenu
import common.engine.components.game.GameEntryPoint
import common.logger.Logger
import de.hsh.Julian.LKEntryPoint
import de.hsh.alexander.PacManController
import de.hsh.amir.AmirEntryPoint
import de.hsh.daniel.RAM
import de.hsh.dennis.DennisGameEntryPoint
import de.hsh.kevin.controller.TIController
import java.util.*

object Updater {
    private const val unknownErrorCode = "ATTENTION : UNKNOWN OBSERVABLE OF TYPE GAME IS NOT PARSED"
    const val unkownParsingCode = "Unknown String argument: "
    const val parsingErrorCode = "Can not parse : "

    /**
     * Cast observables to the correct type, and call the correct method.<br></br>
     * Why?<br></br>
     * When "notifyObservers()" is called, the argument is always casted to observable.<br></br>
     * This is why this method is necessary.<br></br>
     *
     * @param o
     * The observable notifying this observer
     */
    fun update(o: Observable,
               arg: Any,
               gameContainer: GameContainer) {
        when (o) {
            is GameEntryPoint -> {
                when (o) {
                    is PacManController -> {
                        UpdatePacman.update(o,
                                            arg,
                                            gameContainer)
                    }
                    is AmirEntryPoint -> {
                        UpdateAmirsGame.update(o,
                                               arg,
                                               gameContainer)
                    }
                    is RAM -> {
                        UpdateRAM.update(o,
                                         arg,
                                         gameContainer)
                    }
                    is DennisGameEntryPoint -> {
                        UpdateDDOSDefender.update(o,
                                                  arg,
                                                  gameContainer)
                    }
                    is LKEntryPoint -> {
                        UpdateLK.update(o,
                                        arg,
                                        gameContainer)
                    }
                    is TIController -> {
                        UpdateTunnelInvader.update(o,
                                                   arg,
                                                   gameContainer)
                    }
                    else                    -> {
                        Logger.log(unknownErrorCode + " type : " + GameEntryPoint::class.java)
                    }
                }
            }
            is MainMenu -> {
                UpdateMainMenu.update(o as MainMenu?, arg, gameContainer)
            }
            else              -> {
                Logger.log(unknownErrorCode)
                Logger.log(o, arg)
            }
        }
    }

}
