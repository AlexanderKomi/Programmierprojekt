package de.hsh.dennis.model.NpcLogic.actors

import de.hsh.dennis.model.NpcLogic.NPCEnums
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.SkinConfig

class Hacker(spawnType: Spawn?,
             spawnTime: Double,
             speed: Double) : Npc(SkinConfig.Hacker.skin_standard_path,
                                  spawnType!!,
                                  NPCEnums.NpcType.HACKER,
                                  spawnTime,
                                  speed) 
