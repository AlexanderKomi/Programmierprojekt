package de.hsh.alexander.actor;

import common.actor.Actor;
import common.actor.Direction;
import common.util.Path;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActorCreator {

    private static final String   actorLocation = Path.getExecutionLocation() + "de/hsh/alexander/actor/";

    public static Actor initTestWall() throws FileNotFoundException {
        return new Actor(actorLocation + "p1_front.png", 300, 400);
    }

    public static PacMan initPacMan1() throws FileNotFoundException {
        HashMap<String, Direction> pacMan1KeyMap = new HashMap<>();
        pacMan1KeyMap.put( "Up", Direction.Up );
        pacMan1KeyMap.put( "Down", Direction.Down );
        pacMan1KeyMap.put( "Left", Direction.Left );
        pacMan1KeyMap.put( "Right", Direction.Right );

        ArrayList<String> images = new ArrayList<>();
        images.add( actorLocation + "sprite_pacman1_1.png" );
        images.add( actorLocation + "sprite_pacman1_2.png" );
        images.add( actorLocation + "sprite_pacman1_3.png" );
        images.add( actorLocation + "sprite_pacman1_4.png" );

        return new PacMan( images, pacMan1KeyMap );

    }

    public static PacMan initPacMan2() throws FileNotFoundException {
        HashMap<String, Direction> pacMan2KeyMap = new HashMap<>();
        pacMan2KeyMap.put( "W", Direction.Up );
        pacMan2KeyMap.put( "S", Direction.Down );
        pacMan2KeyMap.put( "A", Direction.Left );
        pacMan2KeyMap.put( "D", Direction.Right );

        return new PacMan( actorLocation + "snailWalk2.png", 500, 500, pacMan2KeyMap );
    }
}
