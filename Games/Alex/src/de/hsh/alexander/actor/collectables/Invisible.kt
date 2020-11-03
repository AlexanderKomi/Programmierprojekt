package de.hsh.alexander.actor.collectables

import common.actor.Actor
import common.actor.Collectable
import common.util.PlaySound
import de.hsh.alexander.actor.ResourcePaths
import de.hsh.alexander.actor.ResourcePaths.Actor.Collectables.Invisible.invisiblePicture
import de.hsh.alexander.actor.ResourcePaths.Actor.Collectables.Invisible.invisibleSound

class Invisible(x: Double, y: Double) : Collectable(x, y, invisiblePicture) {
    override fun wasCollectedBy(collector: Actor) {
        PlaySound.playSound(invisibleSound)
        super.wasCollectedBy(collector)
    }
}
