package de.hsh.alexander.src.actor.level_elements;

import common.actor.LevelElement;
import de.hsh.alexander.src.actor.ResourcePaths;

import java.util.List;

public class Wall extends LevelElement {

    private static final String fallbackPicture = ResourcePaths.Actor.LevelElements.Wall.directory + "p1_front.png";

    protected Wall() {
        super( fallbackPicture );
    }

    public Wall( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    protected Wall( List<String> pictureFilePaths, double x, double y, int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

}
