package de.hsh.kevin.logic;

public class Score {
    int score = 0;

    public Score() {

    }

    public int getScore() {
	return score;
    }

    public void increase() {
	score++;
    }
    
    public void decrease() {
	if(score >= 2) {
	    score -= 2;
	} else {
	    score = 0;
	}
	
    }
    
    public void setScore(int score) {
	this.score = score;
    }

}
