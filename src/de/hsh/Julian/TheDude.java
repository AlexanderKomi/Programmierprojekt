package de.hsh.Julian;

import common.actor.Actor;

import java.io.FileNotFoundException;

class TheDude extends Actor {

    private static final String firstImage  = Leertastenklatsche.location + "thedude.png";
    private static final String secondImage = Leertastenklatsche.location + "thedude_turned.png";

    boolean turnedleft = true;

    TheDude( double x, double y ) throws FileNotFoundException {
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
}
