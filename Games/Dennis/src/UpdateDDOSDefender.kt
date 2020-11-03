import common.engine.GameContainer
import common.updates.GameUpdater
import common.updates.UpdateCodes

object UpdateDDOSDefender : GameUpdater{
    override fun update(arg: String, gameContainer: GameContainer) = when (arg) {
       UpdateCodes.Dennis.gameName -> true
       UpdateCodes.Dennis.gameReady -> true
       else                         -> false
    }
}
