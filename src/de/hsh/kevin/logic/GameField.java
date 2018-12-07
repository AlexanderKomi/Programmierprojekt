package de.hsh.kevin.logic;

import java.io.FileNotFoundException;
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
import javafx.scene.input.KeyEvent;

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

    public GameField(Canvas canvas, Score score) {
        this(Config.getDifficultyOption(), canvas, score);
    }

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

    public void setScore(Score score) {
        this.score = score;
    }

    public int getLeben() {
        return leben.getLeben();
    }

    public int getScore() {
        return score.getScore();
    }

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
        try {
            p = new PlayerCharacter(playerImages, playerKeyMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        p.setPos(width / 2 - p.getWidth() / 2, height - 65);
        player = p;
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void spawnPakete() {
        if (spawnDelayBuffer == 0) {
            spawnDelayBuffer = spawnDelay;
        }

        if (spawnDelayBuffer == spawnDelay) {
            Random rand = new Random();
            int number = rand.nextInt(Config.getMaxSpawnCount());
            paketManager.createNewPaket(Config.spawnChanceGtoB, number);
        }
        spawnDelayBuffer--;
    }

    public void activateProjectileSpawn() {
        projectileSpawning = true;
    }

    public void deactivateProjectileSpawn() {
        projectileSpawning = false;
    }

    public void spawnProjectile() {
        if (projectileSpawning) {
            projectileManager.createProjectile(player.getPos(), player.getWidth() / 4);
        }
    }

    public void setPlayerFiring() {
        player.switchFiring();
    }

    public void setPlayerIdle() {
        player.switchIdle();
    }

    public void movePlayer(KeyEvent keyEvent) {
        this.player.move(keyEvent);
    }

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

    public void draw(Canvas canvas) {
        player.draw(canvas);
        paketManager.draw(canvas);
        projectileManager.draw(canvas);
    }

    public void game(Canvas canvas) {
        collision();
        moveAll();
        spawnPakete();
        spawnProjectile();
        draw(canvas);
    }

    private void collision() {
        collisionProjectilePaket();
        collisionPlayerPaket();
    }

    private void collisionPlayerPaket() {
        ArrayList<Paket> toRemove = new ArrayList<>();

        paketManager.getPakete().stream().parallel().forEach(p -> {
            if (player.doesCollide(p)) {
                leben.decrease();
                toRemove.add(p);
            }
        });

        for (int i = 0; i < toRemove.size(); i++) {
            paketManager.remove(toRemove.get(i));
        }
    }

    private void collisionProjectilePaket() {
        ArrayList<Paket> toRemovePakete = new ArrayList<>();
        ArrayList<Projectile> toRemoveProjectiles = new ArrayList<>();

        paketManager.getPakete().stream().parallel().forEach(paket -> {
            projectileManager.getProjectiles().stream().parallel().forEach(proj -> {
                if (paket.doesCollide(proj)) {
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