package de.hsh.alexander.actor.collectables

import common.actor.Actor
import common.actor.Collectable
import common.util.PlaySound
import de.hsh.alexander.actor.ResourcePaths

class Invisible(x: Double, y: Double) : Collectable(x,
                                                    y,
                                                    ResourcePaths.Actor.Collectables.Invisible.invisiblePicture) {
    override fun wasCollectedBy(collector: Actor) {
        PlaySound.playSound(ResourcePaths.Actor.Collectables.Invisible.invisibleSound)
        super.wasCollectedBy(collector)
    }
}
