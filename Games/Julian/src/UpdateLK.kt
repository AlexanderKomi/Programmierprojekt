package common.updates

import common.engine.GameContainer

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
object UpdateLK : GameUpdater {
    override fun update(arg: String, gameContainer: GameContainer) = when (arg) {
        UpdateCodes.LK.gameName -> {
            gameContainer showGame UpdateCodes.LK.gameName
            true
        }
        UpdateCodes.LK.startGame -> {
            gameContainer showGame UpdateCodes.LK.gameName
            true
        }
        else -> false
    }

}
