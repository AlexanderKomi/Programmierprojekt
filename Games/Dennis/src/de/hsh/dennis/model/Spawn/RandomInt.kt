package de.hsh.dennis.model.Spawn

import java.util.concurrent.ThreadLocalRandom

object RandomInt {
    /**
     * Return a random int between min and max (both bounds inclusive)
     * @param min lower bound
     * @param max higher bound
     */
    fun randInt(min: Int, max: Int): Int {
        return ThreadLocalRandom.current().nextInt(min, max + 1)
    }
}
