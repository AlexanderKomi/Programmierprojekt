package de.hsh.daniel.model;


import common.actor.Actor;
import common.util.Logger;
import javafx.scene.input.MouseEvent;


public class Card extends Actor {

    // START: DEBUG CARDBACK
    private static final boolean DEBUG_DONT_SHOW_CARDBACK = false;
    // END: DEBUG CARDBACK


    private final int     pair_id;
    private final String  pictureFileName;
    private       boolean cardMatched  = false;
    private       boolean cardSelected = false;

    Card( String pictureFileName, int pair_id ) {
        super( pictureFileName );
        this.pictureFileName = pictureFileName;
        if ( !DEBUG_DONT_SHOW_CARDBACK ) {
            this.setCurrentImage( Resources.cardback );
        }
        this.pair_id = pair_id;
    }

    public void onMouseClick( final MouseEvent clickEvent ) {
        double x = clickEvent.getX();
        double y = clickEvent.getY();
        Logger.log( this.getClass() + ": Clicked at : (" + x + ", " + y + ")" );
        this.setCurrentImage( pictureFileName );
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


    public boolean isCardMatched( Card c1, Card c2 ) {
        if ( c1.equals( c2 ) ) {
            setCardMatched( true );
            //leave cards face up
        }
        else {
            setCardMatched( false );
            //flip cards back
        }
        return false;
    }


    /* -------------------------------- GETTERS & SETTERS -------------------------------- */


    public int getPair_id() {
        return this.pair_id;
    }

    public boolean getCardMatched() {
        return this.cardMatched;
    }

    public void setCardMatched( boolean cardMatched ) {
        this.cardMatched = cardMatched;
    }

    public boolean getCardSelected() {
        return this.cardSelected;
    }

    public void setCardSelected( boolean cardSelected ) {
        this.cardSelected = cardSelected;
    }

}

