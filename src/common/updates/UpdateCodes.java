package common.updates;

import common.config.WindowConfig;

public class UpdateCodes {

    public class DefaultCodes {
        public static final String exitToMainGUI = "exitToMainGui";
    }

    public class MainMenu {
        public static final String shutdown = "Shutdown";
    }

    public class PacMan {
        public static final String gameName      = WindowConfig.alexander_title;
        public static final String startGame     = gameName + ": Start GameEntryPoint";
        public static final String mainMenu      = gameName + ": Mainmenu";
        public static final String showEndScreen = gameName + ":  Show Endscreen";
        public static final String repeatGame    = gameName + ": RepeatGame";
    }

    public class Dennis {
        public static final String gameName  = WindowConfig.dennis_title;
        public static final String startGame = "Start GameEntryPoint";
    }

    public class TunnelInvader {
        public static final String gameName  = WindowConfig.kevin_title;
        public static final String startGame = "Start GameEntryPoint";
        public static final String gameOver  = "GameEntryPoint Over";
        public static final String gameMenu  = "Game Menu";
    }

    public class Amir {
        public static final String gameName  = WindowConfig.amir_title;
        public static final String startGame = "Start GameEntryPoint";
    }

    public class RAM {
        public static final String startGame = "Start Game";
        public static final String mainMenu  = "Mainmenu";
        public static final String fieldSize = "Field size changed";
    }

}
