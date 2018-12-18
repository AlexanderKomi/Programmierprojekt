package de.hsh.amir.logic;

import common.util.AudioPlayer;
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


    public AmirGame(Canvas canvas, Score points) {
        this.points = points;
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
        clearCanvasForPlayer();
        spielfigur.draw(canvas);
        gegnerManager.draw(canvas);
    }

    private void playSound() {
        AudioPlayer.MusicPlayer.loadFile(this.getClass().getResource("../resources/clickSound.mp3").getPath());
        AudioPlayer.MusicPlayer.play();
    }

    /**
     * Radiert die Canvas komplett für den Weg der Spielfigur
     */
    private void clearCanvasForPlayer() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Collisionsabfrage.
     * Punkntestand wird um 1 erhöht, wenn ein Gegner eingesammelt wird.
     */
    public void collisionGegnerSpieler() {
        ArrayList<Gegner> toRemove = new ArrayList<Gegner>();
        for (Gegner gegner : gegnerManager.getGegnerListe()) {
            if (spielfigur.doesCollide(gegner)) {
                points.increase();

                playSound();

                toRemove.add(gegner);
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
        points.setScore(0);
    }

    public void onKeyPressed(KeyEvent event) {
        this.spielfigur.move(event);
    }
}
