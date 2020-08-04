/**
 * @author Julian Sender
 */
package de.hsh.Julian

import common.actor.Collectable
import common.config.WindowConfig

/**
 * Enemyclass is collectable
 */
internal class Enemy private constructor(imagePath: String) : Collectable(imagePath) {
    companion object {
        /**
         * GETTERS AND SETTERS
         *
         */
        const val imageLocation = Leertastenklatsche.location + "enemyvirus.png"
        const val imageLocation2 = Leertastenklatsche.location + "enemyvirus_turned.png"

        /**
         * Create enemys and let them spawn randomly left or right
         * @return
         */
        @JvmStatic
        fun createEnemy(): Enemy {
            val enemyvirus = Enemy(imageLocation)
            var px = WindowConfig.window_width - enemyvirus.width
            /*double px = 350 * Math.random() + 50;
        double py = 350 * Math.random() + 50;*/
            val rng = Math.random()
            if (rng < 0.5) {
                px = enemyvirus.width
                enemyvirus.setCurrentImage(imageLocation2)
            }
            //Logger.log(this.getClass() + ": rng=" + rng);
            enemyvirus.setPos(px, WindowConfig.window_height * 0.85)
            return enemyvirus
        }
    }
}
