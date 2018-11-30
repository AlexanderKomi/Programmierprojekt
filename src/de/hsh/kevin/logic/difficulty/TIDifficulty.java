package de.hsh.kevin.logic.difficulty;

public class TIDifficulty {
    enmDifficultyOptions diff;
    double factor = 1;

    public TIDifficulty() {

    }

    public TIDifficulty(enmDifficultyOptions difficulty) {
	diff = difficulty;
	setFactor();
    }

    private void setFactor() {
	switch (diff) {
	case easy:
	    factor = 1;
	    break;
	case normal:
	    factor = 1.25;
	    break;
	case hard:
	    factor = 1.5;
	    break;
	default:
	    factor = 1;
	    break;
	}

    }

    public enmDifficultyOptions getDifficulty() {
	return diff;
    }

    public void setDifficulty(enmDifficultyOptions difficulty) {
	this.diff = difficulty;
    }

    public double getFactor() {
	return factor;
    }

}
