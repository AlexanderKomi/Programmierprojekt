package de.hsh.alexander.src.actor.level_elements;

import common.actor.LevelElement;
import de.hsh.alexander.src.actor.ResourcePaths;

public class Condensator extends LevelElement {

    private static final double default_scale = 1.0;

    public Condensator( double x, double y ) {
        super( x, y, ResourcePaths.Actor.LevelElements.Condensator.picture, default_scale );
    }
}
