package de.hsh.amir.logic;

public class Points {
    protected int points = 0;
    private int increment = 1;

    public Points() {

    }

    public void increase() {
        points += increment;
    }

    public void decrease() {
        if (points > getIncrement()) {
            points -= getIncrement();
        } else {
            points = 0;
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }


}
