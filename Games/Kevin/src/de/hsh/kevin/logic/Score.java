package de.hsh.kevin.logic;

/**
 * Counter für den Score
 * @author Kevin
 *
 */
public class Score {
    int score = 0;

    /**
     * Erstellt den Score auf 0
     */
    public Score() {

    }

    /**
     * Liefert den Score
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Erhöht den Score um 1
     */
    public void increase() {
        score++;
    }

    /**
     * Verringert den Score um 2 bis maximal auf 0
     */
    public void decrease() {
        if (score >= 2) {
            score -= 2;
        } else {
            score = 0;
        }

    }

    /**
     * Setzt den Score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

}
