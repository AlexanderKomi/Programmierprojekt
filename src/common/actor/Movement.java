package common.actor;

import java.util.HashMap;
import java.util.Set;

final class Movement {

    private       HashMap<String, Direction>  keymap;
    private final HashMap<Direction, Boolean> holdDown = initHoldDown();
    private       double                      velocity;

    private HashMap<Direction, Boolean> initHoldDown() {
        final HashMap<Direction, Boolean> hashMap = new HashMap<>();
        hashMap.put( Direction.Up, Boolean.FALSE );
        hashMap.put( Direction.Down, Boolean.FALSE );
        hashMap.put( Direction.Left, Boolean.FALSE );
        hashMap.put( Direction.Right, Boolean.FALSE );
        return hashMap;
    }

    private boolean contains( final String keyName ) {
        return keymap.keySet().contains( keyName );
    }

    private boolean isHoldDown( final String keyName ) {
        final Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            return holdDown.get( d );
        }
        return false;
    }

    boolean isHoldDown( Direction direction ) {
        return this.holdDown.get( direction );
    }

    private void setKeyHoldDownIfPresent( final String keyName, final boolean b ) {
        final Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            this.holdDown.put( d, b );
        }
    }

    void onKeyPressed( final String keyName ) {
        if ( !this.isHoldDown( keyName ) ) {
            if ( this.contains( keyName ) ) {
                this.setKeyHoldDownIfPresent( keyName, true );
            }
        }
    }

    void onKeyReleased( final String keyName ) {
        this.setKeyHoldDownIfPresent( keyName, false );
    }

    // ------------- GETTER and Setter --------------

    Set<Direction> getDirections() {
        return this.holdDown.keySet();
    }

    double getVelocity() {
        return velocity;
    }

    void setVelocity( double i ) {
        this.velocity = i;
    }

    void setKeyMap( HashMap<String, Direction> keymap ) {
        this.keymap = keymap;
    }
}
