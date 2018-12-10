package de.hsh.amir.logic;


import common.actor.ControlableActor;
import common.actor.Direction;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class Spielfigur extends ControlableActor{

    private static final double startX = 250;
    private static final double startY = 750;
    private static final int defaultSpeed = 10;

    protected Spielfigur(String pictureFileName, HashMap<String, Direction> keyMap) throws FileNotFoundException {
        this(pictureFileName, startX, startY, keyMap);
    }

    protected Spielfigur(String pictureFileName, double x, double y, HashMap<String, Direction> keyMap) throws FileNotFoundException {
        super(pictureFileName, x, y, keyMap);
        this.setSpeed(defaultSpeed);
    }

    protected Spielfigur(List<String> pictureFileName, double x, double y, HashMap<String, Direction> keyMap, int delay) throws FileNotFoundException {
        super(pictureFileName, x, y, keyMap, delay);
        this.setSpeed(defaultSpeed);
    }


    @Override
    protected double[] calculateDirectedSpeed( Direction direction, double movementSpeed ) {
        double[] xyTuple = new double[ 2 ];
        if (direction == Direction.Left) {
            xyTuple[ 0 ] = -movementSpeed;
            xyTuple[ 1 ] = 0;
        } else if (direction == Direction.Right) {
            xyTuple[ 0 ] = movementSpeed;
            xyTuple[ 1 ] = 0;
        }
        return xyTuple;
    }

}