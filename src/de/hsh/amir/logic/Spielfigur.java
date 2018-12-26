package de.hsh.amir.logic;


import common.actor.ControlableActor;
import common.actor.Direction;

import java.util.HashMap;

public class Spielfigur extends ControlableActor {

    private static final double startX = 250;
    private static final double startY = 750;
    private static final int defaultSpeed = 10;
    private static final String spielFigurBildpfad = "/de/hsh/amir/resources/playerFigur.png";
    private static final HashMap<String, Direction> playerKeyMap = createPlayerKeymap();


    Spielfigur() {
        super(spielFigurBildpfad, startX, startY, playerKeyMap);
        this.setSpeed(defaultSpeed);
    }

    private static HashMap<String, Direction> createPlayerKeymap() {
        HashMap<String, Direction> playerKeyMap = new HashMap<>();
        playerKeyMap.put("Right", Direction.Right);
        playerKeyMap.put("Left", Direction.Left);
        return playerKeyMap;
    }

    @Override
    protected double[] calculateDirectedSpeed(Direction direction, double movementSpeed) {
        double[] xyTuple = new double[2];
        if (direction == Direction.Left) {
            xyTuple[0] = -movementSpeed;
            xyTuple[1] = 0;
        } else if (direction == Direction.Right) {
            xyTuple[0] = movementSpeed;
            xyTuple[1] = 0;
        }
        return xyTuple;
    }

}