package common.updates

import main.common.GameContainer
import common.MainMenu
import common.logger.Logger

object UpdateMainMenu {
    fun update(menu: MainMenu?,
               arg: Any?,
               gameContainer: GameContainer) {
        if (arg is String) {
            if (gameContainer.containsGame(arg)) {
                gameContainer.setGameShown(arg)
            } else if (arg == UpdateCodes.MainMenu.shutdown) {
                gameContainer.stopContainer()
            }
        } else {
            Logger.log(Updater.parsingErrorCode + " update(MainMenu, Object)")
        }
    }
}
