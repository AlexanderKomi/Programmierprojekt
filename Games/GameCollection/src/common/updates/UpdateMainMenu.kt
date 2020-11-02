package common.updates

import common.GameContainer
import common.MainMenu
import common.util.Logger

object UpdateMainMenu {
    fun update(arg: String,
               gameContainer: GameContainer) {
        if (gameContainer.containsGame(arg)) {
            gameContainer.setGameShown(arg)
        } else if (arg == UpdateCodes.MainMenu.shutdown) {
            gameContainer.stopContainer()
        }
    }
}
