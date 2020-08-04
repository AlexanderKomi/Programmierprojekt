package de.hsh.dennis.model.NpcLogic.actors

import de.hsh.dennis.model.NpcLogic.NPCEnums
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.SkinConfig

class Bot(spawnType: Spawn?,
          spawnTime: Double,
          speed: Double) : Npc(SkinConfig.Bot.skin_standard_path,
                               spawnType!!,
                               NPCEnums.NpcType.BOT,
                               spawnTime,
                               speed) {
    var bounceMax = 5.0
    private var bouncePos = bounceMax * -1.0
    private val bounceSpeed = speed / 2.0
    private var bounceInitialized = false
    override fun move() {
        x = if (spawnType == Spawn.RIGHT) x - speed else x + speed
        bounce()
    }

    private fun bounceInit() {
        if (!bounceInitialized) {
            y = y - bounceMax
            bounceInitialized = !bounceInitialized
        }
    }

    private fun bounce() {
        if (bounceMax > 0.0) {
            if (bouncePos < bounceMax) {
                if (bouncePos + bounceSpeed > bounceMax) {
                    bouncePos = bounceMax
                } else {
                    bouncePos += bounceSpeed
                }
            } else if (bouncePos >= bounceMax) {
                bounceMax = bounceMax * -1.0
            }
        } else {
            if (bouncePos > bounceMax) {
                if (bouncePos - bounceSpeed < bounceMax) {
                    bouncePos = bounceMax
                } else {
                    bouncePos -= bounceSpeed
                }
            } else if (bouncePos <= bounceMax) {
                bounceMax = bounceMax * -1.0
            }
        }
        y = y + bouncePos
    }

    init {
        bounceInit()
    }
}
