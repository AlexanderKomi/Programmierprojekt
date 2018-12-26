package de.hsh.amir.logic;

import common.util.Logger;
import de.hsh.amir.controller.AmirsMainMenuController;
import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GegnerManager implements Observer {
    public static final int SPIELFELD_BREITE = 1200;
    public static final int RAND_ABSCHNITT = 200;
    private ArrayList<Gegner> gegnerListe = new ArrayList<Gegner>();
    private Score points;

    public GegnerManager() {
    }

    public ArrayList<Gegner> getGegnerListe() {
        return gegnerListe;
    }

    /**
     * Zeichnet jede Gegnerfigur auf die Canvas.
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        for (Gegner g : gegnerListe) {
            g.draw(canvas);
        }
    }

    /**
     * Erstellt einen Gegner und setzt ihn an eine zufällig x-Position.
     */
    public void erstelleGegner() {
        Random random = new Random();
        int zufallsZahl = RAND_ABSCHNITT + random.nextInt(SPIELFELD_BREITE - 2 * RAND_ABSCHNITT);
        Gegner gegner = new Gegner("/de/hsh/amir/resources/enemyFigur.png", zufallsZahl);
        gegner.addObserver(this);
        this.gegnerListe.add(gegner);
    }

    /**
     * Erstellt minimal einen und maximal fünf Gegner UNABHÄNGIG!!! vom
     * übergebenen Parameter
     *
     * @param anzahlGegner unabhöngig von diesem Parameter werden "max" Gegner erstellt.
     */
    public void erstelleGegner(int anzahlGegner) {
        //CHANGE ONLY THIS IF YOU WANT TO CHANGE THE NUMBER OF GEGNER!!!
        int max = 5;

        if (anzahlGegner <= 0) {
            max = 1;
        } else if (anzahlGegner <= max) {
            max = anzahlGegner;
        } else {
            //Let max be as it is.
        }

        for (int i = 0; i < max; i++) {
            erstelleGegner();
        }
    }

    /**
     * Bewegt Gegner jedes mal an einer neuen Position.
     * Bei Level 2 bewegen sich die Figuren schneller.
     * Bei Level 3 bewegen sich die Figuren schneller und auch teilweise diagonal.
     *
     * @param canvas
     */
    public void move(Canvas canvas) {
        Random random = new Random();
        int zufallsZahl = RAND_ABSCHNITT + random.nextInt(SPIELFELD_BREITE - 2 * RAND_ABSCHNITT);
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
                gegner.move();
                if (gegner.getY() >= canvas.getHeight()) {
                    gegner.setY(-30);
                    gegner.setX(zufallsZahl);
                }
            }
        } else if (levelNumber == 3) {
            for (Gegner gegner : gegnerListe) {
                if (gegnerListe.indexOf(gegner) % 2 == 0) {
                    gegner.move();
                } else {
                    gegner.moveDiagonal();
                }
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
