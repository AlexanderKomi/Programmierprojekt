package de.hsh.alexander.actor;

import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class Player extends Actor {

    private Movement movement = new Movement();

    Player( String pictureFileName, HashMap<String, Direction> keymap ) {
        super( pictureFileName );
        this.movement.setKeyMap( keymap );
    }

    Player( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    Player( String pictureFileName, double x, double y, HashMap<String, Direction> keymap ) {
        super( pictureFileName, x, y );
        this.movement.setKeyMap( keymap );
    }

    Player( String pictureFileName, double x, double y, double height, double width ) {
        super( pictureFileName, x, y, height, width );
    }


    public void move( KeyEvent keyEvent ) {
        String keyName = keyEvent.getCode().getName();
        if ( keyEvent.getEventType().getName().equals( "KEY_PRESSED" ) ) {
            if ( !movement.isHoldDown( keyName ) ) {
                if ( movement.contains( keyName ) ) {
                    movement.setKeyHoldDown( keyName, true );
                    movement.direction = movement.getKeymap().get( keyName );
                }
            }
        }
        else if ( keyEvent.getEventType().getName().equals( "KEY_RELEASED" ) ) {
            movement.setKeyHoldDown( keyName, false );
        }
    }

    public void movePos() {
        Direction direction = movement.direction;
        if ( direction == Direction.Down ) {
            super.movePos( movement.getVelocity(), 0 );
        }
        else if ( direction == Direction.Up ) {
            super.movePos( -movement.getVelocity(), 0 );
        }
        else if ( direction == Direction.Left ) {
            super.movePos( 0, -movement.getVelocity() );
        }
        else if ( direction == Direction.Right ) {
            super.movePos( 0, movement.getVelocity() );
        }
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    public void setKeyMap( HashMap<String, Direction> keyMap ) {
        this.movement.setKeyMap( keyMap );
    }

    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }
}
