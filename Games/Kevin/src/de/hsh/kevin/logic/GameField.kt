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
    private val width: Double = canvas.width
    private val height: Double = canvas.height

    @JvmField
    var player: PlayerCharacter? = null
    private val paketManager: PaketManager
    private val projectileManager: ProjectileManager
    private val leben: Leben = Leben()
    private val spawnDelay: Int = Config.getPaketSpawnDelay()
    private var spawnDelayBuffer = 0
    private var projectileSpawning: Boolean


    /**
     * Erstellt Pakete, wenn spawnDelay vergangen
     */
    private fun spawnPakete() {
        if (spawnDelayBuffer == 0) {
            spawnDelayBuffer = spawnDelay
        }
        if (spawnDelayBuffer == spawnDelay) {
            val number = Random().nextInt(Config.maxSpawnCount) + 1
            paketManager.createNewPaket(0.75, number)
        }
        spawnDelayBuffer--
    }

    /**
     * Aktiviert Spawnen von Projectilen (Schusstaste ist gedrückt)
     */
    fun activateProjectileSpawn() {
        projectileSpawning = true
    }

    /**
     * Deaktiviert Spawnen von Projectilen (Schusstaste ist nicht mehr gedrückt)
     */
    fun deactivateProjectileSpawn() {
        projectileSpawning = false
    }

    /**
     * Änder Player in Idle Modus
     */
    fun setPlayerIdle() {
        player!!.switchIdle()
    }

    /**
     * Bewegt Player
     */
    fun movePlayer(keyEvent: KeyEvent?) {
        player!!.move(keyEvent)
    }

    /**
     * Bewegt Pakete und Projectile
     */
    private fun moveAll() {
        val t1 = Thread(Runnable { paketManager.move() })
        t1.start()
        val t2 = Thread(Runnable { projectileManager.move() })
        t2.start()
        try {
            t1.join()
            t2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * Zeichnet alles aufs Canvas
     */
    private fun draw(canvas: Canvas) {
        clearCanvas(canvas)
        player!!.draw(canvas)
        paketManager.draw(canvas)
        projectileManager.draw(canvas)
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
            projectileManager.createProjectile(player!!.pos, player!!.width / 4)
        }
        draw(canvas)
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
        toRemove.forEach { _ ->
            Sound.playSound(enmSounds.collision)
            leben.decrease()
        }
        for (paket in toRemove) {
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
        for (paket in toRemovePakete) {
            paketManager.remove(paket)
        }
        for (toRemoveProjectile in toRemoveProjectiles) {
            projectileManager.remove(toRemoveProjectile)
        }
    }

    init {
        paketManager = PaketManager(width, height, score, leben)
        projectileManager = ProjectileManager()
        projectileSpawning = false

        val playerKeyMap = HashMap<String, Direction>()
        playerKeyMap["A"] = Direction.Left
        playerKeyMap["D"] = Direction.Right
        // Easteregg Steuerung invertiert
        playerKeyMap["Left"] = Direction.Right
        playerKeyMap["Right"] = Direction.Left

        val p = PlayerCharacter(
            listOf(Config.resLocation + "player/player1.png",
                   Config.resLocation + "player/player2.png"),
            playerKeyMap)
        p.setPos(width / 2 - p.width / 2, height - 65)
        player = p
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
