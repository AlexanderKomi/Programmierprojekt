package de.hsh.alexander.src.actor.collectables;

import common.actor.Actor;
import common.actor.Collectable;
import common.util.PlaySound;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.Collectables.Invisible.invisiblePicture;
import static de.hsh.alexander.src.actor.ResourcePaths.Actor.Collectables.Invisible.invisibleSound;

public class Invisible extends Collectable {

    public Invisible( double x, double y ) {
        super( x, y, invisiblePicture );
    }

    @Override
    public void wasCollected( Actor collector ) {
        PlaySound.playSound( invisibleSound );
        super.wasCollected( collector );
    }
}
