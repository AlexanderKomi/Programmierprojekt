package de.hsh.alexander.src.actor.collectables;

import common.actor.Collectable;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.Collectables.invisiblePicture;

public class Invisible extends Collectable {

    public Invisible( double x, double y ) {
        super( x, y, invisiblePicture );
    }
}
