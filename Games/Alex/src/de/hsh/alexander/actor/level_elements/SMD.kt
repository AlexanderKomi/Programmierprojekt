package de.hsh.alexander.actor.level_elements

import common.actor.LevelElement
import de.hsh.alexander.actor.ResourcePaths

class SMD(x: Double, y: Double) :
        LevelElement(x, y, ResourcePaths.Actor.LevelElements.SMD.pictures, delay = 30.0) {

    init {
        scaleImage(1.2)
    }
}
