package de.hsh.dennis.model.NpcLogic

import de.hsh.dennis.model.NpcLogic.NPCEnums.NpcType
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn

class JsonNpc(var spawnTime: Double, var speed: Double, var spawnType: Spawn, var npcType: NpcType) {
    override fun toString(): String {
        return """
            
            spawnTime:	$spawnTime
            speed:		$speed
            spawnType:	$spawnType
            npcType:	$npcType
            
            """.trimIndent()
    }

}
