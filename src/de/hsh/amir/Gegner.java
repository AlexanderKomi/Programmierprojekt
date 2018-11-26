package de.hsh.amir;

import common.actor.Actor;

public class Gegner extends Actor {

    protected Gegner(String pictureFileName) {
        super(pictureFileName);
    }

    protected Gegner(String pictureFileName, double x, double y) {
        super(pictureFileName, x, y);
    }

    protected Gegner(String pictureFileName, double x, double y, double height, double width) {
        super(pictureFileName, x, y, height, width);
    }
}
