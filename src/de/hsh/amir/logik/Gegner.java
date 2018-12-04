package de.hsh.amir.logik;

import common.actor.Collectable;

import java.io.FileNotFoundException;
import java.util.List;

public class Gegner extends Collectable {

    protected Gegner(String pictureFileName) throws FileNotFoundException {
        super(pictureFileName);
    }

    public Gegner(String pictureFileName, double x, double y) throws FileNotFoundException {
        super(pictureFileName, x, y);
    }

    protected Gegner(List<String> pictureFilePaths, double x, double y, int delay) throws FileNotFoundException {
        super(pictureFilePaths, x, y, delay);
    }

    protected Gegner(double x, double y, int delay, String... pictureFilePaths) throws FileNotFoundException {
        super(x, y, delay, pictureFilePaths);
    }
}
