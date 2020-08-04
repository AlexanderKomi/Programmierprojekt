package de.hsh.alexander.actor.level_elements

import common.actor.LevelElement
import de.hsh.alexander.actor.ResourcePaths

class SMD(x: Double, y: Double) : LevelElement(x,
                                               y,
                                               *ResourcePaths.Actor.LevelElements.SMD.pictures) {
    companion object {
        private const val default_delay = 30
        private const val scale = 1.2
    }

    init {
        setSwitchingDelay(default_delay.toDouble())
        scaleImage(scale)
    }
}
