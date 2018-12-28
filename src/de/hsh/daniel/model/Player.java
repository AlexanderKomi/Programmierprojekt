package de.hsh.daniel.model;

public class Player {


    private String name;
    private int points;
    private boolean myTurn;
    private static int playerCount = 0;


    public Player() {
        ++playerCount;
        points = 0;
        if (playerCount < 3) {
            setName("P" + playerCount);
        } else {
            setName("BOTH");
            ;
        }
    }

    /**
     * Increments players points
     */
    public void incrementPoints() {
        points++;
    }



    /*------------------------------------------------------------------------- GETTERS & SETTERS -------------------------------------------------------------------------*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Player getActivePlayer() {
        return this;
    }

    public int getPoints() {
        return this.points;
    }

    public void setTurn(boolean turn) {
        this.myTurn = turn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

}




