package de.hsh.alexander.actor.collectables;

import common.actor.Actor;
import common.actor.Collectable;
import common.util.PlaySound;
import de.hsh.alexander.actor.ResourcePaths;

public class Invisible extends Collectable {

    public Invisible( double x, double y ) {
        super(x, y, ResourcePaths.Actor.Collectables.Invisible.invisiblePicture );
    }

    @Override
    public void wasCollected( Actor collector ) {
        PlaySound.playSound(ResourcePaths.Actor.Collectables.Invisible.invisibleSound );
        super.wasCollected( collector );
    }
}
