package de.hsh.kevin.logic.myActor;

import common.actor.ControlableActor;
import common.actor.Direction;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Player extends ControlableActor {

    protected Player( String pictureFileName, double x, double y,
                      HashMap<String, Direction> keymap ) throws FileNotFoundException {
        super( pictureFileName, x, y, keymap );
    }

}
