package common.updates

import common.engine.GameContainer


object UpdatePacman : GameUpdater {
    override fun update(arg: String, gameContainer: GameContainer) =
            when (arg) {
                UpdateCodes.PacMan.startGame -> {
                    gameContainer.showGame(UpdateCodes.PacMan.gameName)
                    true
                }
                else -> false
            }
}
