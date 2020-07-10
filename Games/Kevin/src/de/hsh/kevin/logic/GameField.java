package de.hsh.kevin.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import common.actor.Direction;
import de.hsh.kevin.logic.myActor.Paket;
import de.hsh.kevin.logic.myActor.PaketManager;
import de.hsh.kevin.logic.myActor.PlayerCharacter;
import de.hsh.kevin.logic.myActor.Projectile;
import de.hsh.kevin.logic.myActor.ProjectileManager;
import de.hsh.kevin.logic.myActor.enmPaketTyp;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Verwaltet Spiellogik
 * 
 * @author Kevin
 *
 */
public class GameField {
    private double width;
    private double height;

    private Score score;
    private PaketManager paketManager;
    private ProjectileManager projectileManager;
    private PlayerCharacter player;
    private Leben leben;

    private int spawnDelay;
    private int spawnDelayBuffer = 0;
    private boolean projectileSpawning;

    /**
     * Erstellt Spiel
     * 
     * @param canvas
     *            auf welchem Spiel stattfindet
     * @param score
     *            Object des Spiels
     */
    public GameField(Canvas canvas, Score score) {
        this(Config.getDifficultyOption(), canvas, score);
    }

    /**
     * Erstellt Spiel
     * 
     * @param difficulty
     *            Schwierigkeit des Spiels
     * @param canvas
     *            auf welchem Spiel stattfindet
     * @param score
     *            Object des Spiels
     */
    public GameField(enmDifficultyOptions difficulty, Canvas canvas, Score score) {
        width = canvas.getWidth();
        height = canvas.getHeight();
        spawnDelay = (int) (Config.getPaketSpawnDelay());

        this.score = score;
        leben = new Leben();
        paketManager = new PaketManager(width, height, score, leben);
        projectileManager = new ProjectileManager();
        projectileSpawning = false;

        initPlayer(canvas);
    }

    /**
     * Setzt den Score
     * 
     * @param score
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * Liefert Anzahl aktueller Leben
     * 
     * @return anzahl Leben
     */
    public int getLeben() {
        return leben.getLeben();
    }

    /**
     * Liefert aktuellen Score
     * 
     * @return
     */
    public int getScore() {
        return score.getScore();
    }

    /**
     * Initialisiert Player
     * 
     * @param canvas
     */
    private void initPlayer(Canvas canvas) {
        HashMap<String, Direction> playerKeyMap = new HashMap<>();
        playerKeyMap.put("A", Direction.Left);
        playerKeyMap.put("D", Direction.Right);

        // Easteregg Steuerung invertiert
        playerKeyMap.put("Left", Direction.Right);
        playerKeyMap.put("Right", Direction.Left);

        ArrayList<String> playerImages = new ArrayList<>();
        playerImages.add(Config.resLocation + "player/player1.png");
        playerImages.add(Config.resLocation + "player/player2.png");

        PlayerCharacter p = null;
        p = new PlayerCharacter(playerImages, playerKeyMap);
        p.setPos(width / 2 - p.getWidth() / 2, height - 65);
        player = p;
    }

    /**
     * Liefert PlayerCharacter
     * 
     * @return
     */
    public PlayerCharacter getPlayer() {
        return player;
    }

    /**
     * Erstellt Pakete, wenn spawnDelay vergangen
     */
    public void spawnPakete() {
        if (spawnDelayBuffer == 0) {
            spawnDelayBuffer = spawnDelay;
        }

        if (spawnDelayBuffer == spawnDelay) {
            Random rand = new Random();
            int number = rand.nextInt(Config.getMaxSpawnCount()) + 1;
            paketManager.createNewPaket(Config.spawnChanceGtoB, number);
        }
        spawnDelayBuffer--;
    }

    /**
     * Aktiviert Spawnen von Projectilen (Schusstaste ist gedrückt)
     */
    public void activateProjectileSpawn() {
        projectileSpawning = true;
    }

    /**
     * Deaktiviert Spawnen von Projectilen (Schusstaste ist nicht mehr gedrückt)
     */
    public void deactivateProjectileSpawn() {
        projectileSpawning = false;
    }

    /**
     * Spawnt Projectile
     */
    public void spawnProjectile() {
        if (projectileSpawning) {
            projectileManager.createProjectile(player.getPos(), player.getWidth() / 4);
        }
    }

    /**
     * Ändert Player in Firing Modus
     */
    public void setPlayerFiring() {
        player.switchFiring();
    }

    /**
     * Änder Player in Idle Modus
     */
    public void setPlayerIdle() {
        player.switchIdle();
    }

    /**
     * Bewegt Player
     * 
     * @param keyEvent
     */
    public void movePlayer(KeyEvent keyEvent) {
        this.player.move(keyEvent);
    }

    /**
     * Bewegt Pakete und Projectile
     */
    public void moveAll() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                paketManager.move();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                projectileManager.move();
            }
        });

        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Zeichnet alles aufs Canvas
     * 
     * @param canvas
     */
    public void draw(Canvas canvas) {
        clearCanvas(canvas);
        player.draw(canvas);
        paketManager.draw(canvas);
        projectileManager.draw(canvas);
    }

    /**
     * Führt die nächste Spieliteration aus
     * 
     * @param canvas
     */
    public void game(Canvas canvas) {
        collision();
        moveAll();
        spawnPakete();
        spawnProjectile();
        draw(canvas);
    }

    /**
     * Übermalt das Canvas
     * 
     * @param canvas
     */
    public void clearCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(100, 100, 100));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Prüft Collision Player - Pakete und Pakete - Projektile
     */
    private void collision() {
        collisionProjectilePaket();
        collisionPlayerPaket();
    }

    /**
     * Prüft Collision Player - Pakete und spielt Sound bei Collision
     */
    private void collisionPlayerPaket() {
        ArrayList<Paket> toRemove = new ArrayList<>();

        paketManager.getPakete().stream().parallel().forEach(p -> {
            if (player.doesCollide(p)) {
                Sound.playSound(enmSounds.collision);
                leben.decrease();
                toRemove.add(p);
            }
        });

        for (int i = 0; i < toRemove.size(); i++) {
            paketManager.remove(toRemove.get(i));
        }
    }

    /**
     * Prüft Collision - Paket Prokectile und spielt Sound bei Collision
     */
    private void collisionProjectilePaket() {
        ArrayList<Paket> toRemovePakete = new ArrayList<>();
        ArrayList<Projectile> toRemoveProjectiles = new ArrayList<>();

        paketManager.getPakete().stream().parallel().forEach(paket -> {
            projectileManager.getProjectiles().stream().parallel().forEach(proj -> {
                if (paket.doesCollide(proj)) {
                    Sound.playSound(enmSounds.hit);
                    if (!toRemovePakete.contains(paket)) {
                        toRemovePakete.add(paket);
                    }
                    if (!toRemoveProjectiles.contains(proj)) {
                        toRemoveProjectiles.add(proj);
                    }
                    if (paket.getPaketTyp() == enmPaketTyp.good) {
                        leben.decrease();
                    }
                }
            });
        });

        for (int i = 0; i < toRemovePakete.size(); i++) {
            paketManager.remove(toRemovePakete.get(i));
        }

        for (int i = 0; i < toRemoveProjectiles.size(); i++) {
            projectileManager.remove(toRemoveProjectiles.get(i));
        }
    }
}
