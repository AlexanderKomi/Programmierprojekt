package de.hsh.kevin.logic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import common.actor.Direction;
import de.hsh.kevin.logic.myActor.Paket;
import de.hsh.kevin.logic.myActor.PaketManager;
import de.hsh.kevin.logic.myActor.Player;
import de.hsh.kevin.logic.myActor.Projectile;
import de.hsh.kevin.logic.myActor.ProjectileManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameField {
    private double width;
    private double height;
    private Score score;
    private PaketManager paketManager;
    private ProjectileManager projectileManager;
    private Player player;
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
	spawnDelay = (int) (Config.getSpawnDelay());

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

    public double getWidth() {
	return width;
    }

    public double getHeight() {
	return height;
    }

    public int getLeben() {
	return leben.getLeben();
    }

    public int getScore() {
	return score.getScore();
    }

    public void draw(Canvas canvas) {
	paketManager.draw(canvas);
	projectileManager.draw(canvas);
    }

    public void movePlayer(KeyEvent keyEvent) {
	this.player.move(keyEvent);
    }

    public void movePakete() {
	paketManager.move();
    }

    public void moveProjectiles() {
	projectileManager.move();
    }

    public void moveAll() {
	movePakete();
	moveProjectiles();
    }

    private void initPlayer(Canvas canvas) {
	HashMap<String, Direction> playerKeyMap = new HashMap<>();
	playerKeyMap.put("A", Direction.Left);
	playerKeyMap.put("D", Direction.Right);

	// TODO Movement nach links (Taste rechts) ist dauerhaft (Easteregg)
	playerKeyMap.put("Left", Direction.Right);
	playerKeyMap.put("Right", Direction.Left);

	ArrayList<String> playerImages = new ArrayList<>();
	playerImages.add(Config.resLocation + "player/player1.png");
	playerImages.add(Config.resLocation + "player/player2.png");

	Player p = null;
	try {
	    p = new Player(playerImages, playerKeyMap);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	p.setPos(width / 2 - p.getWidth() / 2, height - 65);
	player = p;
    }

    public Player getPlayer() {
	return player;
    }

    public List<Paket> getPakete() {
	return paketManager.getPakete();
    }

    private void addPaket() {
	paketManager.createNewPaket(0.75);
    }

    public void spawnPakete() {
	if (spawnDelayBuffer == 0) {
	    spawnDelayBuffer = spawnDelay;
	}

	if (spawnDelayBuffer == spawnDelay) {
	    Random rand = new Random();
	    int number = rand.nextInt(Config.getMaxSpawnCount());
	    for (int i = 0; i <= number; i++) {
		addPaket();
	    }
	}
	spawnDelayBuffer--;
    }

    public List<Projectile> getProjectiles() {
	return projectileManager.getProjectiles();
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
}