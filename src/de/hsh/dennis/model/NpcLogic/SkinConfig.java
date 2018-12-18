package de.hsh.dennis.model.NpcLogic;

import common.config.WindowConfig;
import javafx.scene.image.Image;

public class SkinConfig {

    public static class Level {
        public static double speed = 1.0;

        public enum Difficulty {EASY, MEDIUM, HARD, NIGHTMARE, CUSTOM}
    }

    public static class Player {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Player/player_standard.png");
        public static Image skin_left = new Image("de/hsh/dennis/resources/actors/Player/player_left.png");
        public static Image skin_right = new Image("de/hsh/dennis/resources/actors/Player/player_right.png");
        public static Image skin_up = new Image("de/hsh/dennis/resources/actors/Player/player_up.png");
        public static Image skin_down = new Image("de/hsh/dennis/resources/actors/Player/player_down.png");

        public static double offsetX = 0;
        public static double offsetY = 100;

        public static double posX = (WindowConfig.window_width / 2.0) - ((int) skin_standard.getWidth() / 2.0) + offsetX;
        public static double posY = (WindowConfig.window_height / 2.0) - ((int) skin_standard.getHeight() / 2.0) + offsetY;
    }

    public static class Bot {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Bot/bot_standard.png");
    }

    public static class Hacker {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Bot/bot_standard.png");
    }

    public static class Package {
        public static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Bot/bot_standard.png");
    }
}
