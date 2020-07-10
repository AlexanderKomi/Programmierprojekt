package de.hsh.alexander.actor.player;

import common.actor.Direction;
import de.hsh.alexander.actor.ResourcePaths;

import java.util.HashMap;

public final class PacMan1 extends PacMan {

    private static HashMap<String, Direction> createKeyMap() {
        HashMap<String, Direction> pacMan1KeyMap = new HashMap<>();
        pacMan1KeyMap.put( "Up", Direction.Up );
        pacMan1KeyMap.put( "Down", Direction.Down );
        pacMan1KeyMap.put( "Left", Direction.Left );
        pacMan1KeyMap.put( "Right", Direction.Right );
        return pacMan1KeyMap;
    }

    public PacMan1( double x, double y ) {
        super(x, y, createKeyMap(), ResourcePaths.Actor.Player.PacMan.pacman1Pictures );
    }

}
