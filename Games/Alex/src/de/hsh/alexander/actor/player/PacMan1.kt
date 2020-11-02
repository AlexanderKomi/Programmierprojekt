package de.hsh.alexander.actor.player

import common.actor.Direction
import de.hsh.alexander.actor.ResourcePaths
import javafx.scene.input.KeyCode
import java.util.*

class PacMan1(x: Double, y: Double)
    : PacMan(x, y, createKeyMap(), ResourcePaths.Actor.Player.PacMan.pacman1Pictures) {
    companion object {
        private fun createKeyMap(): HashMap<KeyCode, Direction> {
            val pacMan1KeyMap = HashMap<KeyCode, Direction>()
            pacMan1KeyMap[KeyCode.UP] = Direction.Up
            pacMan1KeyMap[KeyCode.DOWN] = Direction.Down
            pacMan1KeyMap[KeyCode.LEFT] = Direction.Left
            pacMan1KeyMap[KeyCode.RIGHT] = Direction.Right
            return pacMan1KeyMap
        }
    }
}
