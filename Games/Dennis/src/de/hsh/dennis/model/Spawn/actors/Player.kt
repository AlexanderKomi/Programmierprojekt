package de.hsh.dennis.model.Spawn.actors

import common.actor.Actor
import common.actor.Direction
import de.hsh.dennis.model.Spawn.SkinConfig

class Player : Actor(SkinConfig.Player.skin_standard_path,
                     SkinConfig.Player.posX,
                     SkinConfig.Player.posY) {
    // --- Getter & Setter ------------------------------------------------------------------------
    var direction = Direction.Non
    private var lastLR = Direction.Non

    fun changeSkin(dir: Direction) {
        fun setSkin(playerSkin: String, direction: Direction, lastLR: Direction = direction) {
            setCurrentImage(playerSkin)
            this.direction = direction
            if (lastLR != Direction.Non) {
                this.lastLR = lastLR
            }
        }
        return when (dir) {
            Direction.Left -> setSkin(SkinConfig.Player.skin_hit_left_path, Direction.Left)
            Direction.Right -> setSkin(SkinConfig.Player.skin_hit_right_path, Direction.Right)
            Direction.Up -> setSkin(
                    if (lastLR === Direction.Right) SkinConfig.Player.skin_up_right_path
                    else SkinConfig.Player.skin_up_left_path,
                    Direction.Up,
                    Direction.Non)
            Direction.Down -> setSkin(SkinConfig.Player.skin_down_left_path,
                                      Direction.Down,
                                      Direction.Non)
            Direction.Non -> setSkinToDefault()
        }
    }

    fun setSkinToDefault() {
        if (lastLR === Direction.Right) setCurrentImage(SkinConfig.Player.skin_standard_right_path)
        else setCurrentImage(SkinConfig.Player.skin_standard_left_path)
        direction = Direction.Non
    }

    init {
        setSkinToDefault()
    }
}
