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

    public static final class Dennis {
	public static final String gameName = WindowConfig.dennis_title;
	public static final String startGame = "Start GameEntryPoint";
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
    }

    public static final class RAM {
        public static final String startGame = "Start Game";
        public static final String mainMenu  = "Mainmenu";
        public static final String fieldSize = "Field size changed";
    }

}
