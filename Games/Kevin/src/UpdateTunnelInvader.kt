package common.updates

import common.engine.GameContainer

object UpdateTunnelInvader : GameUpdater {
    override fun update(arg: String, gameContainer: GameContainer): Boolean = when (arg) {
        UpdateCodes.TunnelInvader.startGame -> {
            gameContainer.showGame(UpdateCodes.TunnelInvader.gameName)
            true
        }
        else -> false
    }
}
