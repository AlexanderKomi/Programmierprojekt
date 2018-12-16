package de.hsh.amir.logic;

import common.actor.Actor;

public class Gegner extends Actor {

    private static final double START_Y = 10;
    private static double GEGNER_SPEED = 3;

    public Gegner(String pictureFileName, double x) {
        super(pictureFileName, x, START_Y);
    }

    public void move() {
        setPos(this.getX(), this.getY() + GEGNER_SPEED);
    }

    public void moveDiagonal() {
        setPos(this.getX() + GEGNER_SPEED / 4, this.getY() + GEGNER_SPEED);
    }

    /**
     * setzt Gegnergeschwindigkeit GLOBAL!!! auf den übergebenen Wert.
     *
     * @param speed
     */
    public static void setGegnerSpeed(double speed) {
        GEGNER_SPEED = speed;
    }

    public static double getGegnerSpeed() {
        return GEGNER_SPEED;
    }
}