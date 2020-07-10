package de.hsh.alexander.actor.level_elements;

import common.actor.LevelElement;

import static de.hsh.alexander.actor.ResourcePaths.Actor.LevelElements.SMD.pictures;

public class SMD extends LevelElement {

    private static final int    default_delay = 30;
    private static final double scale         = 1.2;

    public SMD( double x, double y ) {
        super( x, y, pictures );
        this.setSwitchingDelay( default_delay );
        this.scaleImage( scale );
    }
}
