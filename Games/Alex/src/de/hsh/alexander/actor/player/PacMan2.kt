package de.hsh.alexander.actor.player

import common.actor.Direction
import de.hsh.alexander.actor.ResourcePaths
import java.util.*

class PacMan2(x: Double, y: Double)
    : PacMan(x, y, createKeyMap(), ResourcePaths.Actor.Player.PacMan.pacman2Pictures) {

    companion object {
        private fun createKeyMap(): HashMap<String, Direction> {
            val pacMan2KeyMap = HashMap<String, Direction>()
            pacMan2KeyMap["W"] = Direction.Up
            pacMan2KeyMap["S"] = Direction.Down
            pacMan2KeyMap["A"] = Direction.Left
            pacMan2KeyMap["D"] = Direction.Right
            return pacMan2KeyMap
        }
    }
}
