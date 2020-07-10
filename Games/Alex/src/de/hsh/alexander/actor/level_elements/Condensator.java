package de.hsh.alexander.actor.level_elements;

import common.actor.LevelElement;
import de.hsh.alexander.actor.ResourcePaths;

public class Condensator extends LevelElement {

    private static final double default_full_scale = 0.75;
    private static final double default_scale      = 1.0;

    public Condensator( double x, double y ) {
        super(x, y, ResourcePaths.Actor.LevelElements.Condensator.full_picture, default_full_scale );
    }

    public Condensator( double x, double y, int index ) {
        super(x, y, ResourcePaths.Actor.LevelElements.Condensator.pictures[ index ], default_scale );
    }

}
