package de.hsh.amir.logic;

import java.util.ArrayList;

public class GegnerManager {
    private ArrayList<Gegner> gegnerListe;
    private Points points;

    public GegnerManager(ArrayList<Gegner> gegnerListe, Points points) {
        this.gegnerListe = gegnerListe;
        this.points = points;
    }

    public ArrayList<Gegner> getGegnerListe(){
        return gegnerListe;
    }
}
