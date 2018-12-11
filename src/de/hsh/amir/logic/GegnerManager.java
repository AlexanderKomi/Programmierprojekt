package de.hsh.amir.logic;

import common.util.Logger;
import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GegnerManager implements Observer{
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
        gegner.addObserver(this);
        this.gegnerListe.add(gegner);
    }

    public void move(Canvas canvas) {
        for (Gegner gegner : gegnerListe) {
            gegner.move();
            if (gegner.getY() >= canvas.getHeight()){
                gegner.setY(-30);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
