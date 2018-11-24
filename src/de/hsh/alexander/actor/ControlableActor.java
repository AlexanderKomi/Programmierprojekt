package de.hsh.alexander.actor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class ControlableActor extends Actor {

    private Movement movement = new Movement();

    ControlableActor( String pictureFileName, HashMap<String, Direction> keymap ) {
        super( pictureFileName );
        this.movement.setKeyMap( keymap );
    }

    ControlableActor( String pictureFileName, double x, double y, HashMap<String, Direction> keymap ) {
        super( pictureFileName, x, y );
        this.movement.setKeyMap( keymap );
    }

    ControlableActor( String pictureFileName,
                      double x,
                      double y,
                      double height,
                      double width,
                      HashMap<String, Direction> keymap ) {
        super( pictureFileName, x, y, height, width );
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

    public void movePos() {
        movement.getDirections().parallelStream().forEach( direction -> {
            if ( movement.isHoldDown( direction ) ) {
                double velocity = movement.getVelocity();
                if ( direction == Direction.Down ) {
                    super.movePos( velocity, 0 );
                }
                if ( direction == Direction.Up ) {
                    super.movePos( -velocity, 0 );
                }
                if ( direction == Direction.Left ) {
                    super.movePos( 0, -velocity );
                }
                if ( direction == Direction.Right ) {
                    super.movePos( 0, velocity );
                }
            }
        } );
    }

    @Override
    public void draw( GraphicsContext gc ) {
        super.draw( gc );
        this.movePos();
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
}
