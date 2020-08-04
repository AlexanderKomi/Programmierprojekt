package de.hsh.dennis.model.NpcLogic.actors

import common.actor.Actor
import common.actor.Direction
import de.hsh.dennis.model.NpcLogic.SkinConfig

class Player : Actor(SkinConfig.Player.skin_standard_path,
                     SkinConfig.Player.posX,
                     SkinConfig.Player.posY) {
    // --- Getter & Setter ------------------------------------------------------------------------
    var direction = Direction.Non
    private var lastLR = Direction.Non
    fun changeSkin(dir: Direction) {
        when (dir) {
            Direction.Left  -> {
                setSkin(SkinConfig.Player.skin_hit_left_path)
                direction = Direction.Left
                lastLR = Direction.Left
            }
            Direction.Right -> {
                setSkin(SkinConfig.Player.skin_hit_right_path)
                direction = Direction.Right
                lastLR = Direction.Right
            }
            Direction.Up    -> {
                if (lastLR === Direction.Right) {
                    setCurrentImage(SkinConfig.Player.skin_up_right_path)
                } else {
                    setCurrentImage(SkinConfig.Player.skin_up_left_path)
                }
                direction = Direction.Up
            }
            Direction.Down  -> {
                setSkin(SkinConfig.Player.skin_down_left_path)
                direction = Direction.Down
            }
            Direction.Non   -> TODO()
        }
    }

    fun setSkinToDefault() {
        if (lastLR === Direction.Right) {
            setCurrentImage(SkinConfig.Player.skin_standard_right_path)
        } else {
            setCurrentImage(SkinConfig.Player.skin_standard_left_path)
        }
        direction = Direction.Non
    }

    private fun setSkin(imgPath: String) {
        setCurrentImage(imgPath)
    }

    init {
        setSkinToDefault()
    }
}
