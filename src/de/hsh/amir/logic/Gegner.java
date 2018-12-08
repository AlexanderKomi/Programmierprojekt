package de.hsh.amir.logic;

import common.actor.Collectable;

import java.io.FileNotFoundException;
import java.util.List;

public class Gegner extends Collectable {

    private static final double startX = 100;
    private static final double startY = 10;
    private static final int changePictureDelay = 15;
    private double speed = 3;
    private int gegnerTyp;

    //Konstruktoren
    public Gegner(String pictureFileName, int gegnerTyp) throws FileNotFoundException {
        super(pictureFileName, startX, startY);
        setGegnerTyp(gegnerTyp);
    }

    public Gegner(String pictureFileName, double x, double y, int gegnerTyp) throws FileNotFoundException {
        super(pictureFileName, x, y);
        setGegnerTyp(gegnerTyp);
    }

    public Gegner(List<String> pictureFilePaths, double x, double y, int gegnerTyp) throws FileNotFoundException {
        super(pictureFilePaths, x, y, changePictureDelay);
        setGegnerTyp(gegnerTyp);
    }

    public Gegner(double x, double y, int gegnerTyp, String... pictureFilePaths) throws FileNotFoundException {
        super(x, y, changePictureDelay, pictureFilePaths);
        setGegnerTyp(gegnerTyp);
    }

    public void move() {
        setPos(this.getX(), this.getY() + speed);
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

    @Override
    public String toString() {
        return "Ich bin ein GegnerObjekt aus AmirsGame";
    }
}