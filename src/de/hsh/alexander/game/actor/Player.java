package de.hsh.alexander.game.actor;

import java.util.HashMap;

public class Player extends Actor {

    HashMap<String, Direction> keymap;
    private double speed;

    Player( String pictureFileName, HashMap<String, Direction> keymap ) {
        super( pictureFileName );
        this.keymap = keymap;
    }

    Player( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    Player( String pictureFileName, double x, double y, HashMap<String, Direction> keymap ) {
        super( pictureFileName, x, y );
        this.keymap = keymap;
    }

    Player( String pictureFileName, double x, double y, double height, double width ) {
        super( pictureFileName, x, y, height, width );
    }


    public void move( String keyName ) {
        keymap.forEach( ( key, value ) -> {
            if ( key.equals( keyName ) ) {
                this.movePos( this.keymap.get( key ) );
            }
        } );
    }

    public void movePos( Direction direction ) {
        if ( direction == Direction.Down ) {
            this.movePos( speed, 0 );
        }
        else if ( direction == Direction.Up ) {
            this.movePos( -speed, 0 );
        }
        else if ( direction == Direction.Left ) {
            this.movePos( 0, -speed );
        }
        else if ( direction == Direction.Right ) {
            this.movePos( 0, speed );
        }
    }

    public void setSpeed( int i ) {
        this.speed = i;
    }

    public void setKeyMap( HashMap<String, Direction> keymap ) {
        this.keymap = keymap;
    }
}
