package de.hsh.alexander.actor.player

import common.actor.Direction
import de.hsh.alexander.actor.ResourcePaths
import javafx.scene.input.KeyCode
import java.util.*

class PacMan2(x: Double, y: Double)
    : PacMan(x, y, createKeyMap(), ResourcePaths.Actor.Player.PacMan.pacman2Pictures) {

    companion object {
        private fun createKeyMap(): HashMap<KeyCode, Direction> {
            val pacMan2KeyMap = HashMap<KeyCode, Direction>()
            pacMan2KeyMap[KeyCode.W] = Direction.Up
            pacMan2KeyMap[KeyCode.S] = Direction.Down
            pacMan2KeyMap[KeyCode.A] = Direction.Left
            pacMan2KeyMap[KeyCode.D] = Direction.Right
            return pacMan2KeyMap
        }
    }
}
