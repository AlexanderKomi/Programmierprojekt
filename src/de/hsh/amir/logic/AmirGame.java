package de.hsh.amir.logic;

import common.util.Logger;
import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class AmirGame {
    private Spielfigur spielfigur;
    private Score points;
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
        collisionGegnerSpieler();
        gegnerManager.move(canvas);
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        spielfigur.draw(canvas);
        gegnerManager.draw(canvas);
    }


    /**
     * Collisionsabfrage
     */
    public void collisionGegnerSpieler() {
        ArrayList<Gegner> toRemove = new ArrayList<Gegner>();
        for (Gegner gegner : gegnerManager.getGegnerListe()) {
            if (spielfigur.doesCollide(gegner)) {
                points.increase();
                toRemove.add(gegner);
            }
        }

        for (int i = 1; i <= toRemove.size(); i++) {
            gegnerManager.remove(toRemove.get(i));
        }


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
