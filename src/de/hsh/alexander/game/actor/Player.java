package de.hsh.alexander.game.actor;

import de.hsh.alexander.util.Logger;
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
        if ( keyEvent.getEventType().getName().equals( "KEY_PRESSED" ) ||
             keyEvent.getEventType().getName().equals( "KEY_RELEASED" ) ) {
            String keyName = keyEvent.getCode().getName();
            movement.getKeymap().forEach( ( key, value ) -> {
                if ( key.equals( keyName ) ) {
                    Logger.log( keyName );
                    this.movePos( this.movement.getKeymap().get( key ) );
                }
            } );
        }
    }

    public void movePos( Direction direction ) {
        if ( direction == Direction.Down ) {
            this.movePos( movement.getSpeed(), 0 );
        }
        else if ( direction == Direction.Up ) {
            this.movePos( -movement.getSpeed(), 0 );
        }
        else if ( direction == Direction.Left ) {
            this.movePos( 0, -movement.getSpeed() );
        }
        else if ( direction == Direction.Right ) {
            this.movePos( 0, movement.getSpeed() );
        }
    }

    public void setKeyMap( HashMap<String, Direction> keyMap ) {
        this.movement.setKeyMap( keyMap );
    }

    public void setSpeed( int speed ) {
        this.movement.setSpeed( speed );
    }
}
