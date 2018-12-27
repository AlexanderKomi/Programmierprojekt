package de.hsh.amir.logic;

import common.actor.Actor;

public final class Gegner extends Actor {

    private static final double START_Y = 10;
    private static double GEGNER_SPEED = 3;

    private static final String enemyPicture = "/de/hsh/amir/resources/enemyFigur.png";

    Gegner( final double x ) {
        super( enemyPicture, x, START_Y );
    }

    Gegner( String enemyPicture, final double x ) {
        super( enemyPicture, x, START_Y );
    }

    public final void move() {
        setPos(this.getX(), this.getY() + GEGNER_SPEED);
    }

    void moveDiagonal() {
        setPos(this.getX() + GEGNER_SPEED / 4, this.getY() + GEGNER_SPEED);
    }

    /**
     * Setzt Gegnergeschwindigkeit GLOBAL!!! auf den übergebenen Wert.
     * @param speed
     */
    public static void setGegnerSpeed(double speed) {
        GEGNER_SPEED = speed;
    }
}