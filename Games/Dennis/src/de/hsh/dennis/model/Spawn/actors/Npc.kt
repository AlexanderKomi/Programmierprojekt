package de.hsh.dennis.model.Spawn.actors

import common.actor.Actor
import de.hsh.dennis.model.Spawn.Spawn

abstract class Npc(picturePath: String,
                   val spawnType: Spawn,
                   val spawnTime: Double,
                   speed: Double) :
        Actor(picturePath),
        Comparable<Npc> {

    init {
        super.speed = speed
    }

    abstract fun collidesWithPlayer(player: Player): Boolean

    open fun move() {
        x = if (spawnType == Spawn.RIGHT) x - speed else x + speed
    }

    override fun toString(): String {
        return """
            
            ClassName:	${this.javaClass.name}
            spawnTime:	$spawnTime
            speed:		$speed
            spawnType:	$spawnType
            
            """.trimIndent()
    }

    override fun compareTo(other: Npc): Int = when {
        spawnTime < other.spawnTime -> -1
        spawnTime > other.spawnTime -> 1
        else                        -> 0
    }

}
