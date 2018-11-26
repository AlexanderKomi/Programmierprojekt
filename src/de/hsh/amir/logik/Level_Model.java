package de.hsh.amir.logik;

import java.util.Observable;
import java.util.Observer;

public class Level_Model extends Observable implements Observer {

    public static final int LEVELMODEL_WIDTH = 1280;
    public static final int LEVELMODEL_HEIGHT = 800;


    private Spielfigur_Model mSpielfigur;

    private int mMoveDir = 0;

    public Level_Model() {
        mSpielfigur = new Spielfigur_Model();
        mSpielfigur.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
