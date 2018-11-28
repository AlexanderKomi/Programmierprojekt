package de.hsh.kevin.logic.myActor;

import common.actor.ControlableActor;
import common.actor.Direction;

import java.util.HashMap;

public class Player extends ControlableActor {

    protected Player(String pictureFileName, double x, double y, double height, double width,
	    HashMap<String, Direction> keymap) {
        super(pictureFileName, "", x, y, height, width, keymap);
    }

}
