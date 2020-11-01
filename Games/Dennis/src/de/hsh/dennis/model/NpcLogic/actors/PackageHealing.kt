package de.hsh.dennis.model.NpcLogic.actors

import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.SkinConfig

class PackageHealing(spawnType: Spawn,
                     spawnTime: Double,
                     speed: Double) :
        Npc(SkinConfig.Package.skin_healing_path,
            spawnType,
            spawnTime,
            speed) {
    override fun collidesWithPlayer(player: Player): Boolean = player.doesCollide(this)
}
