package de.hsh.kevin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import common.updates.UpdateCodes;
import de.hsh.kevin.logic.GameField;
import de.hsh.kevin.logic.TIConfig;

public class TIGameController extends Observable implements Initializable {

    public static final String fxml = "res/TIGame.fxml";
    private boolean initialized = false;

    private GameField gameField;

    @FXML
    public Canvas canvas;

    @FXML
    public Button btn_score;

    @FXML
    public Label lbl_leben;

    @FXML
    public Label lbl_score;

    public TIGameController() {

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

	this.canvas.setFocusTraversable(true);

	double widthFactor = TIConfig.getDifficultyFactor();
	this.canvas.setWidth(canvas.getWidth() * widthFactor);

	clearCanvas();

//	gameField = new GameField(this.canvas);
//	this.canvas.setOnKeyPressed(gameField::movePlayer);
//	this.canvas.setOnKeyReleased(gameField::movePlayer);

	initialized = true;
    }

    private void clearCanvas() {
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.rgb(100, 100, 100));
	gc.fillRect(0, 0, this.canvas.getWidth(), canvas.getHeight());
    }

    public void render() {
	if (!initialized) {
	    return;
	}
	clearCanvas();

	this.gameField.getPlayer().draw(this.canvas);
	if (gameField.getPakete().size() == 0) {
	    this.gameField.addPaket();
	    this.gameField.addPaket();
	}
	this.gameField.draw(this.canvas);
    }
}
