package de.hsh.amir;

import java.util.Observable;

public class SpielfigurModel extends Observable {

    private int X;

    public void move(final int direction){
        switch (direction) {
            case 0: // nach rechts
                X = Math.min(LevelModel.LEVELMODEL_WIDTH-100,X+5);
                setChanged();
                break;
            case 1: // nach links
                X = Math.max(0, X-5);
                setChanged();
                break;
        }
        notifyObservers();
    }

}
