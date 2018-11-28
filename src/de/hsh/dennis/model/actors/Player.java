package de.hsh.dennis.model.actors;

import common.actor.Actor;
import common.actor.Direction;
import javafx.scene.image.Image;

public class Player extends Actor {

    //GameLogic
    private int health = 100;
    private int score = 0;

    //Visuals
    private Image skin_current;
    private final double posX = Config.Player.posX;
    private final double posY = Config.Player.posY;


    public Player() {
        super("player_standard.png", "de/hsh/dennis/resources/actors/Player/");
        setSkinToDefault();
    }

    public void changeSkin(Direction dir) {
        switch (dir) {
            case Left:
                skin_current = Config.Player.skin_left;
                break;
            case Right:
                skin_current = Config.Player.skin_right;
                break;
            case Up:
                skin_current = Config.Player.skin_up;
                break;
            case Down:
                skin_current = Config.Player.skin_down;
                break;

        }
    }

    // --- Utils ----------------------------------------------------------------------------------

    public void damage(int d) {
        if ((getHealth() - d) <= 0) {
            setHealth(0);
        } else {
            setHealth(getHealth() - d);
        }
    }

    public void heal(int h) {
        if ((getHealth() + h) >= 100) {
            setHealth(100);
        } else {
            setHealth(getHealth() + h);
        }
    }


    // --- Getter & Setter ------------------------------------------------------------------------

    public void setSkinToDefault() {
        skin_current = Config.Player.skin_standard;
    }

    public Image getSkin_current() {
        return skin_current;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
