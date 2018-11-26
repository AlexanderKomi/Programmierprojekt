package de.hsh.dennis.model.actors;

import common.actor.Direction;
import javafx.scene.image.Image;

public class Player {

    private Image skin_standard = new Image("de/hsh/dennis/resources/actors/player_standard.png");


    private Image skin_left = new Image("de/hsh/dennis/resources/actors/player_left.png");
    private Image skin_right = new Image("de/hsh/dennis/resources/actors/player_right.png");
    private Image skin_up = new Image("de/hsh/dennis/resources/actors/player_up.png");
    private Image skin_down = new Image("de/hsh/dennis/resources/actors/player_down.png");

    private final int posX = 600;
    private final int posY = 400;

    private Image skin_current;

    public Player() {
        skin_current = skin_standard;
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
