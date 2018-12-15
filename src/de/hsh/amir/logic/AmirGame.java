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
    private int timer;

    public AmirGame(Canvas canvas) {
        this.canvas = canvas;
    }

    public void initializePlayer() {
        this.spielfigur = new Spielfigur();
        spielfigur.setPos(canvas.getWidth() / 2, canvas.getHeight() - 100);
    }

    public void render(int fps) {
        timer++;
        if (timer == 120) {
            gegnerManager.erstelleGegner(4);
            timer = 0;
        }
        collisionGegnerSpieler();
        gegnerManager.move(canvas);
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        spielfigur.draw(canvas);
        gegnerManager.draw(canvas);
        if (spielGewonnen()) {
            //TODO
            jumpBackToMainMenu();
        }
    }

    //TODO
    private void jumpBackToMainMenu() {
    }

    private boolean spielGewonnen() {
        int punkte = points.getScore();
        if (punkte == 10) {
            return true;
        }
        return false;
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
                Logger.log(this.getClass() + " : bin in collisionGegnerSpiler");
            }
        }

        for (int i = 0; i < toRemove.size(); i++) {
            gegnerManager.remove(toRemove.get(i));
        }


    }


    /**
     * setze Spiel zurück und initialisiert es neu
     */
    public void reset() {
        initializePlayer();
        gegnerManager = new GegnerManager();
        points = new Score();
    }

    public void onKeyPressed(KeyEvent event) {
        this.spielfigur.move(event);
    }
}
