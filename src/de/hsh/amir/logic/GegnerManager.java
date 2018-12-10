package de.hsh.amir.logic;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class GegnerManager {
    private ArrayList<Gegner> gegnerListe = new ArrayList<Gegner>();
    private Points points;

    //TODO
    public GegnerManager(ArrayList<Gegner> gegnerListe, Points points) {
        this.gegnerListe = gegnerListe;
        this.points = points;
    }

    public ArrayList<Gegner> getGegnerListe(){
        return gegnerListe;
    }

    //TODO
    public void draw(Canvas canvas) {
        for (Gegner g : gegnerListe) {
            g.draw(canvas, 0, 0);
        }
    }
}
