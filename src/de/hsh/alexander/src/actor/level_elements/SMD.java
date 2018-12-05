package de.hsh.alexander.src.actor.level_elements;

import common.actor.LevelElement;

import java.io.FileNotFoundException;
import java.util.List;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.LevelElements.SMD.pictures;

public class SMD extends LevelElement {

    protected SMD() throws FileNotFoundException {
        super( pictures );
    }

    public SMD( double x, double y ) throws FileNotFoundException {
        super( x, y, pictures );
    }

    protected SMD( List<String> pictureFilePaths, double x, double y, int delay ) throws FileNotFoundException {
        super( pictureFilePaths, x, y, delay );
    }
}
