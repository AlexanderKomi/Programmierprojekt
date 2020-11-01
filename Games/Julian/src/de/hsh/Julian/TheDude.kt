/**
 * @author Julian Sender
 */
package de.hsh.Julian

import common.actor.Actor
import common.actor.Collectable

/**
 * Main actor of Leertastenklatsche
 */
internal class TheDude(x: Double, y: Double) : Actor(firstImagePath, x, y) {

    init {
        speed = 0.0
    }

    var isTurnedleft = true

    /**
     * Swapping image depending on actors view-direction
     */
    fun swapImage() =
            if (currentImageName == firstImagePath) {
                setCurrentImage(secondImagePath)
            } else {
                setCurrentImage(firstImagePath)
            }

    /**
     *
     * @param other checks if collectable was collected
     * @return returns false or instance of collected item
     */
    public override fun collisionModifier(other: Actor): Boolean =
            if (other is Collectable) {
                other.wasCollectedBy(this)
                false
            } else {
                true
            }

    companion object {
        /**
         * GETTERS AND SETTERS
         */
        const val firstImagePath = Leertastenklatsche.location + "thedude.png"
        const val secondImagePath = Leertastenklatsche.location + "thedude_turned.png"

    }
}
