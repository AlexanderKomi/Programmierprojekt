package de.hsh.dennis.model.Spawn.actors

import common.actor.Direction
import common.config.WindowConfig
import de.hsh.dennis.model.Spawn.Spawn
import de.hsh.dennis.model.Spawn.SkinConfig

class Package(spawnType: Spawn, spawnTime: Double, speed: Double) :
        Npc(SkinConfig.Package.skin_standard_path,
            spawnType,
            spawnTime,
            speed) {

    init {
        x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble()
            else -SkinConfig.Package.skin_width.toDouble()
        y = SkinConfig.Player.posY
    }

    override fun collidesWithPlayer(player: Player): Boolean =
            player.direction === Direction.Down &&
            player.doesCollide(this)
}
