package de.hsh.kevin.logic.myActor;

import common.actor.Actor;

import java.io.FileNotFoundException;
import java.util.List;

public class Projectile extends Actor{
    
    private static final double startX = 100;
    private static final double startY = 10;
    private static final int changePictureDelay = 15;
    private double speed = 4;

    public Projectile(String pictureFileName) throws FileNotFoundException {
	this(pictureFileName, startX, startY);
    }

    public Projectile(String pictureFileName, double x, double y) throws FileNotFoundException {
	super(pictureFileName, x, y);
    }

    public Projectile(List<String> pictureFileName) throws FileNotFoundException {
	this(pictureFileName, startX, startY);
    }

    public Projectile(List<String> pictureFileName, double x, double y) throws FileNotFoundException {
	super(pictureFileName, x, y, changePictureDelay);
    }
    
    public void move() {
	setPos(this.getX(), this.getY() - speed);
    }
    
    public String toString() {
	return super.toString();
    }
}
