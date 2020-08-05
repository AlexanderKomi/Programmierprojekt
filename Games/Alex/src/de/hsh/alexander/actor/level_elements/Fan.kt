package de.hsh.alexander.actor.level_elements

import common.actor.LevelElement
import de.hsh.alexander.actor.ResourcePaths

class Fan @JvmOverloads constructor(x: Double, y: Double, scale: Double = this.scale) :
        LevelElement(x, y, *ResourcePaths.Actor.LevelElements.Fan.pictures) {
    companion object {
        private const val default_delay = 3.0
        private const val scale = 3.4
    }

    init {
        this.switchingDelay = (default_delay)
        scaleImage(scale)
    }
}
