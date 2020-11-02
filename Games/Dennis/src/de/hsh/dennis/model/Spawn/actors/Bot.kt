package de.hsh.dennis.model.Spawn.actors

import common.actor.Direction
import common.config.WindowConfig
import de.hsh.dennis.model.Spawn.SkinConfig
import de.hsh.dennis.model.Spawn.Spawn

class Bot(spawnType: Spawn, spawnTime: Double, speed: Double) :
        Npc(SkinConfig.Bot.skin_standard_path,
            spawnType,
            spawnTime,
            speed) {

    var bounceMax = 5.0
    private var bouncePos: Double = bounceMax * -1.0
    private val bounceSpeed: Double = speed / 2.0

    init {
        x = if (spawnType == Spawn.RIGHT) WindowConfig.window_width.toDouble()
        else -SkinConfig.Bot.skin_width.toDouble()
        y = SkinConfig.Player.posY + SkinConfig.Player.skin_height * 0.2 - bounceMax
    }

    override fun collidesWithPlayer(player: Player): Boolean =
            (this.spawnType == Spawn.RIGHT && player.direction === Direction.Right ||
             this.spawnType == Spawn.LEFT && player.direction === Direction.Left) &&
            player.doesCollide(this)

    override fun move() {
        super.move()
        bounce()
    }

    private fun bounce() {
        if (bounceMax > 0.0) {
            if (bouncePos < bounceMax) {
                if (bouncePos + bounceSpeed > bounceMax) bouncePos = bounceMax
                else bouncePos += bounceSpeed
            } else {
                bounceMax *= -1.0
            }
        } else {
            if (bouncePos > bounceMax) {
                if (bouncePos - bounceSpeed < bounceMax) bouncePos = bounceMax
                else bouncePos -= bounceSpeed
            } else {
                bounceMax *= -1.0
            }
        }
        y += bouncePos
    }

}
