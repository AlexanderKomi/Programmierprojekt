package de.hsh.dennis.model.actors;

import common.actor.Direction;
import common.config.WindowConfig;
import javafx.scene.image.Image;

public class Player {

    private Image skin_standard = new Image("de/hsh/dennis/resources/actors/player_standard.png");


    private Image skin_left = new Image("de/hsh/dennis/resources/actors/player_left.png");
    private Image skin_right = new Image("de/hsh/dennis/resources/actors/player_right.png");
    private Image skin_up = new Image("de/hsh/dennis/resources/actors/player_up.png");
    private Image skin_down = new Image("de/hsh/dennis/resources/actors/player_down.png");

    private int offsetX = 0;
    private int offsetY = 200;

    private final int posX = (WindowConfig.window_width / 2) - ((int) skin_standard.getWidth() / 2) + offsetX;
    private final int posY = (WindowConfig.window_height / 2) - ((int) skin_standard.getHeight() / 2) + offsetY;

    private Image skin_current;

    public Player() {
        setSkinToDefault();
    }

    public void changeSkin(Direction dir) {
        switch (dir) {
            case Left:
                skin_current = skin_left;
                break;
            case Right:
                skin_current = skin_right;
                break;
            case Up:
                skin_current = skin_up;
                break;
            case Down:
                skin_current = skin_down;
                break;

        }
    }

    public void setSkinToDefault() {
        skin_current = skin_standard;
    }

    public Image getSkin_current() {
        return skin_current;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
