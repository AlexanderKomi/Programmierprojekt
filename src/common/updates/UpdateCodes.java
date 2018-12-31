package common.updates;

import common.config.WindowConfig;

public final class UpdateCodes {

    public static final class DefaultCodes {
        public static final String exitToMainGUI = "exitToMainGui";
    }

    public static final class MainMenu {
        public static final String shutdown = "Shutdown";
    }

    public static final class PacMan {
        public static final String gameName = WindowConfig.alexander_title;
        public static final String startGame = gameName + ": Start GameEntryPoint";
        public static final String mainMenu = gameName + ": Mainmenu";
        public static final String showEndScreen = gameName + ":  Show Endscreen";
        public static final String repeatGame = gameName + ": RepeatGame";
    }

    public class Dennis {
	public static final String gameName = WindowConfig.dennis_title;
        public static final String gameReady = "Start Game";
        public static final String gameNotReady = "Don't start";
        public static final String gameLost = "Game lost";
        public static final String gameWon = "Game Won";
        public static final String replay = "replay";
        public static final String continiue = "continiue";
    }

    public static final class TunnelInvader {
        public static final String gameName = WindowConfig.kevin_title;
        public static final String startGame = gameName + " Start GameEntryPoint";
        public static final String playGame = gameName + " Start Game";
        public static final String gameOver = gameName + " Game Over";
        public static final String gameMenu = gameName + " Game Menu";
    }

    public static final class Amir {
        public static final String gameName = WindowConfig.amir_title;
        public static final String startGame = "Start GameEntryPoint";
        public static final String mainMenu = gameName + ": Mainmenu";
        public static final String showEndScreen = gameName + ":  Show Endscreen";
        public static final String repeatGame = gameName + ": RepeatGame";
    }

    public static final class RAM {
        public static final String gameName = WindowConfig.daniel_title;
        public static final String startGame = "Start Game";
        public static final String mainMenu = "MainMenu";
        public static final String fieldSize = "Field size changed";
        public static final String p1Win = "Player 1 wins";
        public static final String p2Win = "Player 2 wins";
        public static final String tie = "It's a tie";
        public static final String quit = "Quit to MainGUI";
    }

    public static final class LK {
        public static final String gameName = WindowConfig.julian_title;
        public static final String startGame = gameName + " Start GameEntryPoint";
        public static final String playGame = gameName + " Start Game";
        public static final String gameOver = gameName + " Game Over";
        public static final String gameMenu = gameName + " Game Menu";
    }

}
