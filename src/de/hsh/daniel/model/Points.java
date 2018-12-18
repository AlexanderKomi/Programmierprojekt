package de.hsh.daniel.model;

public class Points {

    private int points;
    private Player playerOne;
    private Player playTwo;

    public void increment() { points++;}








    /* --------------------- GETTER & SETTER ---------------------*/

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public Player getPlayerOne() {
        return playerOne;
    }
    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayTwo() {
        return playTwo;
    }
    public void setPlayTwo(Player playTwo) {
        this.playTwo = playTwo;
    }



}
