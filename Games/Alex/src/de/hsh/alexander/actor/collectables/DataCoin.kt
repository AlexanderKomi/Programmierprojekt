package de.hsh.alexander.actor.collectables

import common.actor.Collectable
import de.hsh.alexander.actor.ResourcePaths
import java.util.concurrent.ThreadLocalRandom

class DataCoin : Collectable {
    constructor(x: Double, y: Double) : super(x, y,
                                              default_delay + ThreadLocalRandom.current()
                                                      .nextInt(0, 10),
                                              default_scale,
                                              *ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths) {
    }

    constructor(x: Double, y: Double, scale: Double) : super(x, y,
                                                             default_delay + ThreadLocalRandom.current()
                                                                     .nextInt(0, 10),
                                                             scale,
                                                             *ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths) {
    }

    companion object {
        private const val default_delay = 10
        private const val default_scale = 1.2
    }
}
