package de.hsh.dennis.model.Spawn.actors

import de.hsh.dennis.model.Spawn.Spawn
import de.hsh.dennis.model.Spawn.SkinConfig

class PackageHealing(spawnType: Spawn,
                     spawnTime: Double,
                     speed: Double) :
        Npc(SkinConfig.Package.skin_healing_path,
            spawnType,
            spawnTime,
            speed) {
    override fun collidesWithPlayer(player: Player): Boolean = player.doesCollide(this)
}
