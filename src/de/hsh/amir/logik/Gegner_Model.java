package de.hsh.amir.logik;

import java.util.Observable;

public class Gegner_Model extends Observable {
    private int x;
    private int y;


    public void move(final char direction_Char){
        switch (direction_Char) {
            case 'D': // nach unten bewegen
                y = Math.min(Level_Model.LEVELMODEL_HEIGHT-100, y +5);
                setChanged();
                break;
            case 'U': // nach oben bewegen
                y = Math.max(0, y -5);
                setChanged();
                break;
        }
        notifyObservers();
    }






    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x!=this.x){
            this.x = x;
            setChanged();
        }
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y!=this.y){
            this.y = y;
            setChanged();
        }
    }
}
