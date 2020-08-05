package de.hsh.kevin.logic

import common.actor.Direction
import de.hsh.kevin.logic.myActor.*
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import java.util.*

/**
 * Verwaltet Spiellogik
 *
 * @author Kevin
 */
class GameField(canvas: Canvas, private val score: Score) {

    var player: PlayerCharacter = PlayerCharacter(
        listOf(Config.resLocation + "player/player1.png",
               Config.resLocation + "player/player2.png"),
        mapOf("A" to Direction.Left,
                "D" to Direction.Right,
                // Easteregg Steuerung invertiert
                "Left" to Direction.Right,
                "Right" to Direction.Left),
            canvas.width / 2 - 250.0 / 2,
            canvas.height - 65
        )
    private val leben: Leben = Leben()
    private val paketManager: PaketManager = PaketManager(canvas.width, canvas.height, score, leben)
    private val projectileManager: ProjectileManager = ProjectileManager()
    private val spawnDelay: Int = Config.getPaketSpawnDelay()
    private var spawnDelayBuffer = 0

    /**
     * Aktiviert Spawnen von Projectilen (Schusstaste ist gedrückt)
     */
    var projectileSpawning: Boolean = false


    /**
     * Erstellt Pakete, wenn spawnDelay vergangen
     */
    private fun spawnPakete() {
        if (spawnDelayBuffer == 0) {
            spawnDelayBuffer = spawnDelay
        }
        if (spawnDelayBuffer == spawnDelay) {
            paketManager.createNewPaket(
                    0.75,
                    Random().nextInt(Config.maxSpawnCount) + 1)
        }
        spawnDelayBuffer--
    }

    /**
     * Bewegt Pakete und Projectile
     */
    private fun moveAll() {
        val t1 = Thread { paketManager.move() }
        t1.start()
        val t2 = Thread { projectileManager.move() }
        t2.start()
        try {
            t1.join()
            t2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * Führt die nächste Spieliteration aus
     */
    fun game(canvas: Canvas) {
        collisionProjectilePaket()
        collisionPlayerPaket()
        moveAll()
        spawnPakete()
        if (projectileSpawning) {
            projectileManager.createProjectile(player.pos, player.width / 4)
        }
        clearCanvas(canvas)
        player.draw(canvas)
        paketManager.draw(canvas)
        projectileManager.draw(canvas)
    }

    /**
     * Übermalt das Canvas
     */
    fun clearCanvas(canvas: Canvas) {
        val gc = canvas.graphicsContext2D
        gc.fill = Color.rgb(100, 100, 100)
        gc.fillRect(0.0, 0.0, canvas.width, canvas.height)
    }

    /**
     * Prüft Collision Player - Pakete und spielt Sound bei Collision
     */
    private fun collisionPlayerPaket() {
        val toRemove = paketManager.pakete.filter { p: Paket? -> player!!.doesCollide(p) }
        toRemove.forEach { paket ->
            Sound.playSound(enmSounds.collision)
            leben.decrease()
            paketManager.remove(paket)
        }
    }

    /**
     * Prüft Collision - Paket Prokectile und spielt Sound bei Collision
     */
    private fun collisionProjectilePaket() {
        val toRemovePakete = ArrayList<Paket>()
        val toRemoveProjectiles = ArrayList<Projectile>()
        paketManager.pakete.forEach{ paket: Paket ->
            projectileManager.projectile
                .filter { proj -> paket.doesCollide(proj) }
                .forEach{ proj: Projectile ->
                    Sound.playSound(enmSounds.hit)
                    if (!toRemovePakete.contains(paket)) {
                        toRemovePakete.add(paket)
                    }
                    if (!toRemoveProjectiles.contains(proj)) {
                        toRemoveProjectiles.add(proj)
                    }
                    if (paket.paketTyp === enmPaketTyp.good) {
                        leben.decrease()
                    }
                }
        }
        toRemovePakete.forEach { paket ->
            paketManager.remove(paket)
        }
        toRemoveProjectiles.forEach { toRemoveProjectile ->
            projectileManager.remove(toRemoveProjectile)
        }
    }

    /**
     * Liefert Anzahl aktueller Leben
     *
     * @return anzahl Leben
     */
    fun getLeben(): Int {
        return leben.leben
    }

    /**
     * Liefert aktuellen Score
     */
    fun getScore(): Int {
        return score.score
    }
}
