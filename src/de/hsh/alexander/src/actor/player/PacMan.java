package de.hsh.alexander.src.actor.player;

import common.actor.ControlableActor;
import common.actor.Direction;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PacMan extends ControlableActor {

    private              SimpleIntegerProperty points               = new SimpleIntegerProperty();
    private static final double                start_x              = 100;
    private static final double                start_y              = 100;
    private static final int                   default_speed        = 10;
    private static final int                   change_picture_delay = 5;


    public PacMan( String pictureFileName, HashMap<String, Direction> keyMap ) {
        this( pictureFileName, start_x, start_y, keyMap );
    }

    public PacMan( String pictureFileName, double x, double y, HashMap<String, Direction> keyMap ) {
        super( pictureFileName, x, y, keyMap );
        this.setSpeed( default_speed );
    }

    public PacMan( String[] pictureFileName, HashMap<String, Direction> keyMap ) {
        this( start_x, start_y, keyMap, pictureFileName );
    }

    public PacMan( List<String> pictureFileName, HashMap<String, Direction> keyMap ) {
        this( pictureFileName, start_x, start_y, keyMap );
    }

    public PacMan( List<String> pictureFileName, double x, double y, HashMap<String, Direction> keyMap ) {
        super( pictureFileName, x, y, keyMap, change_picture_delay );
        this.setSpeed( default_speed );
    }

    public PacMan( double x, double y, HashMap<String, Direction> keyMap, String mustHave, String... pictureFileName ) {
        super( x, y, keyMap, change_picture_delay, mustHave, pictureFileName );
        this.setSpeed( default_speed );
    }

    public PacMan( double x, double y, HashMap<String, Direction> keyMap, String[] pictureFileName ) {
        super( pictureFileName, x, y, keyMap, change_picture_delay );
        this.setSpeed( default_speed );
    }

    public PacMan( HashMap<String, Direction> keyMap, String[] pictureFileName ) {
        this( Arrays.asList( pictureFileName ), start_x, start_y, keyMap );
    }

    public PacMan( HashMap<String, Direction> keyMap, String mustHave, String[] pictureFileName ) {
        this( start_x, start_y, keyMap, mustHave, pictureFileName );
    }


    @Override
    protected double[] calculateDirectedSpeed( Direction direction, double movement_speed ) {
        double[] xyTuple = new double[ 2 ];
        if ( direction == Direction.Down ) {
            xyTuple[ 0 ] = 0;
            xyTuple[ 1 ] = movement_speed;
        }
        else if ( direction == Direction.Up ) {
            xyTuple[ 0 ] = 0;
            xyTuple[ 1 ] = -movement_speed;
        }
        else if ( direction == Direction.Left ) {
            xyTuple[ 0 ] = -movement_speed;
            xyTuple[ 1 ] = 0;
        }
        else if ( direction == Direction.Right ) {
            xyTuple[ 0 ] = movement_speed;
            xyTuple[ 1 ] = 0;
        }
        return xyTuple;
    }

    public SimpleIntegerProperty getPointProperty() {
        return this.points;
    }

    public int getPoints() {
        return points.get();
    }

    public void addPoint() {
        points.set( points.get() + 1 );
    }

    public void resetPoints() {
        this.points.set( 0 );
    }
}
