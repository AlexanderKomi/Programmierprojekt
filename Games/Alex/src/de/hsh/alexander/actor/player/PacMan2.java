package de.hsh.alexander.actor.player;

import common.actor.Direction;
import de.hsh.alexander.actor.ResourcePaths;

import java.util.HashMap;

public final class PacMan2 extends PacMan {

    private static HashMap<String, Direction> createKeyMap() {
        HashMap<String, Direction> pacMan2KeyMap = new HashMap<>();
        pacMan2KeyMap.put( "W", Direction.Up );
        pacMan2KeyMap.put( "S", Direction.Down );
        pacMan2KeyMap.put( "A", Direction.Left );
        pacMan2KeyMap.put( "D", Direction.Right );
        return pacMan2KeyMap;
    }

    public PacMan2() {
        super(ResourcePaths.Actor.Player.PacMan.pacman2Pictures, createKeyMap() );
    }

    public PacMan2( double x, double y ) {
        super( x, y, createKeyMap(), ResourcePaths.Actor.Player.PacMan.pacman2Pictures );
    }

}
