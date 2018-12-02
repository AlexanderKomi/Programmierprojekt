package de.hsh.kevin.logic.myActor;

import common.actor.Actor;
import javafx.scene.canvas.Canvas;

import java.io.FileNotFoundException;
import java.util.List;

public class Paket extends Actor {

    private static final double startX = 100;
    private static final double startY = 10;
    private static final int changePictureDelay = 15;
    private double speed = 5;
    private enmPaketTyp paketTyp;

    public Paket(String pictureFileName, enmPaketTyp paketTyp) throws FileNotFoundException {
	this(pictureFileName, startX, startY, paketTyp);
    }

    public Paket(String pictureFileName, double x, double y, enmPaketTyp paketTyp) throws FileNotFoundException {
	super(pictureFileName, x, y);
	this.paketTyp = paketTyp;
    }

    public Paket(List<String> pictureFileName, enmPaketTyp paketTyp) throws FileNotFoundException {
	this(pictureFileName, startX, startY, paketTyp);
    }

    public Paket(List<String> pictureFileName, double x, double y, enmPaketTyp paketTyp) throws FileNotFoundException {
	super(pictureFileName, x, y, changePictureDelay);
	this.paketTyp = paketTyp;
    }

    public enmPaketTyp getPaketTyp() {
	return paketTyp;
    }

    public void move() {
	setPos(this.getX(), this.getY() + speed);
    }
    
}
