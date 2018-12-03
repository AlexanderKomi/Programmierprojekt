package de.hsh.alexander.src.actor;

import common.actor.Actor;
import common.util.Path;

import java.io.FileNotFoundException;

public class ActorCreator {

    static final String actorLocation = Path.getExecutionLocation() + "de/hsh/alexander/res/";

    public static Actor initTestWall() throws FileNotFoundException {
        return new Actor(actorLocation + "p1_front.png", 300, 400);
    }

}
