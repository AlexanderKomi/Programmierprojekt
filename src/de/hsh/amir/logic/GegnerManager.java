package de.hsh.amir.logic;

import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class GegnerManager {
    private ArrayList<Gegner> gegnerListe = new ArrayList<Gegner>();
    private Score points;

    public GegnerManager() {

    }

    //TODO
    public GegnerManager(ArrayList<Gegner> gegnerListe) {
        this.gegnerListe = gegnerListe;
    }

    public ArrayList<Gegner> getGegnerListe() {
        return gegnerListe;
    }

    //TODO
    public void draw(Canvas canvas) {
        for (Gegner g : gegnerListe) {
            g.draw(canvas);
        }
    }

    public void erstelleGegner() {
        Gegner gegner = new Gegner("/de/hsh/amir/resources/enemyFigur.png", 1);
        this.gegnerListe.add(gegner);
    }

    public void move() {
        for (Gegner gegner : gegnerListe) {
            gegner.move();
        }
    }
}
