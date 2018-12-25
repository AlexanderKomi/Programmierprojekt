package de.hsh.daniel.model;


import common.actor.Actor;
import common.util.Logger;
import common.util.PlaySound;


public class Card extends Actor {


    private static final String  cardClickedSoundFilePath = "src\\de\\hsh\\Julian\\wav\\collision.wav";
    private final        int     pair_id;
    private final        String  pictureFileName;
    private              boolean cardMatched              = false;


    private boolean turned = true;


    public Card( String pictureFileName, int pair_id ) {
        super( pictureFileName );
        this.pictureFileName = pictureFileName;
        this.setCurrentImage( Resources.cardback );
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

    public void turn() {

        PlaySound.playSound( cardClickedSoundFilePath );
        final double backupWidth  = this.getWidth();
        final double backupHeight = this.getHeight();

        if ( this.isTurned() ) {
            this.setCurrentImage( this.getPictureFileName() );
            this.setTurned( false );
        }
        else if ( !this.isTurned() ) {
            this.setCurrentImage( Resources.cardback );
            this.setTurned( true );
        }
        else {
            Logger.log( "CARD ALREADY FACEUP" );
        }
        this.setWidth( backupWidth );
        this.setHeight( backupHeight );
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

