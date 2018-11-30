package de.hsh.kevin.logic;

import java.util.ArrayList;
import java.util.List;

import common.util.Logger;
import de.hsh.kevin.logic.myActor.Paket;
import de.hsh.kevin.logic.myActor.PaketFactory;
import javafx.scene.canvas.Canvas;

public class PaketManager {

    private ArrayList<Paket> pakete;
    private double width;
    private double height;

    public PaketManager(double width, double height) {
	pakete = new ArrayList<>();
	this.width = width;
	this.height = height;
    }

    public List<Paket> getPakete() {
	return pakete;
    }

    /**
     * Erstellt zu 50:50 ein Good oder BadPaket
     */
    public void createNewPaket() {
	double rand = Math.random();
	if (rand < 0.5) {
	    createGoodPaket();
	} else {
	    createBadPaket();
	}
    }

    /**
     * Erstellt zu angegebener Chance ein GoodPaket, sonst ein BadPaket
     * 
     * @param chance
     */
    public void createNewPaket(double chance) {
	double rand = Math.random();
	if (rand < chance) {
	    createGoodPaket();
	} else {
	    createBadPaket();
	}
    }

    public void createBadPaket() {
	Paket p = PaketFactory.getBadPaket(1, 1);
	setOnFreeLocation(p);
	pakete.add(p);
    }

    public void createGoodPaket() {
	Paket p = PaketFactory.getGoodPaket(1, 1);
	setOnFreeLocation(p);
	pakete.add(p);
    }

    public void move() {
	for (int i = 0; i < pakete.size(); i++) {
	    if (pakete.get(i).getPos()[1] > height) {
		pakete.remove(i);
	    } else {
		pakete.get(i).move();
	    }
	}
    }

    public void draw(Canvas canvas) {
	for (Paket p : pakete) {
	    p.draw(canvas, 0, 0);
	}
    }

    private void setOnFreeLocation(Paket paket) {
	double randX = -1;
	boolean free = false;
	boolean isCollided = false;

	do {
	    // sucht randX sodass Paket ganz im Bild ist
	    do {
		randX = Math.random() * width;
	    } while (randX + paket.getWidth() > width);

	    paket.setPos(randX, 1);

	    // check ob Platz belegt ist
	    isCollided = false;

	    for (Paket p : pakete) {
		if (p.doesCollide(paket)) {
		    isCollided = true;
		    break;
		}
	    }

	    if (!isCollided) {
		free = true;
	    }
	} while (!free);
    }

}
