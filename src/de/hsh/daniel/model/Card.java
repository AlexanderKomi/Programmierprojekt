package de.hsh.daniel.model;


import common.actor.Actor;


public class Card extends Actor {

    // START: DEBUG CARDBACK
    private /*static final */ boolean DEBUG_DONT_SHOW_CARDBACK = false;
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

/*    public void onMouseClick( double x, double y, double imgSize, double[] pos ) {
        Logger.log( this.getClass() + ": Clicked at : (" + x + ", " + y + ")" );
        this.setPos(pos);
    }*/

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
            c1.setCurrentImage(this.pictureFileName);
            c2.setCurrentImage(this.pictureFileName);
        }
        else {
            setCardMatched( false );
            c1.setCurrentImage(Resources.cardback);
            c2.setCurrentImage(Resources.cardback);

        }
        return false;
    }

    public void turn() {
        double[] backupPos    = this.getPos();
        double   backupWidth  = this.getWidth();
        double   backupHeight = this.getHeight();
        this.setCurrentImage( this.getPictureFileName() );
        this.setPos( backupPos );
        this.setWidth( backupWidth );
        this.setHeight( backupHeight );
    }

    /* -------------------------------- GETTERS & SETTERS -------------------------------- */


    public int getPair_id() {
        return this.pair_id;
    }

    public String getPictureFileName() { return this.pictureFileName;}

    public void setDebugDontShowCardback(boolean bool) {
        this.DEBUG_DONT_SHOW_CARDBACK = bool;}

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

