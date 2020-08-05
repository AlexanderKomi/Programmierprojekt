package de.hsh.alexander.actor.level_elements

import common.actor.LevelElement
import de.hsh.alexander.actor.ResourcePaths

class Fan(x: Double, y: Double, scale: Double = 3.4) :
        LevelElement(x, y, ResourcePaths.Actor.LevelElements.Fan.pictures, 3.0) {

    init {
        scaleImage(scale)
    }
}
