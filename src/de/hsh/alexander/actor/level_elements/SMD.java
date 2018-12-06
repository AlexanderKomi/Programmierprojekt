package de.hsh.alexander.actor.level_elements;

import common.actor.LevelElement;

import java.io.FileNotFoundException;
import java.util.List;

import static de.hsh.alexander.actor.ResourcePaths.Actor.LevelElements.SMD.pictures;

public class SMD extends LevelElement {

    private static final int    default_delay = 50;
    private static final double scale         = 2;


    public SMD() throws FileNotFoundException {
        super( pictures );
        this.setSwitchingDelay( default_delay );
        this.scaleImage( scale );
    }

    public SMD( double x, double y ) throws FileNotFoundException {
        super( x, y, pictures );
        this.setSwitchingDelay( default_delay );
        this.scaleImage( scale );
    }

    protected SMD( List<String> pictureFilePaths, double x, double y, int delay ) throws FileNotFoundException {
        super( pictureFilePaths, x, y, delay );
        this.scaleImage( scale );
    }
}
