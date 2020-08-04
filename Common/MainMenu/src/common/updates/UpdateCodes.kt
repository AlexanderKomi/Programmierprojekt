package common.updates

import common.config.WindowConfig

class UpdateCodes {
    object MainMenu {
        const val shutdown = "Shutdown"
    }

    object PacMan {
        const val gameName = WindowConfig.alexander_title
        const val startGame = "$gameName: Start GameEntryPoint"
        const val mainMenu = "$gameName: Mainmenu"
        const val showEndScreen = "$gameName:  Show Endscreen"
        const val repeatGame = "$gameName: RepeatGame"
    }

    object Dennis {
        const val gameName = WindowConfig.dennis_title
        const val gameReady = "Start Game"
        const val gameNotReady = "Don't start"
        const val gameLost = "Game lost"
        const val gameWon = "Game Won"
        const val replay = "replay"
        const val continiue = "continiue"
    }

    object TunnelInvader {
        const val gameName = WindowConfig.kevin_title
        const val startGame = "$gameName Start GameEntryPoint"
        const val playGame = "$gameName Start Game"
        const val gameOver = "$gameName Game Over"
        const val gameMenu = "$gameName Game Menu"
    }

    object Amir {
        const val gameName = WindowConfig.amir_title
        const val startGame = "Start GameEntryPoint"
        const val mainMenu = "$gameName: Mainmenu"
        const val showEndScreen = "$gameName:  Show Endscreen"
        const val repeatGame = "$gameName: RepeatGame"
    }

    object RAM {
        const val gameName = WindowConfig.daniel_title
        const val startGame = "Start Game"
        const val mainMenu = "MainMenu"
        const val fieldSize = "Field size changed"
        const val p1Win = "Player 1 wins"
        const val p2Win = "Player 2 wins"
        const val tie = "It's a tie"
        const val quit = "Quit to MainGUI"
    }

    object LK {
        const val gameName = WindowConfig.julian_title
        const val startGame = "$gameName Start GameEntryPoint"
        const val playGame = "$gameName Start Game"
        const val gameOver = "$gameName Game Over"
        const val gameMenu = "$gameName Game Menu"
    }
}
