package de.hsh.alexander.actor.collectables

import common.actor.Collectable
import de.hsh.alexander.actor.ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths
import java.util.concurrent.ThreadLocalRandom

class DataCoin : Collectable {

    constructor(x: Double, y: Double) : this(x, y, 1.2)

    constructor(x: Double, y: Double, scale: Double) :
            super(pictureFilePaths = pictureFilePaths, x = x, y = y,
                  delay = 10 + ThreadLocalRandom.current().nextInt(0, 10),
                  scale = scale)
}
