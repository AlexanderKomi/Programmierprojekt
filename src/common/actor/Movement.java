package common.actor;

import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Set;

public class Movement {

    private HashMap<String, Direction>  keymap;
    private HashMap<Direction, Boolean> holdDown = initHoldDown();
    private double velocity;

    private HashMap<Direction, Boolean> initHoldDown() {
        HashMap<Direction, Boolean> hashMap = new HashMap<>();
        hashMap.put( Direction.Up, Boolean.FALSE );
        hashMap.put( Direction.Down, Boolean.FALSE );
        hashMap.put( Direction.Left, Boolean.FALSE );
        hashMap.put( Direction.Right, Boolean.FALSE );
        return hashMap;
    }

    boolean contains( String keyName ) {
        return keymap.keySet().contains( keyName );
    }

    boolean isHoldDown( String keyName ) {
        Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            return holdDown.get( d );
        }
        return false;
    }

    public boolean isHoldDown( Direction direction ) {
        return this.holdDown.get( direction );
    }

    void setKeyHoldDownIfPresent( String keyName, boolean b ) {
        Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            this.holdDown.put( d, b );
        }
    }

    static void move( ControlableActor a, KeyEvent keyEvent ) {
        String keyName   = keyEvent.getCode().getName();
        String eventName = keyEvent.getEventType().getName();
        if ( eventName.equals( "KEY_PRESSED" ) ) {
            if ( !a.getMovement().isHoldDown( keyName ) ) {
                if ( a.getMovement().contains( keyName ) ) {
                    a.getMovement().setKeyHoldDownIfPresent( keyName, true );
                    //movement.addDirection(movement.getKeymap().get( keyName ));
                }
            }
        }
        else if ( eventName.equals( "KEY_RELEASED" ) ) {
            a.getMovement().setKeyHoldDownIfPresent( keyName, false );
        }
    }

    // ------------- GETTER and Setter --------------

    public HashMap<String, Direction> getKeymap() {
        return keymap;
    }

    public Set<Direction> getDirections() {
        return this.holdDown.keySet();
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity( double i ) {
        this.velocity = i;
    }

    public void setKeyMap( HashMap<String, Direction> keymap ) {
        this.keymap = keymap;
    }

    public void setHoldDown( HashMap<Direction, Boolean> holdDown ) {
        this.holdDown = holdDown;
    }
}
