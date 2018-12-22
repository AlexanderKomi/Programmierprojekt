package de.hsh.daniel.model;

public class Player {



    private String name;
    private int points;
    private boolean myTurn;
    private int playerCount;

    public Player () {
        ++playerCount;
        points = 0;
        setName("P"+playerCount);
    }

    /*TODO: Implement check if players is in line
    */

    public boolean isMyTurn() {
        return myTurn = false;
    }

    public void incrementPoints() { points++;}



/*------------------------------------------------------------------------- GETTERS & SETTERS -------------------------------------------------------------------------*/


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public int getPoints() {
        return this.points;
    }


}




