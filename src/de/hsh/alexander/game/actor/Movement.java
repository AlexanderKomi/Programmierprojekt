package de.hsh.alexander.game.actor;

import java.util.HashMap;

public class Movement {

    private HashMap<String, Direction>  keymap;
    private HashMap<Direction, Boolean> holdDown = initHoldDown();
    private double                      speed;

    private HashMap<Direction, Boolean> initHoldDown() {
        HashMap<Direction, Boolean> hashMap = new HashMap<>();
        hashMap.put( Direction.Down, Boolean.FALSE );
        hashMap.put( Direction.Up, Boolean.FALSE );
        hashMap.put( Direction.Left, Boolean.FALSE );
        hashMap.put( Direction.Right, Boolean.FALSE );
        return hashMap;
    }

    boolean isHoldDown( String keyName ) {
        Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            return holdDown.get( d );
        }
        return false;
    }

    boolean setKeyHoldDown( String keyName ) {
        Direction d = this.keymap.get( keyName );
        if ( d != null ) {
            this.holdDown.put( d, Boolean.TRUE );
            return true;
        }
        return false;
    }

    public HashMap<String, Direction> getKeymap() {
        return keymap;
    }

    // ------------- GETTER and Setter --------------

    public double getSpeed() {
        return speed;
    }

    public void setSpeed( int i ) {
        this.speed = i;
    }

    public void setKeyMap( HashMap<String, Direction> keymap ) {
        this.keymap = keymap;
    }


    public void setHoldDown( HashMap<Direction, Boolean> holdDown ) {
        this.holdDown = holdDown;
    }
}
