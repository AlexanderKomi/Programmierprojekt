package de.hsh.amir.logic;

import common.util.Logger;
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
        Gegner gegner = new Gegner("/de/hsh/amir/resources/enemyFigur.png", 1);
        gegner.addObserver(this);
        this.gegnerListe.add(gegner);
    }

    /**
     * erstellt minimal einen und maximal fünf Gegner unabhängig
     * übergebenen Parameter
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
     * @param canvas
     */
    public void move(Canvas canvas) {
        Random random = new Random();
        int zufallsZahl = 200 + random.nextInt(1000);
        for (Gegner gegner : gegnerListe) {
            gegner.move();
            if (gegner.getY() >= canvas.getHeight()) {
                gegner.setY(-30);
                gegner.setX(zufallsZahl);
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
