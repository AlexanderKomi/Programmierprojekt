package de.hsh.kevin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

import common.actor.Direction;
import common.updates.UpdateCodes;
import common.util.Logger;
import common.util.Path;
import de.hsh.kevin.logic.GameField;
import de.hsh.kevin.logic.TIConfig;
import de.hsh.kevin.logic.myActor.*;

public class TIGameController extends Observable implements Initializable {

    private static final String resLocation = Path.getExecutionLocation() + "de/hsh/kevin/res/";
    public static final String fxml = "res/TIGame.fxml";
    private boolean initialized = false;

    private GameField gamefield;
    private Player player;
    private ArrayList<Paket> pakete;

    @FXML
    public Canvas gameCanvas;

    @FXML
    public Button btn_score;

    @FXML
    public Label lbl_leben;

    @FXML
    public Label lbl_score;

    public TIGameController() {
	gamefield = new GameField();
	pakete = new ArrayList<>();
    }

    // Vorläufiger Button zum Testen der Funktionalität
    @FXML
    void scorePressed(ActionEvent event) {
	this.setChanged();
	this.notifyObservers(UpdateCodes.TunnelInvader.gameOver);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	if (initialized) {
	    return;
	}
	gameCanvas.setFocusTraversable(true);
	gameCanvas.setWidth(gamefield.getWidth());
	
	clearCanvas();
	
	this.gameCanvas.setOnKeyPressed(this::movePlayer);
	this.gameCanvas.setOnKeyReleased(this::movePlayer);

	try {
	    player = initPlayer();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	initialized = true;
    }
    
    private void clearCanvas() {
	GraphicsContext gc = gameCanvas.getGraphicsContext2D();
	// TODO vllt. Alpha auf 0.75 setzten
	gc.setFill(Color.rgb(100, 100, 100));
	gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
    }

    private Player initPlayer() throws FileNotFoundException {
	HashMap<String, Direction> playerKeyMap = new HashMap<>();
	playerKeyMap.put("A", Direction.Left);
	playerKeyMap.put("D", Direction.Right);

	// TODO Movement nach links (Taste rechts) ist dauerhaft (Easteregg)
	playerKeyMap.put("Left", Direction.Right);
	playerKeyMap.put("Right", Direction.Left);

	ArrayList<String> playerImages = new ArrayList<>();
	playerImages.add(resLocation + "player/player1.png");
	playerImages.add(resLocation + "player/player2.png");

	Player p = new Player(playerImages, playerKeyMap);
	p.setPos(gameCanvas.getWidth() / 2 - p.getWidth() / 2, gameCanvas.getHeight() - 65);
	return p;
    }

    private void movePlayer(KeyEvent keyEvent) {
	player.move(keyEvent);

	// TODO Collision mit anderen Packages
    }

    public void render() {
	if (!initialized) {
	    return;
	}
	clearCanvas();
	
	this.player.draw(this.gameCanvas);

	if (pakete.size() == 0) {
	    pakete.add(getBadPaket());
	    pakete.add(getGoodPaket());
	}
	pakete.get(0).draw(gameCanvas);
	pakete.get(1).draw(gameCanvas);
    }

    private Paket getBadPaket() {
	ArrayList<String> badPaketImages = new ArrayList<>();
	badPaketImages.add(resLocation + "ordner_red/ordner1.png");
	badPaketImages.add(resLocation + "ordner_red/ordner2.png");
	badPaketImages.add(resLocation + "ordner_red/ordner3.png");
	badPaketImages.add(resLocation + "ordner_red/ordner4.png");

	Paket bp = null;
	try {
	    bp = new Paket(badPaketImages, enmPaketTyp.bad);
	    bp.setPos(10, 10);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return bp;
    }
    
    private Paket getGoodPaket() {
	ArrayList<String> badPaketImages = new ArrayList<>();
	badPaketImages.add(resLocation + "ordner_black/ordner1.png");
	badPaketImages.add(resLocation + "ordner_black/ordner2.png");
	badPaketImages.add(resLocation + "ordner_black/ordner3.png");
	badPaketImages.add(resLocation + "ordner_black/ordner4.png");

	Paket bp = null;
	try {
	    bp = new Paket(badPaketImages, enmPaketTyp.good);
	    bp.setPos(50, 10);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return bp;
    }
}
