package de.hsh.Julian

import common.actor.Collectable
import common.config.WindowConfig
import java.util.*

/**
 * Enemyclass is collectable
 */
internal class Enemy(observer: Observer, imagePath: String = Leertastenklatsche.location + "enemyvirus.png") :
        Collectable(imagePath) {

    init {
        this.addObserver(observer)
        var px = WindowConfig.window_width - this.width
        if (Math.random() < 0.5) {
            px = this.width
            this.setCurrentImage(Leertastenklatsche.location + "enemyvirus_turned.png")
        }
        this.setPos(px, WindowConfig.window_height * 0.85)
    }
}
