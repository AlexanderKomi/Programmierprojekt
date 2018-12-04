package de.hsh.amir.logik;

public class Points {
    int points = 0;

    public Points() {

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void increase() {
        points++;
    }

    public void decrease() {
        if (points >= 1) {
            points -= 1;
        } else {
            points = 0;
        }

    }


}
