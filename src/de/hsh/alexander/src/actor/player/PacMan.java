package de.hsh.alexander.src.actor.player;

import common.actor.ControlableActor;
import common.actor.Direction;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.Player.PacMan.pacman1Pictures;
import static de.hsh.alexander.src.actor.ResourcePaths.Actor.Player.PacMan.pacman2Pictures;

public class PacMan extends ControlableActor {

    private static final double start_x              = 100;
    private static final double start_y              = 100;
    private static final int    default_speed        = 10;
    private static final int    change_picture_delay = 5;


    public PacMan( String pictureFileName, HashMap<String, Direction> keyMap ) throws FileNotFoundException {
        this( pictureFileName, start_x, start_y, keyMap );
    }

    public PacMan( String pictureFileName, double x, double y, HashMap<String, Direction> keyMap ) throws FileNotFoundException {
        super( pictureFileName, x, y, keyMap );
        this.setSpeed( default_speed );
    }

    public PacMan( List<String> pictureFileName, HashMap<String, Direction> keyMap ) throws FileNotFoundException {
        this( pictureFileName, start_x, start_y, keyMap );
    }

    public PacMan( List<String> pictureFileName, double x, double y, HashMap<String, Direction> keyMap )
            throws FileNotFoundException {
        super( pictureFileName, x, y, keyMap, change_picture_delay );
        this.setSpeed( default_speed );
    }

    public PacMan( double x, double y, HashMap<String, Direction> keyMap, String... pictureFileName )
            throws FileNotFoundException {
        super( x, y, keyMap, change_picture_delay, pictureFileName );
        this.setSpeed( default_speed );
    }

    public PacMan( HashMap<String, Direction> keyMap, String... pictureFileName ) throws FileNotFoundException {
        this( start_x, start_y, keyMap, pictureFileName );
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

    public static PacMan initPacMan1() throws FileNotFoundException {
        HashMap<String, Direction> pacMan1KeyMap = new HashMap<>();
        pacMan1KeyMap.put( "Up", Direction.Up );
        pacMan1KeyMap.put( "Down", Direction.Down );
        pacMan1KeyMap.put( "Left", Direction.Left );
        pacMan1KeyMap.put( "Right", Direction.Right );

        return new PacMan( pacMan1KeyMap, pacman1Pictures );

    }

    public static PacMan initPacMan2() throws FileNotFoundException {
        HashMap<String, Direction> pacMan2KeyMap = new HashMap<>();
        pacMan2KeyMap.put( "W", Direction.Up );
        pacMan2KeyMap.put( "S", Direction.Down );
        pacMan2KeyMap.put( "A", Direction.Left );
        pacMan2KeyMap.put( "D", Direction.Right );

        return new PacMan( 500, 500, pacMan2KeyMap, pacman2Pictures );
    }
}
