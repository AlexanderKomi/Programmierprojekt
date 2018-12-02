package de.hsh.dennis.model.actors;

import common.config.WindowConfig;
import javafx.scene.image.Image;

public class Config {

    public static class Level {
        static double speed = 1.0;

        public enum Difficulty {EASY, MEDIUM, HARD, NIGHTMARE, CUSTOM}
    }

    static class Player {
        static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Player/player_standard.png");
        static Image skin_left = new Image("de/hsh/dennis/resources/actors/Player/player_left.png");
        static Image skin_right = new Image("de/hsh/dennis/resources/actors/Player/player_right.png");
        static Image skin_up = new Image("de/hsh/dennis/resources/actors/Player/player_up.png");
        static Image skin_down = new Image("de/hsh/dennis/resources/actors/Player/player_down.png");

        static double offsetX = 0;
        static double offsetY = 100;

        static double posX = (WindowConfig.window_width / 2.0) - ((int) skin_standard.getWidth() / 2.0) + offsetX;
        static double posY = (WindowConfig.window_height / 2.0) - ((int) skin_standard.getHeight() / 2.0) + offsetY;
    }

    static class Bot {
        static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Bot/bot_standard.png");
    }

    static class Hacker {
        static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Bot/bot_standard.png");
    }

    static class Package {
        static Image skin_standard = new Image("de/hsh/dennis/resources/actors/Bot/bot_standard.png");
    }
}
