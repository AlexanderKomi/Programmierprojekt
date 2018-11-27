package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class ControlableActor extends Actor {

    private Movement movement = new Movement();

    protected ControlableActor( String pictureFileName, HashMap<String, Direction> keymap ) throws FileNotFoundException {
        super( pictureFileName );
        this.movement.setKeyMap( keymap );
    }

    protected ControlableActor( String pictureFileName, double x, double y, HashMap<String, Direction> keymap )
            throws FileNotFoundException {
        super( pictureFileName, x, y );
        this.movement.setKeyMap( keymap );
    }

    protected ControlableActor( List<String> pictureFileName, double x, double y, HashMap<String, Direction> keymap, int delay )
            throws FileNotFoundException {
        super( pictureFileName, x, y, delay );
        this.movement.setKeyMap( keymap );
    }


    public void move( KeyEvent keyEvent ) {
        String keyName   = keyEvent.getCode().getName();
        String eventName = keyEvent.getEventType().getName();
        if ( eventName.equals( "KEY_PRESSED" ) ) {
            if ( !movement.isHoldDown( keyName ) ) {
                if ( movement.contains( keyName ) ) {
                    movement.setKeyHoldDownIfPresent( keyName, true );
                    //movement.addDirection(movement.getKeymap().get( keyName ));
                }
            }
        }
        else if ( eventName.equals( "KEY_RELEASED" ) ) {
            movement.setKeyHoldDownIfPresent( keyName, false );
        }
    }

    public void draw( Canvas canvas ) {
        double[] temp = this.movePos();
        super.draw( canvas, temp[ 0 ], temp[ 1 ] );
    }

    protected double[] movePos() {
        double[] xyTuple  = new double[ 2 ];
        double   velocity = getMovement().getVelocity();

        getMovement().getDirections().forEach( direction -> {
            if ( getMovement().isHoldDown( direction ) ) {
                if ( direction == Direction.Down ) {
                    xyTuple[ 0 ] += 0;
                    xyTuple[ 1 ] += velocity;
                    //super.movePos( velocity, 0 );
                }
                if ( direction == Direction.Up ) {
                    xyTuple[ 0 ] += 0;
                    xyTuple[ 1 ] += -velocity;
                    //super.movePos( -velocity, 0 );
                }
                if ( direction == Direction.Left ) {
                    xyTuple[ 0 ] += -velocity;
                    xyTuple[ 1 ] += 0;
                    //super.movePos( 0, -velocity );
                }
                if ( direction == Direction.Right ) {
                    xyTuple[ 0 ] += velocity;
                    xyTuple[ 1 ] += 0;
                    //super.movePos( 0, velocity );
                }
            }
        } );
        return xyTuple;
    }

    public void setKeyMap( HashMap<String, Direction> keyMap ) {
        this.movement.setKeyMap( keyMap );
    }

    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    public Movement getMovement() {
        return movement;
    }
}
