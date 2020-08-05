package de.hsh.alexander.actor.player

import common.actor.*
import de.hsh.alexander.actor.ResourcePaths
import javafx.beans.property.SimpleIntegerProperty

import java.util.HashMap

open class PacMan(x: Double, y: Double, keyMap: HashMap<String, Direction>, pictureFileName: Array<String>) :
        ControlableActor(pictureFileName, x, y, keyMap, 5.0) {

    val points: SimpleIntegerProperty  = SimpleIntegerProperty(-1)

    init { this.speed = 10.0 }

    @Override
    override fun calculateDirectedSpeed(direction: Direction, movementSpeed: Double): DoubleArray {
        val xyTuple = direction.toArray(movementSpeed)
        changeFacingDirection( xyTuple )
        return xyTuple
    }

    override fun collisionModifier( other: Actor?): Boolean {
        if ( other is Collectable ) {
            other.wasCollected( this )
            return false
        }
        return super.collisionModifier( other )
    }

    override fun onRemove( collectable: Collectable? ) {
        super.playSound(ResourcePaths.Actor.Player.PacMan.pacManSound )
        super.onRemove( collectable )
    }

    fun addPoint() {
        points.set( points.get() + 1 )
    }
}
