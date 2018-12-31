package de.hsh.dennis.model.NpcLogic;

import common.config.WindowConfig;
import javafx.scene.image.Image;

public class SkinConfig {

    public static class Level {
        public static Image level_Background = new Image("de/hsh/dennis/resources/background_Level.png");

        public static double speed = 1.0;

        public enum Difficulty {EASY, MEDIUM, HARD, NIGHTMARE, CUSTOM}
    }

    public static class Player {

        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/player/player_standard.png");

        public static Image skin_standard_right = new Image("de/hsh/dennis/resources/actors/player/player_standard_right.png");
        public static Image skin_standard_left = new Image("de/hsh/dennis/resources/actors/player/player_standard_left.png");

        public static Image skin_hit_left = new Image("de/hsh/dennis/resources/actors/player/player_hit_left.png");
        public static Image skin_hit_right = new Image("de/hsh/dennis/resources/actors/player/player_hit_right.png");

        public static Image skin_up_right = new Image("de/hsh/dennis/resources/actors/player/player_up_right.png");
        public static Image skin_up_left = new Image("de/hsh/dennis/resources/actors/player/player_up_left.png");

        public static Image skin_down_right = new Image("de/hsh/dennis/resources/actors/player/player_down_right.png");
        public static Image skin_down_left = new Image("de/hsh/dennis/resources/actors/player/player_down_left.png");


        public static double offsetX = 0;
        public static double offsetY = 100;

        public static double posX = (WindowConfig.window_width / 2.0) - ((int) skin_standard.getWidth() / 2.0) + offsetX;
        public static double posY = (WindowConfig.window_height / 2.0) - ((int) skin_standard.getHeight() / 2.0) + offsetY;

        public static double resetDelay = 0.2d;
    }

    public static class Bot {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/bot/bot.png");
    }

    public static class Hacker {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/hacker/hacker.png");
    }

    public static class Package {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/package/package.png");
        public static Image skin_healing = new Image("de/hsh/dennis/resources/actors/package/packageHealing.png");
    }
}
