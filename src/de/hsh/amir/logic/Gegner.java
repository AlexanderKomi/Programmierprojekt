package de.hsh.amir.logic;

import common.actor.Actor;

public class Gegner extends Actor {

    private static final double START_X = 200;
    private static final double START_Y = 10;
    //    private static final int changePictureDelay = 1;
    private static double GEGNER_SPEED = 3;
    private int gegnerTyp;

    //Konstruktoren
    public Gegner(String pictureFileName, double x, int gegnerTyp) {
        super(pictureFileName, x, START_Y);
        setGegnerTyp(gegnerTyp);
    }

//    public Gegner(String pictureFileName, int gegnerTyp) {
//        super(pictureFileName, START_X, START_Y);
//        setGegnerTyp(gegnerTyp);
//    }
//
//    public Gegner(String pictureFileName, double x, double y, int gegnerTyp) {
//        super(pictureFileName, x, y);
//        setGegnerTyp(gegnerTyp);
//    }
//
//    public Gegner(List<String> pictureFilePaths, double x, double y, int gegnerTyp) {
//        super(pictureFilePaths, x, y, changePictureDelay);
//        setGegnerTyp(gegnerTyp);
//    }
//
//    public Gegner(double x, double y, int gegnerTyp, String... pictureFilePaths) {
//        super(x, y, changePictureDelay, pictureFilePaths);
//        setGegnerTyp(gegnerTyp);
//    }

    public void move() {
        setPos(this.getX(), this.getY() + GEGNER_SPEED);
    }

    public void moveDiagonal() {
        setPos(this.getX() + GEGNER_SPEED / 2, this.getY() + GEGNER_SPEED);
    }

    /**
     * Setter überprüft ob der Gegnertyp richtig gesetzt wurde
     */
    public void setGegnerTyp(int gegnerTyp) {
        if (gegnerTyp == 1) {
            this.gegnerTyp = gegnerTyp;
        } else if (gegnerTyp == 2) {
            this.gegnerTyp = 2;
        } else {
            System.out.println("GEGNERTYP NICHT GESETZT!");
        }
    }

    public int getGegnerTyp() {
        return gegnerTyp;
    }

    /**
     * setzt Gegner Geschwindigkeit auf den übergebenen Wert.
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