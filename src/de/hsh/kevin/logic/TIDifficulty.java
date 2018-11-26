package de.hsh.kevin.logic;

public class TIDifficulty {
    int diff = 0;
    double factor = 1;

    public TIDifficulty() {

    }

    public TIDifficulty(int difficulty) {
	diff = difficulty;
	setFactor();
    }

    private void setFactor() {
	switch (diff) {
	case 0:
	    factor = 1;
	    break;
	case 1:
	    factor = 1.2;
	    break;
	case 2:
	    factor = 1.5;
	    break;
	default:
	    factor = 1;
	    break;
	}

    }

    public int getDifficulty() {
	return diff;
    }

    public void setDifficulty(int difficulty) {
	this.diff = difficulty;
    }

    public double getFactor() {
	return factor;
    }

}
