/**
 * @author Julian Sender
 */
package common.actor;

import java.util.HashMap;
import java.util.Set;

/**
 * Checks inputs for movements
 */
final class Movement {

    private       HashMap<String, Direction>  keymap;
    private final HashMap<Direction, Boolean> holdDown = initHoldDown();
    private       double                      velocity;

    /**
     * returns hashmap of movements
     * @return hashmap of movement-orders
     */
    private HashMap<Direction, Boolean> initHoldDown() {
        final HashMap<Direction, Boolean> hashMap = new HashMap<>();
        hashMap.put( Direction.Up, Boolean.FALSE );
        hashMap.put( Direction.Down, Boolean.FALSE );
        hashMap.put( Direction.Left, Boolean.FALSE );
        hashMap.put( Direction.Right, Boolean.FALSE );
        return hashMap;
    }

    /**
     * sets Keys
     * @param keyName UP,DOWN,LEFT,RIGHT
     * @return name of the pressed key
     */
    private boolean contains( final String keyName ) {
        return keymap.keySet().contains( keyName );
    }

    /**
     * Checks if key is held down
     * @param keyName e.g. UP,DOWN,LEFT,RIGHT
     * @return a boolean if key was hold or not
     */
    private boolean isHoldDown( final String keyName ) {
        final Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            return holdDown.get( d );
        }
        return false;
    }

    /**
     *
     * @param direction instance of Direction
     * @return returns a direction
     */
    boolean isHoldDown( Direction direction ) {
        return this.holdDown.get( direction );
    }

    /**
     *
     * @param keyName Name of key pressed
     * @param b
     */
    private void setKeyHoldDownIfPresent( final String keyName, final boolean b ) {
        final Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            this.holdDown.put( d, b );
        }
    }

    /**
     * Action to take when key is pressed
     * @param keyName Name of Key
     */
    void onKeyPressed( final String keyName ) {
        if ( !this.isHoldDown( keyName ) ) {
            if ( this.contains( keyName ) ) {
                this.setKeyHoldDownIfPresent( keyName, true );
            }
        }
    }

    /**
     * Action to take when key is released
     * @param keyName Keyname
     */
    void onKeyReleased( final String keyName ) {
        this.setKeyHoldDownIfPresent( keyName, false );
    }

    /**
     * GETTERS AND SETTERS
     *
     */

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
