package de.hsh.kevin.logic.myActor;

import common.actor.ControlableActor;
import common.actor.Direction;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class Player extends ControlableActor {

    private static final double startX = 250;
    private static final double startY = 750;
    private static final int defaultSpeed = 10;
    private static final int changePictureDelay = 15;

    public Player(String pictureFileName, HashMap<String, Direction> keyMap) throws FileNotFoundException {
	this(pictureFileName, startX, startY, keyMap);
    }

    public Player(String pictureFileName, double x, double y, HashMap<String, Direction> keyMap)
	    throws FileNotFoundException {
	super(pictureFileName, x, y, keyMap);
	this.setSpeed(defaultSpeed);
    }

    
    public Player(List<String> pictureFileName, HashMap<String, Direction> keyMap) throws FileNotFoundException {
	this(pictureFileName, startX, startY, keyMap);
    }

    public Player(List<String> pictureFileName, double x, double y, HashMap<String, Direction> keyMap)
	    throws FileNotFoundException {
	super(pictureFileName, x, y, keyMap, changePictureDelay);
	this.setSpeed(defaultSpeed);
    }

    @Override
    protected double[] calculateNewPosFromInput() {
	double[] xyTuple = new double[2];
	double velocity = getSpeed();

	getMovement().getDirections().forEach(direction -> {
	    if (getMovement().isHoldDown(direction)) {
		if (direction == Direction.Left) {
		    xyTuple[0] = -velocity;
		    xyTuple[1] = 0;
		} else if (direction == Direction.Right) {
		    xyTuple[0] = velocity;
		    xyTuple[1] = 0;
		}
	    }
	});
	return xyTuple;
    }

}
