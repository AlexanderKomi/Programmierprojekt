package de.hsh.amir.logic;

import common.actor.Direction;
import common.util.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class AmirGame {
    private Spielfigur spielfigur;
    private Gegner gegner;
    private Canvas canvas;

    public AmirGame(Canvas canvas) {
        this.canvas = canvas;
    }

    public void initializePlayer() {
        this.spielfigur = new Spielfigur();
        spielfigur.setPos(canvas.getWidth() / 2, canvas.getHeight() - 100);
    }

    public void render(int fps) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        spielfigur.draw(canvas);
    }

    public void reset() {
        initializePlayer();
    }

    public void onKeyPressed(KeyEvent event) {
        this.spielfigur.move(event);
    }
}
