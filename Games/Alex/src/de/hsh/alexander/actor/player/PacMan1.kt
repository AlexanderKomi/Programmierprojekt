package de.hsh.alexander.actor.player

import common.actor.Direction
import de.hsh.alexander.actor.ResourcePaths
import java.util.*

class PacMan1(x: Double, y: Double)
    : PacMan(x, y, createKeyMap(), ResourcePaths.Actor.Player.PacMan.pacman1Pictures) {
    companion object {
        private fun createKeyMap(): HashMap<String, Direction> {
            val pacMan1KeyMap = HashMap<String, Direction>()
            pacMan1KeyMap["Up"] = Direction.Up
            pacMan1KeyMap["Down"] = Direction.Down
            pacMan1KeyMap["Left"] = Direction.Left
            pacMan1KeyMap["Right"] = Direction.Right
            return pacMan1KeyMap
        }
    }
}
