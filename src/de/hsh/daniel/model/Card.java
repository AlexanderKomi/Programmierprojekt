package de.hsh.daniel.model;


import common.actor.Actor;
import common.util.Logger;


public class Card extends Actor {

    // START: DEBUG CARDBACK
    private /*static final */ boolean DEBUG_DONT_SHOW_CARDBACK = false;
    // END: DEBUG CARDBACK


    private final int     pair_id;
    private final String  pictureFileName;
    private       boolean cardMatched  = false;


    private boolean turned = true;


    Card( String pictureFileName, int pair_id ) {
        super( pictureFileName );
        this.pictureFileName = pictureFileName;
        if ( !DEBUG_DONT_SHOW_CARDBACK ) {
            this.setCurrentImage( Resources.cardback );
        }
        this.pair_id = pair_id;
    }

    @Override
    public String toString() {
        return "pair_id: " + this.getPair_id();
    }


    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) { return true; }
        if ( obj == null ) { return false; }
        if ( getClass() != obj.getClass() ) { return false; }
        Card other = (Card) obj;
        return pair_id == other.pair_id;
    }

    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + pair_id;
        return result;
    }




    /* -------------------------------- GETTERS & SETTERS -------------------------------- */


    public int getPair_id() {
        return this.pair_id;
    }

    public String getPictureFileName() { return this.pictureFileName;}


    public boolean isCardMatched() {
        return this.cardMatched;
    }

    public void setCardMatched( boolean cardMatched ) {
        this.cardMatched = cardMatched;
    }

    public boolean isTurned() {
        return turned;
    }

    public void setTurned(boolean turned) {
        this.turned = turned;
    }

}

