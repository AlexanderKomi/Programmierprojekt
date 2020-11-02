package de.hsh.dennis.model.Spawn.actors

import common.actor.Direction
import common.config.WindowConfig
import de.hsh.dennis.model.Spawn.Spawn
import de.hsh.dennis.model.Spawn.SkinConfig

class Hacker(spawnType: Spawn, spawnTime: Double, speed: Double) :
        Npc(SkinConfig.Hacker.skin_standard_path,
            spawnType,
            spawnTime,
            speed) {

    init {
        x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble()
            else -SkinConfig.Hacker.skin_width.toDouble()
        y = SkinConfig.Player.posY + SkinConfig.Player.skin_height - SkinConfig.Hacker.skin_height
    }

    override fun collidesWithPlayer(player: Player): Boolean =
            player.direction === Direction.Up &&
            player.doesCollide(this)
}
