package de.hsh.dennis.model.NpcLogic;

import common.config.WindowConfig;
import javafx.scene.image.Image;

public final class SkinConfig {

    private static final String resourceFolderPath = "/de/hsh/dennis/resources/";
    private static final String skinFolder         = resourceFolderPath + "skins/";

    public static final class Level {
        public static Image level_Background = new Image( resourceFolderPath + "background_Level.png" );

        public static double speed = 1.0;

        public enum Difficulty {EASY, MEDIUM, HARD, NIGHTMARE, CUSTOM}
    }

    public static final class Player {
        private static final String playerPath = skinFolder + "player/";


        public static final String skin_standard_path = playerPath + "player_standard.png";

        public static final String skin_standard_right_path = playerPath + "player_standard_right.png";

        public static final String skin_standard_left_path = playerPath + "player_standard_left.png";


        public static final String skin_hit_left_path = playerPath + "player_hit_left.png";

        public static final String skin_hit_right_path = playerPath + "player_hit_right.png";


        public static final String skin_up_right_path = playerPath + "player_up_right.png";

        public static final String skin_up_left_path = playerPath + "player_up_left.png";


        public static final String skin_down_right_path = playerPath + "player_down_right.png";

        public static final String skin_down_left_path = playerPath + "player_down_left.png";


        static double offsetX = 0;
        static double offsetY = 100;

        public static final int    skin_height = 200;
        public static final int    skin_width  = 200;
        public static final double posX        = (WindowConfig.window_width / 2.0) - (skin_width / 2.0) + offsetX;
        public static final double posY        = (WindowConfig.window_height / 2.0) - (skin_width / 2.0) + offsetY;

        public static double resetDelay = 0.2d;
    }

    public static final class Bot {
        private static final String botFolder          = skinFolder + "bot/";
        public static final  String skin_standard_path = botFolder + "bot.png";
        public static final  int    skin_width         = 50;
        public static final  int    skin_height        = 50;
    }

    public static final class Hacker {
        private static final String hackerFolder       = skinFolder + "hacker/";
        public static final  String skin_standard_path = hackerFolder + "hacker.png";

        public static final int skin_width  = 50;
        public static final int skin_height = 50;
    }

    public static final class Package {
        private static final String packageFolder = skinFolder + "package/";

        public static final String skin_standard_path = packageFolder + "package.png";

        public static final String skin_healing_path = packageFolder + "packageHealing.png";

        public static final int skin_width  = 50;
        public static final int skin_height = 50;
    }
}
