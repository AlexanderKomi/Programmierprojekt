package de.hsh.kevin.logic.myActor

import javafx.scene.canvas.Canvas
import java.util.*

/**
 * Verwaltet Projektile
 * @author Kevin
 */
class ProjectileManager {
    val projectile = ArrayList<Projectile>()
    private var lastProjectileSpawn: Long = 0

    /**
     * Erstellt ein neues Projektil bei dem Spieler
     * @param playerPos Position des Players wo Projektil erstellt werden soll
     * @param offset zur Position des Players
     */
    fun createProjectile(playerPos: DoubleArray, offset: Double) {
        val curMillis = System.currentTimeMillis()
        if (curMillis >= lastProjectileSpawn + 400) {
            projectile.add(Projectile(playerPos[0] + offset, playerPos[1]))
            lastProjectileSpawn = curMillis
        }
    }

    /**
     * Bewegt alle Pakete
     */
    fun move() {
        val toRemove = ArrayList<Projectile>()
        projectile.forEach{ proj: Projectile ->
            if (proj.pos[1] < 0) {
                toRemove.add(proj)
            } else {
                proj.move()
            }
        }
        for (value in toRemove) {
            projectile.remove(value)
        }
    }

    /**
     * Zeichnet alle Pakete aufs Canvas
     * @param canvas
     */
    fun draw(canvas: Canvas) {
        for (p in projectile) {
            p.draw(canvas)
        }
    }

    /**
     * Entfernt ein Paket
     * @param p zu entfernendes Paket
     */
    fun remove(p: Projectile?) {
        projectile.remove(p)
    }
}
