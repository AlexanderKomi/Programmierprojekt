package de.hsh.Julian;

import common.actor.Actor;
import common.actor.Collectable;

class TheDude extends Actor {

    private static final String firstImage  = Leertastenklatsche.location + "thedude.png";
    private static final String secondImage = Leertastenklatsche.location + "thedude_turned.png";

    boolean turnedleft = true;

    TheDude( double x, double y ) {
        super( firstImage, x, y );
    }

    void swapImage() {
        if ( this.getName().equals( firstImage ) ) {
            this.setCurrentImage( secondImage );
        }
        else {
            this.setCurrentImage( firstImage );
        }
    }

    /**
     * I have no idea what im doing.
     * - Alexander Komischke.
     */
    @Override
    public synchronized boolean collisionModifier( Actor other ) {
        if ( other instanceof Collectable ) {
            final Collectable c = (Collectable) other;
            c.wasCollected( this );
            return false;
        }

        return super.collisionModifier( other );
    }
}
