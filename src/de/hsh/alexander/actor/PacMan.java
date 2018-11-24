package de.hsh.alexander.actor;

import common.actor.ControlableActor;
import common.actor.Direction;

import java.util.HashMap;

public class PacMan extends ControlableActor {


    private static final double start_x       = 100;
    private static final double start_y       = 100;
    private static final int    default_speed = 10;


    public PacMan( String pictureFileName, HashMap<String, Direction> keyMap ) {
        super( pictureFileName, start_x, start_y, keyMap );
        this.setSpeed( default_speed );
    }

    public PacMan( String pictureFileName, double x, double y, HashMap<String, Direction> keyMap ) {
        super( pictureFileName, x, y, keyMap );
    }

}
