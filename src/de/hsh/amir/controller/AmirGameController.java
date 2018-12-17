package de.hsh.amir.controller;

import de.hsh.amir.logic.AmirGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AmirGameController extends Observable implements Initializable {

    public static final String fxml = "view/AmirGame.fxml";
    private AmirGame game;
    private boolean initialized = false;
    @FXML
    private Canvas gameCanvas;

    public void render(int fps) {
        if (initialized) {
            if (game != null) {
                game.render(fps);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(event -> {
            game.onKeyPressed(event);
        });
        gameCanvas.setOnKeyReleased(event -> {
            game.onKeyPressed(event);
        });
        game = new AmirGame(gameCanvas);
        game.reset();
        initialized = true;
    }
}
