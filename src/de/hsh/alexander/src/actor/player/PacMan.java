package de.hsh.alexander.src.actor.player;

import common.actor.Actor;
import common.actor.Collectable;
import common.actor.ControlableActor;
import common.actor.Direction;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PacMan extends ControlableActor {

    private final        SimpleIntegerProperty points               = new SimpleIntegerProperty( -1 );
    private static final double                start_x              = 100;
    private static final double                start_y              = 100;
    private static final int                   default_speed        = 10;
    private static final int                   change_picture_delay = 5;
    private static final double                default_sound_delay  = 3;

    private Direction facingDirection = Direction.Left;

    public PacMan( final String pictureFileName, final HashMap<String, Direction> keyMap ) {
        this( pictureFileName, start_x, start_y, keyMap );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( final String pictureFileName, final double x, final double y, final HashMap<String, Direction> keyMap ) {
        super( pictureFileName, x, y, keyMap );
        this.setSpeed( default_speed );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( final String[] pictureFileName, final HashMap<String, Direction> keyMap ) {
        this( start_x, start_y, keyMap, pictureFileName );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( List<String> pictureFileName, HashMap<String, Direction> keyMap ) {
        this( pictureFileName, start_x, start_y, keyMap );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( List<String> pictureFileName, double x, double y, HashMap<String, Direction> keyMap ) {
        super( pictureFileName, x, y, keyMap, change_picture_delay );
        this.setSpeed( default_speed );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( double x, double y, HashMap<String, Direction> keyMap, String mustHave, String... pictureFileName ) {
        super( x, y, keyMap, change_picture_delay, mustHave, pictureFileName );
        this.setSpeed( default_speed );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( double x, double y, HashMap<String, Direction> keyMap, String[] pictureFileName ) {
        super( pictureFileName, x, y, keyMap, change_picture_delay );
        this.setSpeed( default_speed );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( final HashMap<String, Direction> keyMap, final String[] pictureFileName ) {
        this( Arrays.asList( pictureFileName ), start_x, start_y, keyMap );
        this.setSoundDelay( default_sound_delay );
    }

    public PacMan( final HashMap<String, Direction> keyMap, final String mustHave, final String[] pictureFileName ) {
        this( start_x, start_y, keyMap, mustHave, pictureFileName );
        this.setSoundDelay( default_sound_delay );
    }


    @Override
    protected double[] calculateDirectedSpeed( final Direction direction, final double movement_speed ) {
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

        changeFacingDirection( xyTuple );

        return xyTuple;
    }

    private void changeFacingDirection( final double[] xyTuple ) {
        if ( xyTuple[ 0 ] > 0 ) {
            if ( this.facingDirection != Direction.Right ) {
                this.facingDirection = Direction.Right;
                if ( this.getScaleX() > 0 ) {
                    this.scaleImageWidth( -1 );
                }
            }
        }
        else if ( xyTuple[ 0 ] < 0 ) {
            if ( this.facingDirection != Direction.Left ) {
                this.facingDirection = Direction.Left;
                if ( this.getScaleX() < 0 ) {
                    this.scaleImageWidth( -1 );
                }
            }
        }
        if ( xyTuple[ 1 ] > 0 ) {
            if ( this.facingDirection != Direction.Down ) {
                this.facingDirection = Direction.Down;
            }
        }
        else if ( xyTuple[ 1 ] < 0 ) {
            if ( this.facingDirection != Direction.Up ) {
                this.facingDirection = Direction.Up;
            }
        }
    }

    protected synchronized void playSound() {
        super.playSound( "src/de/hsh/dennis/resources/audioFiles/sound1.mp3" );
    }

    @Override
    public synchronized boolean collisionModifier( Actor other ) {
        if ( other instanceof Collectable ) {
            final Collectable c = (Collectable) other;
            c.wasCollected( this );
            return false;
        }

        return super.collisionModifier( other );
    }

    public SimpleIntegerProperty getPointProperty() {
        return this.points;
    }

    public void addPoint() {
        points.set( points.get() + 1 );
    }
}
