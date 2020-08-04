package de.hsh.dennis.model.NpcLogic.actors

import common.actor.Actor
import common.config.WindowConfig
import de.hsh.dennis.model.NpcLogic.NPCEnums.NpcType
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.SkinConfig

abstract class Npc : Actor, Comparable<Npc> {
    val spawnTime: Double
    val spawnType: Spawn
    val npcType: NpcType

    internal constructor(picturePath: String?, spawnType: Spawn, type: NpcType) : super(picturePath!!) {
        this.spawnType = spawnType
        npcType = type
        spawnTime = 0.0
        this.speed = SkinConfig.Level.speed
        when (type) {
            NpcType.PACKAGE -> {
                x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble() else - SkinConfig.Package.skin_width.toDouble()
                y = SkinConfig.Player.posY
            }
            NpcType.BOT     -> {
                x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble() else - SkinConfig.Bot.skin_width.toDouble()
                y = SkinConfig.Player.posY + SkinConfig.Player.skin_height * 0.2
            }
            NpcType.HACKER  -> {
                x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble() else - SkinConfig.Hacker.skin_width.toDouble()
                y = SkinConfig.Player.posY + SkinConfig.Player.skin_height -
                    SkinConfig.Hacker.skin_height
            }
        }
    }

    internal constructor(picturePath: String?,
                         spawnType: Spawn,
                         type: NpcType,
                         spawnTime: Double,
                         speed: Double) : super(picturePath!!) {
        this.spawnTime = spawnTime
        npcType = type
        this.spawnType = spawnType
        this.speed = speed
        when (type) {
            NpcType.PACKAGE -> {
                x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble() else -SkinConfig.Package.skin_width.toDouble()
                y = SkinConfig.Player.posY
            }
            NpcType.BOT     -> {
                x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble() else -SkinConfig.Bot.skin_width.toDouble()
                y = SkinConfig.Player.posY + SkinConfig.Player.skin_height * 0.2
            }
            NpcType.HACKER  -> {
                x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble() else -SkinConfig.Hacker.skin_width.toDouble()
                y = SkinConfig.Player.posY + SkinConfig.Player.skin_height -
                    SkinConfig.Hacker.skin_height
            }
        }
    }

    open fun move() {
        x = if (spawnType == Spawn.RIGHT) x - speed else x + speed
    }

    override fun toString(): String {
        return """
            
            ClassName:	${this.javaClass.name}
            spawnTime:	$spawnTime
            speed:		$speed
            spawnType:	$spawnType
            npcType:	$npcType
            
            """.trimIndent()
    }

    override fun compareTo(other: Npc): Int = when {
        spawnTime < other.spawnTime -> -1
        spawnTime > other.spawnTime -> 1
        else                        -> 0
    }

}
