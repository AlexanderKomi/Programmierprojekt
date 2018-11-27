package de.hsh.alexander.actor;

import common.actor.ControlableActor;
import common.actor.Direction;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class PacMan extends ControlableActor {


    private static final double start_x       = 100;
    private static final double start_y       = 100;
    private static final int    default_speed = 10;


    public PacMan( String pictureFileName, HashMap<String, Direction> keyMap ) throws FileNotFoundException {
        super( pictureFileName, start_x, start_y, keyMap );
        this.setSpeed( default_speed );
    }

    public PacMan( String pictureFileName, double x, double y, HashMap<String, Direction> keyMap ) throws FileNotFoundException {
        super( pictureFileName, x, y, keyMap );
    }

    public PacMan( List<String> pictureFileName, HashMap<String, Direction> keyMap ) throws FileNotFoundException {
        super( pictureFileName, start_x, start_y, keyMap );
        this.setSpeed( default_speed );
    }

    @Override
    protected double[] movePos() {
        double[] xyTuple  = new double[ 2 ];
        double   velocity = getSpeed();

        getMovement().getDirections().forEach( direction -> {
            if ( getMovement().isHoldDown( direction ) ) {
                if ( direction == Direction.Down ) {
                    xyTuple[ 0 ] = 0;
                    xyTuple[ 1 ] = velocity;
                }
                else if ( direction == Direction.Up ) {
                    xyTuple[ 0 ] = 0;
                    xyTuple[ 1 ] = -velocity;
                }
                else if ( direction == Direction.Left ) {
                    xyTuple[ 0 ] = -velocity;
                    xyTuple[ 1 ] = 0;
                }
                else if ( direction == Direction.Right ) {
                    xyTuple[ 0 ] = velocity;
                    xyTuple[ 1 ] = 0;
                }
            }
        } );
        return xyTuple;
    }

}
