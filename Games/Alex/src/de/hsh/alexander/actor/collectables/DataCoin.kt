package de.hsh.alexander.actor.collectables

import common.actor.Collectable
import de.hsh.alexander.actor.ResourcePaths
import java.util.concurrent.ThreadLocalRandom

class DataCoin : Collectable {

    constructor(x: Double, y: Double) :
            super(pictureFilePaths = ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths,
                  x = x, y = y,
                  delay = default_delay + ThreadLocalRandom.current().nextInt(0, 10),
                  scale = 1.2)

    constructor(x: Double, y: Double, scale: Double) :
            super(pictureFilePaths = ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths,
                  x = x, y = y,
                  delay = default_delay + ThreadLocalRandom.current().nextInt(0, 10),
                  scale = scale)

    companion object {
        private const val default_delay = 10
    }
}
