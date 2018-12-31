package de.hsh.dennis.model.NpcLogic;

import common.config.WindowConfig;
import javafx.scene.image.Image;

public final class SkinConfig {

    private static final String resourceFolderPath = "de/hsh/dennis/resources/";
    private static final String actorFolder        = resourceFolderPath + "actors/";

    public static class Level {
        public static Image level_Background = new Image( resourceFolderPath + "background_Level.png" );

        public static double speed = 1.0;

        public enum Difficulty {EASY, MEDIUM, HARD, NIGHTMARE, CUSTOM}
    }

    public static class Player {
        private static final String playerPath    = actorFolder + "player/";
        public static        Image  skin_standard = new Image( playerPath + "player_standard.png" );

        public static Image skin_standard_right = new Image( playerPath + "player_standard_right.png" );
        public static Image skin_standard_left  = new Image( playerPath + "player_standard_left.png" );

        public static Image skin_hit_left  = new Image( playerPath + "player_hit_left.png" );
        public static Image skin_hit_right = new Image( playerPath + "player_hit_right.png" );

        public static Image skin_up_right = new Image( playerPath + "player_up_right.png" );
        public static Image skin_up_left  = new Image( playerPath + "player_up_left.png" );

        public static Image skin_down_right = new Image( playerPath + "player_down_right.png" );
        public static Image skin_down_left  = new Image( playerPath + "player_down_left.png" );


        static double offsetX = 0;
        static double offsetY = 100;

        public static double posX = (WindowConfig.window_width / 2.0) - ((int) skin_standard.getWidth() / 2.0) + offsetX;
        public static double posY = (WindowConfig.window_height / 2.0) - ((int) skin_standard.getHeight() / 2.0) + offsetY;

        public static double resetDelay = 0.2d;
    }

    public static class Bot {
        private static final String botFolder          = actorFolder + "bot/";
        static final         String skin_standard_path = botFolder + "bot.png";
        public static        Image  skin_standard      = new Image( skin_standard_path );
    }

    public static class Hacker {
        private static final String hackerFolder       = actorFolder + "hacker/";
        static final         String skin_standard_path = hackerFolder + "hacker.png";
        public static        Image  skin_standard      = new Image( skin_standard_path );
    }

    public static class Package {
        private static final String packageFolder = actorFolder + "package/";

        private static final String skin_standard_path = packageFolder + "package.png";
        public static        Image  skin_standard      = new Image( skin_standard_path );

        private static final String skin_healing_path = packageFolder + "packageHealing.png";
        public static        Image  skin_healing      = new Image( skin_healing_path );
    }
}
