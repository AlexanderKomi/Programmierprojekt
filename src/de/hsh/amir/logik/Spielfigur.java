package de.hsh.amir.logik;


import common.actor.ControlableActor;
import common.actor.Direction;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class Spielfigur extends ControlableActor{

    protected Spielfigur(String pictureFileName, HashMap<String, Direction> keymap) throws FileNotFoundException {
        super(pictureFileName, keymap);
    }

    protected Spielfigur(String pictureFileName, double x, double y, HashMap<String, Direction> keymap) throws FileNotFoundException {
        super(pictureFileName, x, y, keymap);
    }

    protected Spielfigur(List<String> pictureFileName, double x, double y, HashMap<String, Direction> keymap, int delay) throws FileNotFoundException {
        super(pictureFileName, x, y, keymap, delay);
    }

    protected Spielfigur(double x, double y, HashMap<String, Direction> keymap, int delay, String... pictureFileNames) throws FileNotFoundException {
        super(x, y, keymap, delay, pictureFileNames);
    }
}