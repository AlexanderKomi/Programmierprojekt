package de.hsh.amir.logik;

import java.util.Observable;

public class Gegner_Model extends Observable {

    private int x;
    private int y;
    private int modelType;

    private boolean isTouched = false;


    public Gegner_Model() {
        isTouched = false;
    }

    public Gegner_Model(int x, int y, int modelType) {
        this.x = x;
        this.y = y;
        this.modelType = modelType; // um Gegnertypen später zu definieren und ihr Verhalten zu steuern
    }

    public void move(final char direction_Char) {
        switch (direction_Char) {
            case 'D': // nach unten bewegen
                y = Math.min(Level_Model.LEVELMODEL_HEIGHT - 100, y + 5);
                setChanged();
                break;
            case 'U': // nach oben bewegen
                y = Math.max(0, y - 5);
                setChanged();
                break;
        }
        notifyObservers();
    }


    public boolean getIsTouched() {
        return isTouched;
    }

    public void setIsTouched(boolean isTouched) {
        if (isTouched != this.isTouched) {
            this.isTouched = isTouched;
            setChanged();
        }
    }

    public void touch() {
        this.isTouched = true;
    }


    //    Getter und Setter
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
