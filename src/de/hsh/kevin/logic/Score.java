package de.hsh.kevin.logic;

public class Score {
    int score = 0;

    public Score() {

    }

    public int getScore() {
	return score;
    }

    public void increaseScore() {
	increaseScore(0);
    }

    /**
     * 
     * @param type 0 -> , 1 -> , 2 ->
     */
    public void increaseScore(int type) {
	switch (type) {
	case 0:
	    break;
	case 1:
	    break;
	case 2:
	    break;
	default:
	    break;
	}
    }
    
    public void setScore(int score) {
	this.score = score;
    }

}
