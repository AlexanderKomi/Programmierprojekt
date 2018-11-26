package de.hsh.amir;

import common.actor.Actor;

public class Spielfigur extends Actor{

    Spielfigur(String pictureFileName) {
        super(pictureFileName);
    }

    Spielfigur(String pictureFileName, double x, double y) {
        super(pictureFileName, x, y);
    }

    Spielfigur(String pictureFileName, double x, double y, double height, double width) {
        super(pictureFileName, x, y, height, width);
    }
}
