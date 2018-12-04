package de.hsh.kevin.logic.myActor;

import common.actor.ControlableActor;
import common.actor.Direction;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class Player extends ControlableActor {

    private static final double startX = 250;
    private static final double startY = 750;
    private static final int defaultSpeed = 10;
    private static Image firingImage;
    private static Image idleImage;
    private static boolean isFiring;

    public Player(String pictureFileName, HashMap<String, Direction> keyMap) throws FileNotFoundException {
	this(pictureFileName, startX, startY, keyMap);
    }

    public Player(String pictureFileName, double x, double y, HashMap<String, Direction> keyMap)
	    throws FileNotFoundException {
	super(pictureFileName, x, y, keyMap);
	this.setSpeed(defaultSpeed);
    }

    /**
     * Setzt das zweite Bild der List<String> als feuerndes Bild und das erste Bild
     * sonst
     *
     * @param pictureFileName
     * @param keyMap
     * @throws FileNotFoundException
     */
    public Player(List<String> pictureFileName, HashMap<String, Direction> keyMap) throws FileNotFoundException {
	this(pictureFileName, startX, startY, keyMap);
    }

    /**
     * Setzt das zweite Bild der List<String> als feuerndes Bild und das erste Bild
     * sonst
     *
     * @param pictureFileName
     * @param keyMap
     * @throws FileNotFoundException
     */
    public Player(List<String> pictureFileName, double x, double y, HashMap<String, Direction> keyMap)
	    throws FileNotFoundException {
	super(pictureFileName.get(0), x, y, keyMap);
	this.setSpeed(defaultSpeed);
	if (pictureFileName.size() >= 2) {
	    idleImage = new Image(new FileInputStream(pictureFileName.get(0)));
	    firingImage = new Image(new FileInputStream(pictureFileName.get(1)));
	}
    }

	@Override
	protected double[] calculateDirectedSpeed( Direction direction, double movement_speed ) {
		double[] xyTuple = new double[ 2 ];
		if (direction == Direction.Left) {
			xyTuple[ 0 ] = -movement_speed;
			xyTuple[ 1 ] = 0;
		} else if (direction == Direction.Right) {
			xyTuple[ 0 ] = movement_speed;
			xyTuple[ 1 ] = 0;
		}
	return xyTuple;
    }

    public void switchFiring() {
	if(firingImage == null) {
	    return;
	}
	this.setCurrentImage(firingImage);
	isFiring = true;
    }

    public void switchIdle() {
	if(idleImage == null) {
	    return;
	}
	this.setCurrentImage(idleImage);
	isFiring = false;
    }

    public boolean isFiring() {
	return isFiring;
    }
}
