package de.hsh.alexander.src.actor.level_elements;

import common.actor.LevelElement;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.LevelElements.Fan.pictures;

public class Fan extends LevelElement {

    private static final double default_delay = 3;
    private static final double scale         = 3;

    public Fan( double x, double y ) {
        super( x, y, pictures );
        this.setSwitchingDelay( default_delay );
        this.scaleImage( scale );
    }
}
