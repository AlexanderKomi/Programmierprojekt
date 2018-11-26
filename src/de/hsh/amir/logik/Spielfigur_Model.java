package de.hsh.amir.logik;

import java.util.Observable;

public class Spielfigur_Model extends Observable {

    private int x;
    private int y; //Muss später final gesetzt werden, da sich Spielfigur nur nach links unr rechts bewegen kann.

    public static int counter = 0; //um Punkte zu zählen.

    public void move(final char direction_Char) {
        switch (direction_Char) {
            case 'R': // nach rechts bewegen
                x = Math.min(Level_Model.LEVELMODEL_WIDTH - 100, x + 5);
                setChanged();
                break;
            case 'L': // nach links bewegen
                x = Math.max(0, x - 5);
                setChanged();
                break;
        }
        notifyObservers();
    }


    //GETTER UND SETTER
    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x != this.x) {
            this.x = x;
            setChanged();
        }
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y != this.y) {
            this.y = y;
            setChanged();
        }
    }
}
