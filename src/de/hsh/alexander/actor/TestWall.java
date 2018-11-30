package de.hsh.alexander.actor;

import common.actor.Actor;

import java.io.FileNotFoundException;

public class TestWall extends Actor {

    public TestWall( String pictureFileName ) throws FileNotFoundException {
        super( pictureFileName );
    }

    public TestWall( String pictureFileName, double x, double y ) throws FileNotFoundException {
        super( pictureFileName , x, y);
    }
}
