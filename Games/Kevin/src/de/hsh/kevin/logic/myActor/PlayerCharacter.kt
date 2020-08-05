package de.hsh.kevin.logic.myActor

import common.actor.ControlableActor
import common.actor.Direction
import common.actor.ImageLoader.loadImage
import javafx.scene.image.Image
import java.io.IOException

/**
 * Erstellt einen PlayerCharacter
 *
 * @author Kevin
 */
class PlayerCharacter(
        pictureFileName: List<String>,
        keyMap: Map<String, Direction>,
        x : Double = 250.0,
        y : Double = 750.0
) :
        ControlableActor(
                pictureFileName[0],
                x,
                y,
                keyMap) {
    /**
     * Überschreibt die Bewegung von Super, sodass die Bewegung nur nach Links und
     * Rechts möglich ist
     */
    override fun calculateDirectedSpeed(direction: Direction,
                                        movementSpeed: Double): DoubleArray {
        val xyTuple = DoubleArray(2)
        if (direction === Direction.Left) {
            xyTuple[0] = -movementSpeed
            xyTuple[1] = 0.0
        } else if (direction === Direction.Right) {
            xyTuple[0] = movementSpeed
            xyTuple[1] = 0.0
        }
        return xyTuple
    }

    /**
     * Ändert das Bild zum feuernden
     */
    fun switchFiring() {
        currentImage = firingImage
        isFiring = true
    }

    /**
     * Änder Player in Idle Modus
     */
    fun switchIdle() {
        currentImage = idleImage
        isFiring = false
    }

    /**
     * Gibt an ob der PlayerCharacter gerade schiesst
     *
     * @return ob PlayerCharacter gerade schiesst
     */
    private var isFiring: Boolean = false

    companion object {
        private lateinit var firingImage: Image
        private lateinit var idleImage: Image
    }

    /**
     * Setzt das zweite Bild der List<String> als feuerndes Bild und das erste Bild
    </String> */
    init {
        speed = 10.0
        if (pictureFileName.size >= 2) {
            try {
                idleImage = loadImage(pictureFileName[0])
                firingImage = loadImage(pictureFileName[1])
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
        }
    }
}
