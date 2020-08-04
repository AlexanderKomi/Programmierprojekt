package de.hsh.alexander.actor.player

import common.actor.Actor
import common.actor.Collectable
import common.actor.ControlableActor
import common.actor.Direction
import de.hsh.alexander.actor.ResourcePaths
import javafx.beans.property.SimpleIntegerProperty

import java.util.HashMap

open class PacMan(x: Double, y: Double, keyMap: HashMap<String, Direction>, pictureFileName: Array<String>) :
        ControlableActor(pictureFileName, x, y, keyMap, 5) {

    val points: SimpleIntegerProperty  = SimpleIntegerProperty(-1)
    private var facingDirection: Direction = Direction.Left

    init {
        this.speed = 10.0
    }


    @Override
    override fun calculateDirectedSpeed(direction: Direction, movement_speed: Double): DoubleArray {
        val xyTuple = DoubleArray(2)
        when (direction) {
            Direction.Down  -> {
                xyTuple[ 1 ] = movement_speed
            }
            Direction.Up    -> {
                xyTuple[ 1 ] = -movement_speed
            }
            Direction.Left  -> {
                xyTuple[ 0 ] = -movement_speed
            }
            Direction.Right -> {
                xyTuple[ 0 ] = movement_speed
            }
            Direction.Non   -> TODO()
        }

        changeFacingDirection( xyTuple )

        return xyTuple
    }

    private fun changeFacingDirection( xyTuple: DoubleArray ) {
        if ( xyTuple[ 0 ] > 0 ) {
            if ( this.facingDirection != Direction.Right ) {
                this.facingDirection = Direction.Right
                if ( this.scaleX > 0 ) {
                    this.scaleImageWidth( -1.0 )
                }
            }
        }
        else if ( xyTuple[ 0 ] < 0 ) {
            if ( this.facingDirection != Direction.Left ) {
                this.facingDirection = Direction.Left
                if ( this.scaleX < 0 ) {
                    this.scaleImageWidth( -1.0 )
                }
            }
        }
        if ( xyTuple[ 1 ] > 0 ) {
            if ( this.facingDirection != Direction.Down ) {
                this.facingDirection = Direction.Down
            }
        }
        else if ( xyTuple[ 1 ] < 0 ) {
            if ( this.facingDirection != Direction.Up ) {
                this.facingDirection = Direction.Up
            }
        }
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
