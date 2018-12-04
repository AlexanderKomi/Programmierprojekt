package de.hsh.Julian;

import common.actor.Actor;

import java.io.FileNotFoundException;

class TheDude extends Actor {

    public static final String firstImage = Leertastenklatsche.location + "thedude.png";

    TheDude( double x, double y ) throws FileNotFoundException {
        super( firstImage, x, y );
    }

}
