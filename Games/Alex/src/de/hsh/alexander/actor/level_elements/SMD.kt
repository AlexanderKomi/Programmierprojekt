package de.hsh.alexander.actor.level_elements

import common.actor.LevelElement
import de.hsh.alexander.actor.ResourcePaths.Actor.LevelElements.SMD.pictures

class SMD(x: Double, y: Double) : LevelElement(x, y, pictures, delay = 30) {
    init {
        scaleImage(1.2)
    }
}
