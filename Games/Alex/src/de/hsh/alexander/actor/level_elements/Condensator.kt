package de.hsh.alexander.actor.level_elements

import common.actor.LevelElement
import de.hsh.alexander.actor.ResourcePaths

class Condensator : LevelElement {
    constructor(x: Double, y: Double) : super(x,
                                              y,
                                              ResourcePaths.Actor.LevelElements.Condensator.full_picture,
                                              default_full_scale) {
    }

    constructor(x: Double, y: Double, index: Int) : super(x,
                                                          y,
                                                          ResourcePaths.Actor.LevelElements.Condensator.pictures[index],
                                                          default_scale) {
    }

    companion object {
        private const val default_full_scale = 0.75
        private const val default_scale = 1.0
    }
}
