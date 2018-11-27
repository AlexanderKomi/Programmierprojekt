package de.hsh.kevin.logic.myActor;

import java.io.FileNotFoundException;

public class BadPackage extends Package{

    protected BadPackage( String pictureFileName ) throws FileNotFoundException {
	super(pictureFileName);
    }

}
