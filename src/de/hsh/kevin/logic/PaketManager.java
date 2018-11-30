package de.hsh.kevin.logic;

import java.util.ArrayList;
import java.util.List;

import de.hsh.kevin.logic.myActor.Paket;
import de.hsh.kevin.logic.myActor.PaketFactory;
import javafx.scene.canvas.Canvas;

public class PaketManager {

    private ArrayList<Paket> pakete;
    private double width;

    public PaketManager( double width) {
	pakete = new ArrayList<>();
	this.width = width;
    }

    public List<Paket> getPakete() {
	return pakete;
    }

    
    /**
     * Erstellt zu 50:50 ein Good oder BadPaket
     */
    public void createNewPaket() {
	double rand = Math.random();
	if(rand < 0.5) {
	    createGoodPaket();
	} else {
	    createBadPaket();
	}
    }
    
    
    /**
     * Erstellt zu angegebener Chance ein GoodPaket, sonst ein BadPaket
     * @param chance
     */
    public void createNewPaket(double chance) {
	double rand = Math.random();
	if(rand < chance) {
	    createGoodPaket();
	} else {
	    createBadPaket();
	}
    }

    public void createBadPaket() {
	Paket p = PaketFactory.getBadPaket(0, 0);
	setOnFreeLocation(p);
	pakete.add(p);
    }
    
    

    public void createGoodPaket() {
	Paket p = PaketFactory.getGoodPaket(0, 0);
	setOnFreeLocation(p);
	pakete.add(p);
    }
    
    public void move() {
	for(Paket p: pakete) {
	    p.move();
	}
    }
    
    public void draw(Canvas canvas) {
	for(Paket p: pakete) {
	    p.draw(canvas);
	}
    }
    
    private void setOnFreeLocation(Paket paket) {
	double randX = 0;
	
	while(randX == 0) {
	   randX = Math.random() * width;
	   
	   // check ob Paket nur halb im Bild (rechter Rand)
	   if(randX + paket.getWidth() > width) {
	       randX = 0;
	   }
	   
	   // check ob Platz belegt ist
	   for (Paket p: pakete) {
	       if(p.doesCollide(paket) || paket.doesCollide(p)) {
		   randX= 0;
		   break;
	       }
	   }
	}
	
	// Freier Platz wurde gefunden
	paket.setPos(randX, paket.getPos()[0]);
    }

}
