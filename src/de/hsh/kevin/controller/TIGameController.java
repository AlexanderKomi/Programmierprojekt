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
import common.util.Logger;
import de.hsh.kevin.logic.GameField;
import de.hsh.kevin.logic.TIConfig;

public class TIGameController extends Observable implements Initializable {

    public static final String fxml = "res/TIGame.fxml";
    private boolean initialized = false;

    private GameField gameField;

    @FXML
    public Canvas gameCanvas;

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

	if(gameCanvas == null) {
	    Logger.log(this.getClass() + ": Canvas is Null");
	    return;
	}
	
	this.gameCanvas.setFocusTraversable(true);

	double widthFactor = TIConfig.getDifficultyFactor();
	this.gameCanvas.setWidth(gameCanvas.getWidth() * widthFactor);

	clearCanvas();

	gameField = new GameField(this.gameCanvas);
	this.gameCanvas.setOnKeyPressed(gameField::movePlayer);
	this.gameCanvas.setOnKeyReleased(gameField::movePlayer);

	initialized = true;
    }

    private void clearCanvas() {
	GraphicsContext gc = gameCanvas.getGraphicsContext2D();
	gc.setFill(Color.rgb(100, 100, 100));
	gc.fillRect(0, 0, this.gameCanvas.getWidth(), gameCanvas.getHeight());
    }

    public void render(int fps) {
	if (!initialized) {
	    return;
	}
	clearCanvas();

	this.gameField.getPlayer().draw(this.gameCanvas);
	if (gameField.getPakete().size() == 0) {
	    this.gameField.addPaket();
	    this.gameField.addPaket();
	}
	this.gameField.draw(this.gameCanvas);
    }
}
