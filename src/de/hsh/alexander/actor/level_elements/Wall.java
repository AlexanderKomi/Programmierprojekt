package de.hsh.alexander.actor.level_elements;

import common.actor.LevelElement;
import de.hsh.alexander.actor.ResourcePaths;

import java.io.FileNotFoundException;
import java.util.List;

public class Wall extends LevelElement {

    private static final String fallbackPicture = ResourcePaths.Actor.LevelElements.Wall.directory + "p1_front.png";

    protected Wall() throws FileNotFoundException {
        super( fallbackPicture );
    }

    public Wall( String pictureFileName, double x, double y ) throws FileNotFoundException {
        super( pictureFileName, x, y );
    }

    protected Wall( List<String> pictureFilePaths, double x, double y, int delay ) throws FileNotFoundException {
        super( pictureFilePaths, x, y, delay );
    }

    public static Wall initTestWall() throws FileNotFoundException {
        return new Wall( fallbackPicture, 300, 400 );
    }

}
