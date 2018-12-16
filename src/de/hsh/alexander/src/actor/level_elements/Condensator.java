package de.hsh.alexander.src.actor.level_elements;

import common.actor.LevelElement;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.LevelElements.Condensator.full_picture;
import static de.hsh.alexander.src.actor.ResourcePaths.Actor.LevelElements.Condensator.pictures;

public class Condensator extends LevelElement {

    private static final double default_full_scale = 0.75;
    private static final double default_scale      = 1.0;

    public Condensator( double x, double y ) {
        super( x, y, full_picture, default_full_scale );
    }

    public Condensator( double x, double y, int index ) {
        super( x, y, pictures[ index ], default_scale );
    }

}
