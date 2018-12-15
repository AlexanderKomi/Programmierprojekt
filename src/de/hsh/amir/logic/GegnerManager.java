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
        Gegner gegner = new Gegner("/de/hsh/amir/resources/enemyFigur.png", zufallsZahl1);
        gegner.addObserver(this);
        this.gegnerListe.add(gegner);
    }

    /**
     * erstellt minimal einen und maximal 5 Gegner unabhängig
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
            //TODO #4 moving behavior of the enemies must be changed
            for (Gegner gegner : gegnerListe) {
                gegner.move();
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
