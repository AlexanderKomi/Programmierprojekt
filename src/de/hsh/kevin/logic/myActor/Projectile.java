package de.hsh.kevin.logic.myActor;

import common.actor.Actor;

import java.util.List;

/**
 * Erstellt Projektil 
 * @author Kevin
 *
 */
public class Projectile extends Actor {
    
    private static final double startX = 100;
    private static final double startY = 10;
    private static final int changePictureDelay = 15;
    private double speed = 4;

    /**
     * Erstellt Projektil an Standard Position
     * @param pictureFileName Pfad zum Bild
     */
    public Projectile(String pictureFileName) {
	this(pictureFileName, startX, startY);
    }

    /**
     * Erstellt Projektil an Position (x,y)
     * @param pictureFileName Pfad zum Bild
     * @param x - Wert des Projektils
     * @param y - Wert des Projektils
     */
    public Projectile(String pictureFileName, double x, double y)  {
	super(pictureFileName, x, y);
    }

    /**
     * Erstellt Projektil an Standard Position mit wechselnden Bildern
     * @param pictureFileName pictureFileName Pfad zum Bildern
     */
    public Projectile(List<String> pictureFileName) {
	this(pictureFileName, startX, startY);
    }

    /**
     *  Erstellt Projektil an Position (x,y) mit wechselnden Bildern
     * @param pictureFileName pictureFileName Pfad zum Bildern
     * @param x - Wert des Projektils
     * @param y - Wert des Projektils
     */
    public Projectile(List<String> pictureFileName, double x, double y) {
	super(pictureFileName, x, y, changePictureDelay);
    }
    
    /**
     * Bewegt das Projektil um gegebenes Speed
     */
    public void move() {
	setPos(this.getX(), this.getY() - speed);
    }
    
    public String toString() {
	return super.toString();
    }
}
