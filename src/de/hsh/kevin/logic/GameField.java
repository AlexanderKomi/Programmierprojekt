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
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameField {
    private double width;
    private double height;
    private Score score;
    private PaketManager paketManager;
    private Player player;
    private Leben leben;
    private int spawnDelay;
    private int spawnDelayBuffer = 0;

    public GameField(Canvas canvas) {
	this(Config.getDifficultyOption(), canvas);
    }

    public GameField(enmDifficultyOptions difficulty, Canvas canvas) {
	width = canvas.getWidth();
	height = canvas.getHeight();
	spawnDelay = (int) (Config.getSpawnDelay());

	score = new Score();
	leben = new Leben();
	paketManager = new PaketManager(width, height);

	initPlayer(canvas);
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

    public Score getTIScore() {
	return score;
    }

    public int getScore() {
	return score.getScore();
    }

    public void draw(Canvas canvas) {
	paketManager.draw(canvas);
	paketManager.move();
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

    public void movePlayer(KeyEvent keyEvent) {
	this.player.move(keyEvent);
    }

    public Player getPlayer() {
	return player;
    }

    public List<Paket> getPakete() {
	return paketManager.getPakete();
    }

    public void addPaket() {
	paketManager.createNewPaket(0.75);
    }

    public void addGoodPaket() {
	paketManager.createGoodPaket();
    }

    public void addBadPaket() {
	paketManager.createBadPaket();
    }

    public void spawnPakete() {
	if (spawnDelayBuffer == 0) {
	    spawnDelayBuffer = spawnDelay;
	}

	if (spawnDelayBuffer == spawnDelay) {
	    Random rand = new Random();
	    int number = rand.nextInt(Config.getMaxSpawnCount());
	    for(int i = 0; i <= number; i++) {
		addPaket();
	    }
	}
	spawnDelayBuffer--;
    }
}