package de.hsh.daniel.model;

public class Player {



    private String name;
    private int points;
    private boolean myTurn;
    private static int playerCount = 0;


    public Player () {
        ++playerCount;
        points = 0;
        setName("P"+playerCount);
    }

    /*TODO: Implement check if players is in line
    */



    /*
    Adds one point to points
     */
    public void incrementPoints() { points++;}



/*------------------------------------------------------------------------- GETTERS & SETTERS -------------------------------------------------------------------------*/


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Player getActivePlayer() { return this; }
    public int getPoints() {
        return this.points;
    }

    public void setTurn() {this.myTurn = true; }
    public boolean isMyTurn() { return myTurn;
    }

}




