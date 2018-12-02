package de.hsh.kevin.logic.myActor;

import common.actor.Actor;

import java.io.FileNotFoundException;

public class Projectile extends Actor{

    protected Projectile( String pictureFileName ) throws FileNotFoundException {
	super(pictureFileName);
    }

}
