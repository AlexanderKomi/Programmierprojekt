package de.hsh.dennis.model.NpcLogic.actors

import de.hsh.dennis.model.NpcLogic.NPCEnums
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.SkinConfig

class PackageHealing(spawnType: Spawn?,
                     spawnTime: Double,
                     speed: Double) : Npc(SkinConfig.Package.skin_healing_path,
                                          spawnType!!,
                                          NPCEnums.NpcType.PACKAGE,
                                          spawnTime,
                                          speed) 
