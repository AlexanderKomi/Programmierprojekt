package de.hsh.alexander.actor.player

import common.actor.*
import common.util.PlaySound
import de.hsh.alexander.actor.ResourcePaths
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.input.KeyCode
import java.util.*

open class PacMan(x: Double, y: Double, keyMap: HashMap<KeyCode, Direction>, pictureFileName: Array<String>) :
        ControllableActor(pictureFileName, x, y, keyMap, 5) {

    val points: SimpleIntegerProperty = SimpleIntegerProperty(-1)

    init {
        super.speed = 10.0
    }

    @Override
    override fun calculateDirectedSpeed(direction: Direction, movementSpeed: Double): DoubleArray =
        direction.toArray(movementSpeed).also {
            changeFacingDirection(it)
        }

    override fun collisionModifier(other: Actor): Boolean =
            if (other is Collectable) {
                other.wasCollectedBy(this)
                false
            } else super.collisionModifier(other)

    override fun onRemove(collectable: Collectable) = PlaySound.playSound(ResourcePaths.Actor.Player.PacMan.pacManSound)

    fun addPoint() = points.set(points.get() + 1)
}
