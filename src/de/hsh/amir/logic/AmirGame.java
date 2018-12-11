package de.hsh.amir.logic;

import common.util.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class AmirGame {
    private Spielfigur spielfigur;
    private GegnerManager gegnerManager;
    private Canvas canvas;

    public AmirGame(Canvas canvas) {
        this.canvas = canvas;
    }

    public void initializePlayer() {
        this.spielfigur = new Spielfigur();
        spielfigur.setPos(canvas.getWidth() / 2, canvas.getHeight() - 100);
    }

    public void render(int fps) {
        gegnerManager.move(canvas);
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        spielfigur.draw(canvas);
        gegnerManager.draw(canvas);
    }

    /**
     * setze Spiel zurück und initialisiert es neu
     */
    public void reset() {
        initializePlayer();
        gegnerManager = new GegnerManager();
        gegnerManager.erstelleGegner();
    }

    public void onKeyPressed(KeyEvent event) {
        this.spielfigur.move(event);
    }
}
