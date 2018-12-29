package de.hsh.daniel.model;

public class Player {


    private String name;
    private int points;
    private boolean myTurn;


    public Player(String name) {
        setName(name);
        points = 0;
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


    /*public Player getActivePlayer() {
        return this;
    }*/
    /*public void setDraw(int points) {
        this.points = points;
    }
*/
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




