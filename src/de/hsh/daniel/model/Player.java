package de.hsh.daniel.model;

public class Player {



    private String name;
    private int points;
    private boolean myTurn;

    public Player (String name) {
        setName(name);
    }

    /*TODO: Implement check if players is in line
    public boolean isMyTurn() {
        return myTurn = false;
    }



/*------------------------------------------------------------------------- GETTERS & SETTERS -------------------------------------------------------------------------*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}




