package de.hsh.amir.logic;

import de.hsh.amir.controller.AmirsMainMenuController;
import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GegnerManager implements Observer {
    private ArrayList<Gegner> gegnerListe = new ArrayList<Gegner>();
    private Score points;

    public GegnerManager() {
    }

    public ArrayList<Gegner> getGegnerListe() {
        return gegnerListe;
    }


    public void draw(Canvas canvas) {
        for (Gegner g : gegnerListe) {
            g.draw(canvas);
        }
    }

    public void erstelleGegner() {
        Random random = new Random();
        int zufallsZahl1 = 200 + random.nextInt(800);
        Gegner gegner = new Gegner("/de/hsh/amir/resources/enemyFigur.png", zufallsZahl1, 1);
        gegner.addObserver(this);
        this.gegnerListe.add(gegner);
    }

    /**
     * erstellt minimal einen und maximal fünf Gegner unabhängig
     * übergebenen Parameter //TODO
     *
     * @param anzahlGegner
     */
    public void erstelleGegner(int anzahlGegner) {
        if (anzahlGegner <= 1) {
            erstelleGegner();
        } else if (anzahlGegner >= 5) {
            for (int i = 1; i <= 5; i++) {
                erstelleGegner();
            }
        } else {
            for (int i = 1; i <= anzahlGegner; i++) {
                erstelleGegner();
            }
        }
    }

    /**
     * Bewegt Gegner jedes mal an einer neuen Position.
     * Bei Level 2 bewegen sich die Figuren diagonal.
     *
     * @param canvas
     */
    public void move(Canvas canvas) {
        Random random = new Random();
        int zufallsZahl = 200 + random.nextInt(800);
        int levelNumber = AmirsMainMenuController.LEVEL_NUMBER;
        if (levelNumber == 1) {
            for (Gegner gegner : gegnerListe) {
                gegner.move();
                if (gegner.getY() >= canvas.getHeight()) {
                    gegner.setY(-30);
                    gegner.setX(zufallsZahl);
                }
            }
        } else if (levelNumber == 2) {
            for (Gegner gegner : gegnerListe) {
                gegner.moveDiagonal();
                if (gegner.getY() >= canvas.getHeight()) {
                    gegner.setY(-30);
                    gegner.setX(zufallsZahl);
                }
            }
        } else {
            for (Gegner gegner : gegnerListe) {
                gegner.moveDiagonal();
                if (gegner.getY() >= canvas.getHeight()) {
                    gegner.setY(-30);
                    gegner.setX(zufallsZahl);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void remove(Gegner gegner) {
        gegnerListe.remove(gegner);
    }
}
