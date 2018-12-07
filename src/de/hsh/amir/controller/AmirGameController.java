package de.hsh.amir.controller;

import de.hsh.amir.logic.AmirGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AmirGameController extends Observable implements Initializable {

    public static final String fxml = "view/AmirGame.fxml";
    private AmirGame game = new AmirGame();
    @FXML
    private Canvas gameCanvas;

    public void render(int fps) {
        game.render(gameCanvas, fps);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameCanvas.setFocusTraversable(true);
    }
}
